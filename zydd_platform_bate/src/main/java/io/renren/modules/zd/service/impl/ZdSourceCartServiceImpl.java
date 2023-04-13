package io.renren.modules.zd.service.impl;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.dao.ZdSourceCartDao;
import io.renren.modules.zd.entity.ZdSourceCartEntity;
import io.renren.modules.zd.form.ZdSourceCatFrom;
import io.renren.modules.zd.service.ZdSourceCartService;
import io.renren.modules.zd.vo.ZdSourceCartVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("zdSourceCatService")
public class ZdSourceCartServiceImpl extends ServiceImpl<ZdSourceCartDao, ZdSourceCartEntity> implements ZdSourceCartService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SubjectResourceService subjectResourceService;
    @Override
    @Transactional
    public boolean saveCatForm(ZdSourceCatFrom zdCatFrom) {
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        zdCatFrom.getCartList().forEach(item->{
            item.setOrgCode(zdCatFrom.getSysUserEntity().getOrgCode());
            if(item.getNum()<=0){
              throw new ServiceException("数量不能小于等于0");
            }
            ZdSourceCartEntity zdSourceCartEntity=selectOne(new EntityWrapper<ZdSourceCartEntity>().eq("resource_id",item.getResourceId()).eq("create_by",sysUserEntity.getUserId()).eq("semester_code",zdCatFrom.getSemesterCode()));
            if(zdSourceCartEntity==null){
                item.setSemesterCode(zdCatFrom.getSemesterCode());
                insert(item);
            }else{
                zdSourceCartEntity.setNum(item.getNum()+zdSourceCartEntity.getNum());
                updateById(zdSourceCartEntity);
            }
        });
        return true;
    }

    @Override
    @Transactional
    public boolean updateCatForm(ZdSourceCatFrom zdCatFrom) {
        zdCatFrom.getCartList().forEach(item->{
            if(StringUtils.isBlank(item.getId())){
                throw new ServiceException("id不能为空");
            }
            if(item.getNum()<=0){
                throw new ServiceException("数量不能小于等于0");
            }
            updateById(item);
        });
        return true;
    }

    @Override
    public List<ZdSourceCartVO> queryList(Map<String, Object> params) {
        return baseMapper.selectAll(params);
    }

    @Override
    @Transactional
    public List<ZdSourceCartVO> importCart(List<ZdSourceCartVO> list, SysUserEntity user, String semesterCode) {
        list.stream().forEach(item->{
             if(StringUtils.isBlank(item.getResourceCode())){
                item.setResult("失败！必填项有空值");
                return ;
            }
            if(item.getNum()<=0){
                item.setResult("数量非法！");
                return ;
            }
            SubjectResourceEntity subjectResourceEntity=subjectResourceService.selectByCode(item.getResourceCode());
            if(subjectResourceEntity==null){
                item.setResult("书代号未找到！");
                return ;
            }
            if(Constant.RESOURCE_SHOW.NOT_SHOW.equals(subjectResourceEntity.getIsShow())){
                item.setResult("图书已下架！");
                return ;
            }
            if(Constant.RESOURCE_SCOPE.CUSTOM.equals(subjectResourceEntity.getResourceScope())){
                int count=subjectResourceService.countMap(subjectResourceEntity.getId(),user.getOrgCode());
                if(count<=0){
                    item.setResult("图书范围不合规！");
                    return ;
                }
            }
            ZdSourceCartEntity zdSourceCartEntity=selectOne(new EntityWrapper<ZdSourceCartEntity>().eq("resource_id",
                    subjectResourceEntity.getId()).eq("create_by",user.getUserId()));

            if(zdSourceCartEntity==null){
                zdSourceCartEntity=new ZdSourceCartEntity();
            }
            if(zdSourceCartEntity.getNum()==null){
                zdSourceCartEntity.setNum(0);
            }
            zdSourceCartEntity.setSemesterCode(semesterCode);
            zdSourceCartEntity.setNum(zdSourceCartEntity.getNum()+item.getNum());
            zdSourceCartEntity.setResourceId(subjectResourceEntity.getId());
            zdSourceCartEntity.setOrgCode(user.getOrgCode());
            zdSourceCartEntity.setCreateBy(user.getUserId()+"");
            zdSourceCartEntity.setCreateTime(new Date());
            insertOrUpdate(zdSourceCartEntity);
            item.setResult("成功！");
        });
        return list;
    }


}
