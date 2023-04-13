package io.renren.modules.zd.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.dao.ZdOrderCartDao;
import io.renren.modules.zd.entity.ZdMajorCourseResourceEntity;
import io.renren.modules.zd.entity.ZdOrderCartEntity;
import io.renren.modules.zd.form.ZdCatFrom;
import io.renren.modules.zd.form.ZdCatResourceFrom;
import io.renren.modules.zd.service.ZdMajorCourseResourceService;
import io.renren.modules.zd.service.ZdOrderCartService;
import io.renren.modules.zd.service.ZdOrderService;
import io.renren.modules.zd.vo.ZdCatVO;
import io.renren.modules.zd.vo.ZdOrderCartVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("zdOrderCartService")
public class ZdOrderCartServiceImpl extends ServiceImpl<ZdOrderCartDao, ZdOrderCartEntity> implements ZdOrderCartService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ZdOrderService zdOrderService;
    @Resource
    private ZdMajorCourseResourceService zdMajorCourseResourceService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdOrderCartEntity> page = this.selectPage(
                new Query<ZdOrderCartEntity>(params).getPage(),
                new EntityWrapper<ZdOrderCartEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveCatForm(ZdCatFrom zdCatFrom) {
      List<ZdCatResourceFrom> resourceFromList= zdCatFrom.getCatForms();
      SysUserEntity sysUserEntity= zdCatFrom.getSysUserEntity();
      for(ZdCatResourceFrom zdCatResourceFrom:resourceFromList)
      {
          if(StringUtils.isBlank(zdCatResourceFrom.getZmcrId()))
          {
              throw new RRException("报定关系ID不可为空！");
          }
          ZdMajorCourseResourceEntity zdMajorCourseResourceEntity=zdMajorCourseResourceService.selectById(zdCatResourceFrom.getZmcrId());
          if(zdMajorCourseResourceEntity==null)
          {
              throw new RRException("报定关系未找到！");
          }
          if(zdCatResourceFrom.getCatNum()<=0)
          {
              throw new RRException("数量不可小于等于0");
          }
          if(StringUtils.isBlank(zdCatResourceFrom.getMajorId())||StringUtils.isBlank(zdCatResourceFrom.getCourseId())||StringUtils.isBlank(zdCatResourceFrom.getResourceId()))
          {
              throw new RRException("请检查请求参数");
          }
          ZdOrderCartEntity zdOrderCartEntity=baseMapper.selectByMCRId(zdCatResourceFrom.getMajorId(),zdCatResourceFrom.getCourseId(),zdCatResourceFrom.getResourceId(),sysUserEntity.getUserId()+"");
          if(zdOrderCartEntity==null)
          {
              zdOrderCartEntity=new ZdOrderCartEntity();
              zdOrderCartEntity.setZmcrId(zdCatResourceFrom.getZmcrId());
              zdOrderCartEntity.setCourseId(zdCatResourceFrom.getCourseId());
              zdOrderCartEntity.setMajorId(zdCatResourceFrom.getMajorId());
              zdOrderCartEntity.setResourceId(zdCatResourceFrom.getResourceId());
              zdOrderCartEntity.setOrgCode(sysUserEntity.getOrgCode());
              zdOrderCartEntity.setCatNum(zdCatResourceFrom.getCatNum());
              zdOrderCartEntity.setCreateBy(sysUserEntity.getUserId()+"");
              zdOrderCartEntity.setCreateTime(new Date());
              insert(zdOrderCartEntity);
              logger.debug("废弃！添加购物车:{},操作人:{}",JSON.toJSONString(zdOrderCartEntity), ShiroUtils.getUserEntity().getUsername());
          }else
          {
              if(zdOrderCartEntity.getCatNum()==null)
              {
                  zdOrderCartEntity.setCatNum(0);
              }
                zdOrderCartEntity.setCatNum(zdOrderCartEntity.getCatNum()+zdCatResourceFrom.getCatNum());
                updateById(zdOrderCartEntity);
              logger.debug("废弃！更新购物车:{},操作人:{}",JSON.toJSONString(zdOrderCartEntity), ShiroUtils.getUserEntity().getUsername());

          }

      }
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
        logger.debug("废弃！提交购物车:{},操作人:{}", JSON.toJSONString(cartEntityList), ShiroUtils.getUserEntity().getUsername());
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
            ZdOrderCartEntity zdOrderCartEntity=baseMapper.selectById(zdCatResourceFrom.getId());
            if(zdOrderCartEntity==null)
            {
                throw new RRException("未查找到此订单");

            }
            zdOrderCartEntity.setCatNum(zdCatResourceFrom.getCatNum());
            zdOrderCartEntity.setUpdateBy(sysUserEntity.getUserId()+"");
            zdOrderCartEntity.setUpdateTime(new Date());
            updateById(zdOrderCartEntity);
            logger.debug("废弃！更新购物车:{},操作人:{}",JSON.toJSONString(zdOrderCartEntity), ShiroUtils.getUserEntity().getUsername());

        }
    }

}
