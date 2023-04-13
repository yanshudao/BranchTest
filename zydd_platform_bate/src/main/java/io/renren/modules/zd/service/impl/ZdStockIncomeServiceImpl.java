package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.subject.dao.SubjectResourceDao;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.entity.SysCompanyEntity;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysCompanyService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.dao.ZdPublishDao;
import io.renren.modules.zd.dao.ZdSourceDao;
import io.renren.modules.zd.dao.ZdStockIncomeDao;
import io.renren.modules.zd.dao.ZdStockIncomeResourceDao;
import io.renren.modules.zd.entity.ZdPublishEntity;
import io.renren.modules.zd.entity.ZdStockEntity;
import io.renren.modules.zd.entity.ZdStockIncomeEntity;
import io.renren.modules.zd.entity.ZdStockIncomeResourceEntity;
import io.renren.modules.zd.form.*;
import io.renren.modules.zd.service.ZdPublishService;
import io.renren.modules.zd.service.ZdStockIncomeService;
import io.renren.modules.zd.service.ZdStockService;
import io.renren.modules.zd.vo.ZdStockIncomeVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Service("zdStockIncomeService")
public class ZdStockIncomeServiceImpl extends ServiceImpl<ZdStockIncomeDao, ZdStockIncomeEntity> implements ZdStockIncomeService {

    @Autowired
    private SysOrgDao sysOrgDao;
    @Autowired
    private ZdSourceDao zdSourceDao;
    @Autowired
    private ZdPublishDao zdPublishDao;
    @Autowired
    private SubjectResourceDao subjectResourceDao;
    @Autowired
    private ZdStockIncomeResourceDao zdStockIncomeResourceDao;
    @Autowired
    private ZdStockIncomeDao zdStockIncomeDao;
    @Autowired
    private ZdStockService zdStockService;
    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private ZdPublishService zdPublishService;
    @Autowired
    private SysSemesterService sysSemesterService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdStockIncomeEntity> page = this.selectPage(
                new Query<ZdStockIncomeEntity>(params).getPage(),
                new EntityWrapper<ZdStockIncomeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveIncome(ZdSourceIncomeForm zdSourceIncomeForm,SysUserEntity sysUserEntity) {
       SysCompanyEntity  sysCompanyEntity=sysCompanyService.selectById(zdSourceIncomeForm.getSupplierId());
       if(sysCompanyEntity==null)
       {
           throw new RRException("供应商未找到");
       }
        String timeInsecond=DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        ZdStockIncomeEntity zdStockIncomeEntity=new ZdStockIncomeEntity();
        zdStockIncomeEntity.setIncomeName("入库单"+timeInsecond);
        zdStockIncomeEntity.setIncomeSn("RK"+timeInsecond);
        zdStockIncomeEntity.setOrgCode(sysUserEntity.getOrgCode());
        zdStockIncomeEntity.setCompanyId(sysCompanyEntity.getId());
        zdStockIncomeEntity.setIncomeSn("RK"+timeInsecond);
        zdStockIncomeEntity.setSemesterCode(zdSourceIncomeForm.getSemesterCode());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,10);
        zdStockIncomeEntity.setLastIncomeTime(calendar.getTime());
        zdStockIncomeEntity.setStatus(Constant.STOCK_STATUS.AUDIT);
        zdStockIncomeDao.insert(zdStockIncomeEntity);
        for(ZdSourceResourceIncomeForm zdSourceResourceIncomeForm:zdSourceIncomeForm.getIncomeForms())
        {
            SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(zdSourceResourceIncomeForm.getResourceId());
            if(subjectResourceEntity!=null)
            {
                ZdStockIncomeResourceEntity zdStockIncomeResourceEntity=new ZdStockIncomeResourceEntity();
                zdStockIncomeResourceEntity.setIncomeNum(zdSourceResourceIncomeForm.getNum());
                zdStockIncomeResourceEntity.setResourceId(zdSourceResourceIncomeForm.getResourceId());
                zdStockIncomeResourceEntity.setIncomeId(zdStockIncomeEntity.getId());
                zdStockIncomeResourceEntity.setStatus(Constant.STOCK_STATUS.AUDIT);
                zdStockIncomeResourceEntity.setIncomePrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                zdStockIncomeResourceEntity.setRealNum(zdSourceResourceIncomeForm.getNum());
                zdStockIncomeResourceEntity.setNitemdiscountrate(DecimalUtils.DEFAULT_DISCOUNT);
                zdStockIncomeResourceEntity.setMayang(DecimalUtils.mayang(subjectResourceEntity.getPrice(),zdSourceResourceIncomeForm.getNum()));
                zdStockIncomeResourceEntity.setShiyang(DecimalUtils.shiyang(subjectResourceEntity.getPrice(),zdSourceResourceIncomeForm.getNum(),DecimalUtils.DEFAULT_DISCOUNT));
              //  zdStockIncomeResourceEntity.setSourceResourceId(zdSourceResourceIncomeForm.getSourceResourceId());
                zdStockIncomeResourceDao.insert(zdStockIncomeResourceEntity);

              /*  ZdStockEntity zdStockEntity=new ZdStockEntity();
                zdStockEntity.setResourceId(zdStockIncomeResourceEntity.getResourceId());
                zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
                zdStockEntity.setTotalAmount(zdStockIncomeResourceEntity.getIncomeNum());
                zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
                zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.INCOME);
              //  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
                zdStockService.saveStock(zdStockEntity,sysUserEntity.getOrgCode());*/
             /*   ZdStockEntity zdStockEntity=new ZdStockEntity();
                zdStockEntity.setResourceId(subjectResourceEntity.getId());
                zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
                zdStockEntity.setTotalAmount(zdStockIncomeResourceEntity.getIncomeNum());
                zdStockEntity.setCost(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
                zdStockService.saveStock(zdStockEntity,sysUserEntity);*/

            }

        }



    }

