package io.renren.modules.zd.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.dao.ZdCartDao;
import io.renren.modules.zd.entity.ZdCartEntity;
import io.renren.modules.zd.entity.ZdMajorCourseResourceEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.form.ZdCatFrom;
import io.renren.modules.zd.form.ZdCatResourceFrom;
import io.renren.modules.zd.service.ZdCartService;
import io.renren.modules.zd.service.ZdMajorCourseResourceService;
import io.renren.modules.zd.service.ZdOrderService;
import io.renren.modules.zd.vo.CartResourceVO;
import io.renren.modules.zd.vo.ZdCatVO;
import io.renren.modules.zd.vo.ZdOrderCartVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("zdCatService")
public class ZdCartServiceImpl extends ServiceImpl<ZdCartDao, ZdCartEntity> implements ZdCartService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ZdOrderService zdOrderService;
    @Resource
    private ZdMajorCourseResourceService zdMajorCourseResourceService;
    @Resource
    private SubjectResourceService subjectResourceService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdCartEntity> page = this.selectPage(
                new Query<ZdCartEntity>(params).getPage(),
                new EntityWrapper<ZdCartEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryListPage(Map<String, Object> params) {
        Page<ZdCatVO> page=new Query<ZdCatVO>(params).getPage();
        page.setRecords(baseMapper.selectListPage(page,params));
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void submitOrder(List ids, SysUserEntity user,String status,String note,String remark) {
        List<ZdOrderCartVO> cartEntityList=baseMapper.selectVOByIds(ids);
        if(cartEntityList==null||cartEntityList.size()==0)
        {
            throw new RRException("没有查到要提交的购物车订单");
        }
        logger.debug("提交购物车:{},操作人:{}",JSON.toJSONString(cartEntityList), ShiroUtils.getUserEntity().getUsername());
        zdOrderService.submitCatOrder(cartEntityList,user,status,note,remark);
        deleteBatchIds(ids);
    }

    @Override
    @Transactional
    public void updateCatForm(ZdCatFrom zdCatFrom) {
        List<ZdCatResourceFrom> resourceFromList= zdCatFrom.getCatForms();
        SysUserEntity sysUserEntity=zdCatFrom.getSysUserEntity();
        for(ZdCatResourceFrom zdCatResourceFrom:resourceFromList)
        {
            if(zdCatResourceFrom.getCatNum()<=0)
            {
                throw new RRException("数量不可小于等于0");
            }
           /* if(StringUtils.isBlank(zdCatResourceFrom.getMajorId())||StringUtils.isBlank(zdCatResourceFrom.getCourseId())||StringUtils.isBlank(zdCatResourceFrom.getResourceId()))
            {
                throw new RRException("请检查请求参数");
            }*/
            ZdCartEntity ZdCartEntity=baseMapper.selectById(zdCatResourceFrom.getId());
            if(ZdCartEntity==null)
            {
                throw new RRException("未查找到此订单");

            }
            ZdCartEntity.setCatNum(zdCatResourceFrom.getCatNum());
            ZdCartEntity.setUpdateBy(sysUserEntity.getUserId()+"");
            ZdCartEntity.setUpdateTime(new Date());
            updateById(ZdCartEntity);
            logger.debug("更新购物车:{},操作人:{}",JSON.toJSONString(ZdCartEntity), ShiroUtils.getUserEntity().getUsername());
        }
    }

    @Override
    @Transactional
    public void saveCatFormByRelation(ZdCatFrom zdCatFrom) {
        List<ZdCatResourceFrom> resourceFromList= zdCatFrom.getCatForms();
        SysUserEntity sysUserEntity= zdCatFrom.getSysUserEntity();
        for(ZdCatResourceFrom zdCatResourceFrom:resourceFromList)
        {
            if(zdCatResourceFrom.getCatNum()<=0)
            {
                throw new RRException("数量不可小于等于0");
            }
            if(StringUtils.isBlank(zdCatResourceFrom.getZmcrId()))
            {
                throw new RRException("请检查请求参数");
            }
            ZdCartEntity ZdCartEntity=baseMapper.selectByMCRId(zdCatResourceFrom.getZmcrId(),sysUserEntity.getUserId()+"");
            if(ZdCartEntity==null)
            {
                ZdCartEntity=new ZdCartEntity();
                ZdCartEntity.setCourseId(zdCatResourceFrom.getCourseId());
                ZdCartEntity.setMajorId(zdCatResourceFrom.getMajorId());
                ZdCartEntity.setResourceId(zdCatResourceFrom.getResourceId());
                ZdCartEntity.setOrgCode(sysUserEntity.getOrgCode());
                ZdCartEntity.setCatNum(zdCatResourceFrom.getCatNum());
                ZdCartEntity.setCreateBy(sysUserEntity.getUserId()+"");
                ZdCartEntity.setZmcrId(zdCatResourceFrom.getZmcrId());
                ZdCartEntity.setCreateTime(new Date());
                insert(ZdCartEntity);
                logger.debug("添加购物车:{},操作人:{}", JSON.toJSONString(ZdCartEntity), ShiroUtils.getUserEntity().getUsername());

            }else
            {
                if(ZdCartEntity.getCatNum()==null)
                {
                    ZdCartEntity.setCatNum(0);
                }
                ZdCartEntity.setCatNum(ZdCartEntity.getCatNum()+zdCatResourceFrom.getCatNum());
                updateById(ZdCartEntity);
                logger.debug("更新购物车:{},操作人:{}",JSON.toJSONString(ZdCartEntity), ShiroUtils.getUserEntity().getUsername());

            }

        }
    }

    @Override
    public List queryOrgList(Map<String, Object> params) {
        return baseMapper.listOrg(params);
    }

    @Override
    public List<ZdOrderCartVO> selectVOByIds(List ids) {
        return baseMapper.selectVOByIds(ids);
    }

    @Override
    public List<ZdCatVO> listAll(SearchForm searchForm) {

        return baseMapper.listAll(searchForm);
    }

    @Override
    @Transactional
    public void updateVersion(String id, String id1, List<String> orgList,String semesterCode) {
        baseMapper.updateVersion(id,id1,orgList,semesterCode);
    }

    @Override
    @Transactional
    public List<CartResourceVO> importCart(List<CartResourceVO> list, SysUserEntity user, String semesterCode) {
         list.stream().forEach(item->{
         ZdMajorCourseResourceEntity zdMajorCourseResourceEntity=zdMajorCourseResourceService.
                 selectOne(new EntityWrapper<ZdMajorCourseResourceEntity>().eq("major_code",item.getMajorCode()).eq("major_type",item.getMajorType())
                 .eq("student_type",item.getStudentType()).eq("course_code",item.getCourseCode()).eq("resource_code",item.getResourceCode()));
            if(StringUtils.isBlank(item.getResourceCode())||StringUtils.isBlank(item.getCourseCode())||StringUtils.isBlank(item.getStudentType())
                    ||StringUtils.isBlank(item.getMajorCode()) ||StringUtils.isBlank(item.getMajorType())){
                item.setResult("失败！必填项有空值");
                return ;
            }
            if(zdMajorCourseResourceEntity==null){
                item.setResult("失败！未找到报订规则");
                return ;
            }
            if(Constant.ZMCR_ARCHIVES.ARCHIVES.equals(zdMajorCourseResourceEntity.getIsArchives())){
                item.setResult("报订规则已归档！请联系管理员！");
                return ;
            }
            if(!NumberUtils.isNumber(item.getCatNum())||Integer.parseInt(item.getCatNum())<=0){
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

            ZdCartEntity zdCartEntity=baseMapper.selectByMCRId(zdMajorCourseResourceEntity.getId(),user.getUserId()+"");
            if(zdCartEntity==null){
                zdCartEntity=new ZdCartEntity();
            }
            if(zdCartEntity.getCatNum()==null){
                zdCartEntity.setCatNum(0);
            }
            zdCartEntity.setCatNum(zdCartEntity.getCatNum()+Integer.parseInt(item.getCatNum()));
            zdCartEntity.setZmcrId(zdMajorCourseResourceEntity.getId());
            zdCartEntity.setOrgCode(user.getOrgCode());
            zdCartEntity.setCreateBy(user.getUserId()+"");
            zdCartEntity.setUpdateBy(user.getUserId()+"");
            zdCartEntity.setCreateTime(new Date());
            zdCartEntity.setUpdateTime(new Date());
            insertOrUpdate(zdCartEntity);
            item.setResult("成功！");
        });
        return list;
    }


}