    @Override
    public PageUtils queryByOrg(Map<String, Object> params) {
        Page<ZdStockIncomeVO> page=new Query<ZdStockIncomeVO>(params).getPage();
        page.setRecords(zdStockIncomeDao.selectListPage(page,params));
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveIncomeResource(ZdIncomeForm zdIncomeForm, SysUserEntity sysUserEntity) {
        ZdStockIncomeEntity zdStockIncomeEntity=zdStockIncomeDao.selectById(zdIncomeForm.getIncomeId());
        if(Constant.STOCK_STATUS.SURE.equals(zdStockIncomeEntity.getStatus()))
        {
            throw new RRException("已确认过的单据不可重复确认");
        }
        if(Constant.STOCK_STATUS.SURE.equals(zdIncomeForm.getStatus()))
        {
            List<ZdStockIncomeResourceEntity> zdStockIncomeResourceEntityList=zdStockIncomeResourceDao.selectByIncomeId(zdIncomeForm.getIncomeId());
            for(ZdStockIncomeResourceEntity zdStockIncomeResourceEntity:zdStockIncomeResourceEntityList)
            {
                if(Constant.STOCK_STATUS.SURE.equals(zdStockIncomeResourceEntity.getStatus()))
                {
                    throw new RRException("已确认过的子单据不可重复确认");
                }
                zdStockIncomeResourceEntity.setRealNum(zdStockIncomeResourceEntity.getIncomeNum());
                zdStockIncomeResourceEntity.setMayang(DecimalUtils.mayang(zdStockIncomeResourceEntity.getIncomePrice(),zdStockIncomeResourceEntity.getRealNum()));
                if(zdStockIncomeResourceEntity.getNitemdiscountrate()==null){
                    zdStockIncomeResourceEntity.setNitemdiscountrate(new BigDecimal("100.00"));
                }
                zdStockIncomeResourceEntity.setShiyang(DecimalUtils.shiyang(zdStockIncomeResourceEntity.getIncomePrice(),zdStockIncomeResourceEntity.getRealNum(),zdStockIncomeResourceEntity.getNitemdiscountrate()));
                zdStockIncomeResourceEntity.setStatus(Constant.STOCK_STATUS.SURE);
                zdStockIncomeResourceDao.updateById(zdStockIncomeResourceEntity);
                ZdStockEntity zdStockEntity=new ZdStockEntity();
                zdStockEntity.setResourceId(zdStockIncomeResourceEntity.getResourceId());
                zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
                zdStockEntity.setTotalAmount(zdStockIncomeResourceEntity.getRealNum());
                zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
                zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.INCOME);
                zdStockService.saveStock(zdStockEntity,sysUserEntity.getOrgCode());
            }
            zdStockIncomeEntity.setIncomeTime(new Date());
            zdStockIncomeEntity.setStatus(Constant.STOCK_STATUS.SURE);
            zdStockIncomeDao.updateById(zdStockIncomeEntity);
        }else
        {
            for(ZdSourceIncomeResourceForm zdSourceIncomeResourceForm:zdIncomeForm.getList())
            {
                ZdStockIncomeResourceEntity zdStockIncomeResourceEntity=zdStockIncomeResourceDao.selectById(zdSourceIncomeResourceForm.getId());
                if(Constant.STOCK_STATUS.SURE.equals(zdStockIncomeResourceEntity.getStatus()))
                {
                    throw new RRException("已确认过的子单据不可重复确认");
                }

                zdStockIncomeResourceEntity.setRealNum(zdSourceIncomeResourceForm.getNum());
                zdStockIncomeResourceEntity.setMayang(DecimalUtils.mayang(zdStockIncomeResourceEntity.getIncomePrice(),zdStockIncomeResourceEntity.getRealNum()));
                zdStockIncomeResourceEntity.setShiyang(DecimalUtils.shiyang(zdStockIncomeResourceEntity.getIncomePrice(),zdStockIncomeResourceEntity.getRealNum(),zdStockIncomeResourceEntity.getNitemdiscountrate()));
                zdStockIncomeResourceEntity.setStatus(Constant.STOCK_STATUS.SURE);
                zdStockIncomeResourceDao.updateById(zdStockIncomeResourceEntity);
                ZdStockEntity zdStockEntity=new ZdStockEntity();
                zdStockEntity.setResourceId(zdStockIncomeResourceEntity.getResourceId());
                zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
                zdStockEntity.setTotalAmount(zdStockIncomeResourceEntity.getRealNum());
                zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
                zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.INCOME);
                zdStockService.saveStock(zdStockEntity,sysUserEntity.getOrgCode());
            }
            Map<String,Object> queryParams=new HashMap<>();
            queryParams.put("incomeId",zdStockIncomeEntity.getId());
            queryParams.put("status",Constant.STOCK_STATUS.AUDIT);
            int count=zdStockIncomeResourceDao.countByMap(queryParams);
            if(count==0)
            {
                zdStockIncomeEntity.setIncomeTime(new Date());
                zdStockIncomeEntity.setStatus(Constant.STOCK_STATUS.SURE);
                zdStockIncomeDao.updateById(zdStockIncomeEntity);
            }
        }

        /*if("1".equals(zdIncomeForm.getStatus()))
        {
            ZdStockIncomeEntity zdStockIncomeEntity=zdStockIncomeDao.selectById(zdIncomeForm.getIncomeId());
            if(Constant.STOCK_STATUS.SURE.equals(zdStockIncomeEntity.getStatus()))
            {
                throw new RRException("已确认过的子单据不可重复确认");
            }

            List<ZdStockIncomeResourceEntity> list=zdStockIncomeResourceDao.selectByIncomeId(zdStockIncomeEntity.getId());
            for(ZdStockIncomeResourceEntity zdStockIncomeResourceEntity:list)
            {
               if(!"1".equals(zdStockIncomeEntity.getStatus()))
                {
                    zdStockIncomeResourceEntity.setRealNum(zdStockIncomeResourceEntity.getIncomeNum());
                    zdStockIncomeResourceEntity.setStatus("1");
                    zdStockIncomeResourceDao.updateById(zdStockIncomeResourceEntity);
                    ZdStockEntity zdStockEntity=new ZdStockEntity();
                    zdStockEntity.setResourceId(zdStockIncomeResourceEntity.getResourceId());
                    zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
                    zdStockEntity.setTotalAmount(zdStockIncomeResourceEntity.getRealNum());
                    zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
                    zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.INCOME);
                    zdStockService.saveStock(zdStockEntity,sysUserEntity.getOrgCode());
                }


            }
            zdStockIncomeEntity.setStatus("1");
            zdStockIncomeDao.updateById(zdStockIncomeEntity);
        }else
        {
            for(ZdSourceIncomeResourceForm zdSourceIncomeResourceForm:zdIncomeForm.getList())
            {
                ZdStockIncomeResourceEntity zdStockIncomeEntity=zdStockIncomeResourceDao.selectById(zdSourceIncomeResourceForm.getId());
                if("1".equals(zdStockIncomeEntity.getStatus()))
                {
                    throw new RRException("已确认过的子单据不可重复确认");
                }
                zdStockIncomeEntity.setRealNum(zdSourceIncomeResourceForm.getNum());
                zdStockIncomeEntity.setStatus("1");
                zdStockIncomeResourceDao.updateById(zdStockIncomeEntity);
                ZdStockEntity zdStockEntity=new ZdStockEntity();
                zdStockEntity.setResourceId(zdStockIncomeEntity.getResourceId());
                zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
                zdStockEntity.setTotalAmount(zdSourceIncomeResourceForm.getNum());
                zdStockEntity.setCost(zdStockIncomeEntity.getIncomePrice());
                zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.INCOME);
                zdStockService.saveStock(zdStockEntity,sysUserEntity.getOrgCode());
            }
        }*/

    }

    @Override
    public int countByMap(Map map) {
        return zdStockIncomeDao.countByMap(map);
    }

    @Override
    @Transactional
    public void saveIncomeByNC(ZdSourceIncomeForm zdSourceIncomeForm,SysOrgEntity sysOrgEntity) {
        SysCompanyEntity  sysCompanyEntity=sysCompanyService.selectById(zdSourceIncomeForm.getSupplierId());
        if(sysCompanyEntity==null)
        {
            throw new RRException("供应商未找到");
        }
        String timeInsecond=DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        ZdStockIncomeEntity zdStockIncomeEntity=zdStockIncomeDao.selectByForeignId(zdSourceIncomeForm.getForeignId());
        if(zdStockIncomeEntity==null)
        {
            zdStockIncomeEntity=new ZdStockIncomeEntity();
        }else if(Constant.STOCK_STATUS.SURE.equals(zdStockIncomeEntity.getStatus())||Constant.STOCK_STATUS.FINISH.equals(zdStockIncomeEntity.getStatus()))
        {
            throw new RRException("无法更新已确认的单据!");
        }
        zdStockIncomeEntity.setIncomeName("入库单"+timeInsecond);
        zdStockIncomeEntity.setIncomeSn("RK"+timeInsecond);
        zdStockIncomeEntity.setOrgCode(sysOrgEntity.getOrgCode());
        zdStockIncomeEntity.setCreateTime(new Date());
        zdStockIncomeEntity.setCreateBy("1");
        zdStockIncomeEntity.setCompanyId(sysCompanyEntity.getId());
        zdStockIncomeEntity.setIncomeSn("RK"+timeInsecond);
        zdStockIncomeEntity.setSemesterCode(zdSourceIncomeForm.getSemesterCode());
        zdStockIncomeEntity.setStatus(Constant.STOCK_STATUS.AUDIT);
        zdStockIncomeEntity.setToOrgCode(zdSourceIncomeForm.getStaplerbasis());
        zdStockIncomeEntity.setForeignId(zdSourceIncomeForm.getForeignId());
        zdStockIncomeEntity.setErpcode(zdSourceIncomeForm.getErpcode());
        if(StringUtils.isBlank(zdStockIncomeEntity.getId()))
        {
            zdStockIncomeDao.insert(zdStockIncomeEntity);
        }else
        {
            zdStockIncomeDao.updateById(zdStockIncomeEntity);
        }

        for(ZdSourceResourceIncomeForm zdSourceResourceIncomeForm:zdSourceIncomeForm.getIncomeForms())
        {
            SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectByCode(zdSourceResourceIncomeForm.getResourceCode());
            if(subjectResourceEntity!=null)
            {
                ZdStockIncomeResourceEntity zdStockIncomeResourceEntity=zdStockIncomeResourceDao.selectByForeignId(zdSourceResourceIncomeForm.getForeignId());
                if(zdStockIncomeResourceEntity==null)
                {
                    zdStockIncomeResourceEntity=new ZdStockIncomeResourceEntity();
                }
                zdStockIncomeResourceEntity.setIncomeNum(zdSourceResourceIncomeForm.getNum());
                zdStockIncomeResourceEntity.setResourceId(subjectResourceEntity.getId());
                zdStockIncomeResourceEntity.setIncomeId(zdStockIncomeEntity.getId());
                zdStockIncomeResourceEntity.setStatus(Constant.STOCK_STATUS.AUDIT);
                zdStockIncomeResourceEntity.setNitemdiscountrate(zdSourceResourceIncomeForm.getNitemdiscountrate());
                zdStockIncomeResourceEntity.setMayang(zdSourceResourceIncomeForm.getMayang());
                zdStockIncomeResourceEntity.setShiyang(zdSourceResourceIncomeForm.getShiyang());
                zdStockIncomeResourceEntity.setIncomePrice(zdSourceResourceIncomeForm.getIncomePrice());
                zdStockIncomeResourceEntity.setForeignId(zdSourceResourceIncomeForm.getForeignId());
                //  zdStockIncomeResourceEntity.setSourceResourceId(zdSourceResourceIncomeForm.getSourceResourceId());
                if(StringUtils.isBlank(zdStockIncomeResourceEntity.getId()))
                {
                    zdStockIncomeResourceDao.insert(zdStockIncomeResourceEntity);
                }else
                {
                    zdStockIncomeResourceDao.updateById(zdStockIncomeResourceEntity);
                }

                /*ZdStockEntity zdStockEntity=new ZdStockEntity();
                zdStockEntity.setResourceId(subjectResourceEntity.getId());
                zdStockEntity.setOrgCode(sysOrgEntity.getOrgCode());
                zdStockEntity.setTotalAmount(zdStockIncomeResourceEntity.getIncomeNum());
                zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
                zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.INCOME);*/
                //  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
               // zdStockService.saveStock(zdStockEntity,sysOrgEntity.getOrgCode());
             /*   ZdStockEntity zdStockEntity=new ZdStockEntity();
                zdStockEntity.setResourceId(subjectResourceEntity.getId());
                zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
                zdStockEntity.setTotalAmount(zdStockIncomeResourceEntity.getIncomeNum());
                zdStockEntity.setCost(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
                zdStockService.saveStock(zdStockEntity,sysUserEntity);*/

            }else
            {
                throw new RRException("图书未找到："+zdSourceResourceIncomeForm.getResourceCode());
            }

        }
    }

    @Override
    @Transactional
    public void delayIncome(ZdIncomeForm zdIncomeForm) {
      Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,10);
        ZdStockIncomeEntity zdStockIncomeEntity=  zdStockIncomeDao.selectById(zdIncomeForm.getIncomeId());
        zdStockIncomeEntity.setLastIncomeTime(calendar.getTime());
        zdStockIncomeDao.updateById(zdStockIncomeEntity);
    }

    @Override
    @Transactional
    public void  transforPublish(String id) {
        ZdStockIncomeEntity zdStockIncome=zdStockIncomeDao.selectById(id);
        if(zdStockIncome==null)
        {
            throw new RRException("未找到入库单");
        }
       /* if(!Constant.STOCK_STATUS.SURE.equals(zdStockIncome.getStatus()))
        {
            throw new RRException("状态不允许发行");
        }*/
        if(StringUtils.isBlank(zdStockIncome.getToOrgCode()))
        {
            throw new RRException("没有找到发行依据，不允许转发行");
        }
        SysOrgEntity sysOrgEntity=sysOrgDao.selectByOrgCode(zdStockIncome.getToOrgCode());
        if(sysOrgEntity==null)
        {
            throw new RRException("没有找到发行单位，不允许转发行");
        }
        ZdPublishEntity zdPublishEntity=zdPublishDao.selectBySource(Constant.PUBLISH_SOURCE_TYPE.INCOME,id);
        if(zdPublishEntity!=null)
        {
            throw new RRException("入库单只能被转换一次");
        }

        List<ZdStockIncomeResourceEntity> list=zdStockIncomeResourceDao.selectByIncomeId(id);
        if(list.size()<=0)
        {
            throw new RRException("没有找到入库详情单，不允许发行");
        }
        ZdPublishForm zdPublishForm=new ZdPublishForm();
        List<ZdPublishResourceForm> publishResourceEntities=new ArrayList<>();
//        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        zdPublishForm.setSemesterCode(zdStockIncome.getSemesterCode());
        zdPublishForm.setOrgCode(zdStockIncome.getToOrgCode());
        zdPublishForm.setSourceType(Constant.PUBLISH_SOURCE_TYPE.INCOME);
        zdPublishForm.setForeignId(id);
        for(ZdStockIncomeResourceEntity zdStockIncomeResourceEntity:list)
        {
            ZdPublishResourceForm zdPublishResourceForm=new ZdPublishResourceForm();
//            zdPublishResourceForm.setNitemdiscountrate(new BigDecimal(1));
            zdPublishResourceForm.setNum(zdStockIncomeResourceEntity.getIncomeNum());
            zdPublishResourceForm.setResourceId(zdStockIncomeResourceEntity.getResourceId());
            publishResourceEntities.add(zdPublishResourceForm);
        }
        zdPublishForm.setResourceForm(publishResourceEntities);
        zdPublishService.transforPublish(zdPublishForm);

        zdStockIncome.setStatus(Constant.STOCK_STATUS.SURE);
        baseMapper.updateById(zdStockIncome);
    }


}
