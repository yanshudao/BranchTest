package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.search.vo.StatisticsPublishResourceVO;
import io.renren.modules.subject.dao.SubjectResourceDao;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.dao.SysSemesterDao;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.dao.ZdOrderResourceDao;
import io.renren.modules.zd.dao.ZdPublishDao;
import io.renren.modules.zd.dao.ZdPublishResourceDao;
import io.renren.modules.zd.entity.ZdPublishEntity;
import io.renren.modules.zd.entity.ZdPublishResourceEntity;
import io.renren.modules.zd.entity.ZdStockEntity;
import io.renren.modules.zd.form.PublishConfirmForm;
import io.renren.modules.zd.form.ZdPublishForm;
import io.renren.modules.zd.form.ZdPublishResourceForm;
import io.renren.modules.zd.service.ZdPublishResourceService;
import io.renren.modules.zd.service.ZdPublishService;
import io.renren.modules.zd.service.ZdStockService;
import io.renren.modules.zd.vo.PublishResourceVO;
import io.renren.modules.zd.vo.ZdStockResourceVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("zdPublishService")
public class ZdPublishServiceImpl extends ServiceImpl<ZdPublishDao, ZdPublishEntity> implements ZdPublishService {

	private Logger logger = LoggerFactory.getLogger(ZdPublishServiceImpl.class);

	@Autowired
    ZdPublishDao zdPublishDao;
	
	@Autowired
    SysOrgDao sysOrgDao;
	
	@Autowired
    SubjectResourceDao subjectResourceDao;
	
	@Autowired
    ZdPublishResourceDao zdPublishResourceDao;
	@Autowired
    ZdPublishResourceService zdPublishResourceService;

	@Autowired
    ZdOrderResourceDao zdOrderResourceDao;
	
	@Autowired
    SysSemesterDao sysSemesterDao;
	@Autowired
	ZdStockService zdStockService;

	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdPublishEntity> page = this.selectPage(
                new Query<ZdPublishEntity>(params).getPage(),
                new EntityWrapper<ZdPublishEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public PageUtils queryByLimit(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List list = zdPublishDao.selectByLimit(params);
		PageUtils pageUtils = new PageUtils(list, 100, 10, 1);
		return pageUtils;
	}

	@Override
	public PageUtils queryOrg(Map<String, Object> params) {
		// TODO Auto-generated method stub
		int totalCount = -1;
		int pageSize = -1;
		int currPage = -1;
		List list = sysOrgDao.queryOrgByType(params);
		System.out.println("list大小:" + list.size());
		PageUtils pageUtils = new PageUtils(list, totalCount, pageSize, currPage);
		return pageUtils;
	}


	@Override
	public PageUtils queryPublishList(Map<String, Object> params) {
		Page page=new Query(params).getPage();
		page.setRecords(zdPublishDao.queryPublishList(page,params));
		return new PageUtils(page);
	/*	totalCount = zdPublishDao.queryPublishListCount(params);
		System.out.println("list大小:" + list.size());
		PageUtils pageUtils = new PageUtils(list, totalCount, pageSizeUtil, currPage);*/
	}

	@Override
	public List<PublishResourceVO> queryPublishListDetail(Map<String, Object> params) {

		return zdPublishResourceDao.queryPublishListDetail(params);
		// TODO Auto-generated method stub
		//int totalCount = -1;
		//int pageSizeUtil = -1;
		//int currPage = -1;
//		int pageNo = Integer.parseInt((String) params.get("pageNo"));
//		int pageSize =  Integer.parseInt((String) params.get("pageSize"));
//		pageNo = (pageNo - 1) * pageSize;
//		params.put("pageNo", pageNo);
//		params.put("pageSize", pageSize);
		/*List list = zdPublishResourceDao.queryPublishListDetail(params);
		//System.out.println("list大小:" + list.size());
		PageUtils pageUtils = new PageUtils(list, totalCount, pageSizeUtil, currPage);
		
		return pageUtils;*/
	}

	@Override
	public PageUtils querySemester(Map<String, Object> params) {
		// TODO Auto-generated method stub
		int totalCount = -1;
		int pageSizeUtil = -1;
		int currPage = -1;		
//		int pageNo = Integer.parseInt((String) params.get("pageNo"));
//		int pageSize =  Integer.parseInt((String) params.get("pageSize"));
//		pageNo = (pageNo - 1) * pageSize;
//		params.put("pageNo", pageNo);
//		params.put("pageSize", pageSize);
		List list = sysSemesterDao.querySemester(params);
		System.out.println("list大小:" + list.size());
		PageUtils pageUtils = new PageUtils(list, totalCount, pageSizeUtil, currPage);
		return pageUtils;
	}



	


	@Override
	@Transactional
	@SysLog("省级保存发行单")
	public void savePublish(ZdPublishForm zdPublishForm) {
		SysOrgEntity sysOrgEntity=sysOrgDao.selectByOrgCode(zdPublishForm.getOrgCode());
		SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
		//SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
		//SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(zdCourseForm.getOrgCode());
		ZdPublishEntity zdPublishEntity=new ZdPublishEntity();
		zdPublishEntity.setFromOrgCode(sysUserEntity.getOrgCode());
		zdPublishEntity.setToOrgCode(zdPublishForm.getOrgCode());
		String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
		zdPublishEntity.setPublishNo("FX"+timeInSecond);
		zdPublishEntity.setStatus(Constant.PUBLISH_STATUS.NEW);
		zdPublishEntity.setSemesterCode(zdPublishForm.getSemesterCode());
		zdPublishEntity.setLogisticBag(zdPublishForm.getLogisticBag());
		zdPublishEntity.setLogisticCompany(zdPublishForm.getLogisticCompany());
		zdPublishEntity.setLogisticNo(zdPublishForm.getLogisticNo());
		zdPublishEntity.setLogisticType(zdPublishForm.getLogisticType());
		zdPublishEntity.setLogisticAddress(zdPublishForm.getLogisticAddress());
		zdPublishEntity.setLogisticPerson(zdPublishForm.getLogisticPerson());
		zdPublishEntity.setLogisticTelphone(zdPublishForm.getLogisticTelphone());
		zdPublishEntity.setPublishName(sysOrgEntity.getOrgName()+timeInSecond+"发行单");
		zdPublishEntity.setSourceType(zdPublishForm.getSourceType());
		zdPublishEntity.setForeignId(zdPublishForm.getForeignId());
		zdPublishDao.insert(zdPublishEntity);
		//List<ZdPublishResourceEntity> list=new ArrayList<>();
		for(ZdPublishResourceForm zdPublishResourceForm:zdPublishForm.getResourceForm())
		{
			//String courseId=zdCourseDetailForm.getCourseId();
			SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(zdPublishResourceForm.getResourceId());
		 			if(subjectResourceEntity==null)
					{
						throw new RRException("教材未找到，请仔细查询教材信息!");
					}
			//	SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(courseResourceOrgVO.getId());
				ZdPublishResourceEntity zdPublishResourceEntity=new ZdPublishResourceEntity();
				zdPublishResourceEntity.setPublishId(zdPublishEntity.getId());
				zdPublishResourceEntity.setResourceId(subjectResourceEntity.getId());
				zdPublishResourceEntity.setPublishNum(zdPublishResourceForm.getNum());
				zdPublishResourceEntity.setPublishPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
				zdPublishResourceEntity.setRealNum(zdPublishResourceForm.getNum());
			    zdPublishResourceEntity.setMayng(BigDecimal.valueOf(subjectResourceEntity.getPrice()).multiply(BigDecimal.valueOf(zdPublishResourceForm.getNum())));
			    zdPublishResourceEntity.setShiyang(BigDecimal.valueOf(subjectResourceEntity.getPrice()).multiply(BigDecimal.valueOf(zdPublishResourceForm.getNum())));
			    zdPublishResourceEntity.setNitemdiscountrate(new BigDecimal("100.00"));
			    //				zdOrderResourceEntity.setOrderResourceNo("ORDERDETAIL"+timeInSecond);
//				zdOrderResourceEntity.setItemPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));

			zdPublishResourceDao.insert(zdPublishResourceEntity);
			ZdStockEntity zdStockEntity=new ZdStockEntity();
			zdStockEntity.setResourceId(zdPublishResourceEntity.getResourceId());
			zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
			zdStockEntity.setTotalAmount(-zdPublishResourceForm.getNum());
			zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.PUBLISH);
			//zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
			//  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
			zdStockService.saveStock(zdStockEntity,sysUserEntity.getOrgCode());
			//list.add(zdPublishResourceEntity);
		}

	}
	@Override
	@Transactional
	@SysLog("省级保存发行单")
	public void transforPublish(ZdPublishForm zdPublishForm) {
		SysOrgEntity sysOrgEntity=sysOrgDao.selectByOrgCode(zdPublishForm.getOrgCode());
		SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
		//SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
		//SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(zdCourseForm.getOrgCode());
		ZdPublishEntity zdPublishEntity=new ZdPublishEntity();
		zdPublishEntity.setFromOrgCode(sysUserEntity.getOrgCode());
		zdPublishEntity.setToOrgCode(zdPublishForm.getOrgCode());
		String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
		zdPublishEntity.setPublishNo("FX"+timeInSecond);
		zdPublishEntity.setStatus(Constant.PUBLISH_STATUS.COMPLETE);
		zdPublishEntity.setSemesterCode(zdPublishForm.getSemesterCode());
		zdPublishEntity.setLogisticBag(zdPublishForm.getLogisticBag());
		zdPublishEntity.setLogisticCompany(zdPublishForm.getLogisticCompany());
		zdPublishEntity.setLogisticNo(zdPublishForm.getLogisticNo());
		zdPublishEntity.setLogisticType(zdPublishForm.getLogisticType());
		zdPublishEntity.setLogisticAddress(zdPublishForm.getLogisticAddress());
		zdPublishEntity.setLogisticPerson(zdPublishForm.getLogisticPerson());
		zdPublishEntity.setLogisticTelphone(zdPublishForm.getLogisticTelphone());
		zdPublishEntity.setPublishName(sysOrgEntity.getOrgName()+timeInSecond+"发行单");
		zdPublishEntity.setSourceType(zdPublishForm.getSourceType());
		zdPublishEntity.setForeignId(zdPublishForm.getForeignId());
		zdPublishDao.insert(zdPublishEntity);
		//List<ZdPublishResourceEntity> list=new ArrayList<>();
		for(ZdPublishResourceForm zdPublishResourceForm:zdPublishForm.getResourceForm())
		{
			//String courseId=zdCourseDetailForm.getCourseId();
			SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(zdPublishResourceForm.getResourceId());
			if(subjectResourceEntity==null)
			{
				throw new RRException("教材未找到，请仔细查询教材信息!");
			}
			//	SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(courseResourceOrgVO.getId());
			ZdPublishResourceEntity zdPublishResourceEntity=new ZdPublishResourceEntity();
			zdPublishResourceEntity.setPublishId(zdPublishEntity.getId());
			zdPublishResourceEntity.setResourceId(subjectResourceEntity.getId());
			zdPublishResourceEntity.setPublishNum(zdPublishResourceForm.getNum());
			zdPublishResourceEntity.setPublishPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
			zdPublishResourceEntity.setRealNum(zdPublishResourceForm.getNum());
			if(subjectResourceEntity.getPrice()==null||zdPublishResourceForm.getNum()==null) {
				throw new RRException("教材"+subjectResourceEntity.getResourceCode()+":"+subjectResourceEntity.getResourceName()+"没有价格，或者数量异常");
			}
			zdPublishResourceEntity.setMayng(BigDecimal.valueOf(subjectResourceEntity.getPrice()).multiply(BigDecimal.valueOf(zdPublishResourceForm.getNum())));
			zdPublishResourceEntity.setShiyang(BigDecimal.valueOf(subjectResourceEntity.getPrice()).multiply(BigDecimal.valueOf(zdPublishResourceForm.getNum())));
			zdPublishResourceEntity.setNitemdiscountrate(new BigDecimal("100.00"));
			//				zdOrderResourceEntity.setOrderResourceNo("ORDERDETAIL"+timeInSecond);
//				zdOrderResourceEntity.setItemPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));

			zdPublishResourceDao.insert(zdPublishResourceEntity);
			//ZdStockEntity zdStockEntity=new ZdStockEntity();
			//zdStockEntity.setResourceId(zdPublishResourceEntity.getResourceId());
			//zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
			//zdStockEntity.setTotalAmount(-zdPublishResourceForm.getNum());
			//zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.PUBLISH);
			//zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
			///  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
			//zdStockService.saveStock(zdStockEntity,sysUserEntity.getOrgCode());
			//list.add(zdPublishResourceEntity);
		}

	}
	@Override
	public PageUtils queryOrderResourcePage(Map<String, Object> params) {
		Page<ZdStockResourceVO> page=new Query<ZdStockResourceVO>(params).getPage();
		page.setRecords(subjectResourceDao.selectPublishResourcePage(page,params));
		return new PageUtils(page);
	}

	@Override
	public PageUtils queryResourcePage(Map<String, Object> params) {
		Page<ZdStockResourceVO> page=new Query<ZdStockResourceVO>(params).getPage();
		page.setRecords(subjectResourceDao.selectStockResourcePage(page,params));
		return new PageUtils(page);
	}

	@Override
	public PageUtils queryStatisticsByMap(Map<String, Object> params) {
		Page<StatisticsPublishResourceVO> page=new Query<StatisticsPublishResourceVO>(params).getPage();
		page.setRecords(zdPublishDao.selectStatisticsByMap(page,params));
		return new PageUtils(page);
	}

	@Override
	public List<StatisticsPublishResourceVO> queryAllStatisticsByMap(Map<String, Object> params) {
		return zdPublishDao.selectStatisticsByMap(params);
	}

	@Override
	public List<ZdStockResourceVO> queryOrderResourceAll(Map<String, Object> params) {
		return subjectResourceDao.selectPublishResourcePage(params);
	}

	@Override
	@Transactional
	public void modifyPublishResource(ZdPublishForm zdPublishForm) {
		ZdPublishEntity zdPublishEntity=zdPublishDao.selectById(zdPublishForm.getPublishId());
		if(Constant.PUBLISH_STATUS.FINISH.equals(zdPublishEntity.getStatus()))
		{
			throw new RRException("已结算的发行单不允许修改折扣");
		}
		List<ZdPublishResourceEntity> list=zdPublishResourceDao.selectByPublishId(zdPublishEntity.getId());
		for(ZdPublishResourceEntity zdPublishResourceEntity:list)
		{
			for(ZdPublishResourceForm zdPublishResourceForm:zdPublishForm.getResourceForm())
			{
				if(zdPublishResourceEntity.getId().equals(zdPublishResourceForm.getId()))
				{
					if(zdPublishResourceForm.getNitemdiscountrate().compareTo(new BigDecimal("0.00"))>0)
					{
						BigDecimal nitemdiscountrate=zdPublishResourceForm.getNitemdiscountrate();
						zdPublishResourceEntity.setNitemdiscountrate(nitemdiscountrate);
						zdPublishResourceEntity.setShiyang(DecimalUtils.shiyang(zdPublishResourceEntity.getPublishPrice(),zdPublishResourceEntity.getRealNum(),nitemdiscountrate));
						zdPublishResourceDao.updateById(zdPublishResourceEntity);
						break;
					}
				}

			}

		}

	}

	@Override
	@Transactional
	public void rejectPublish(ZdPublishForm zdPublishForm) {

		ZdPublishEntity zdPublishEntity=zdPublishDao.selectById(zdPublishForm.getPublishId());
		if(Constant.PUBLISH_STATUS.FINISH.equals(zdPublishEntity.getStatus())){
			throw new RRException("已结算的单据不允许退回");
		}
		List<ZdPublishResourceEntity> zdPublishResourceEntities=zdPublishResourceDao.selectByPublishId(zdPublishForm.getPublishId());
		if(zdPublishResourceEntities.size()<=0)
		{
			throw new RRException("无法查询到详情单");
		}
		for(ZdPublishResourceEntity zdPublishResourceEntity:zdPublishResourceEntities)
		{
			ZdStockEntity zdStockEntity=new ZdStockEntity();
			zdStockEntity.setResourceId(zdPublishResourceEntity.getResourceId());
			zdStockEntity.setOrgCode(zdPublishEntity.getOrgCode());
			zdStockEntity.setTotalAmount(zdPublishResourceEntity.getPublishNum());
			zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.PUBLISH_REJECT);
			//zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
			//  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
			zdStockService.saveStock(zdStockEntity,zdPublishEntity.getOrgCode());
		}
		zdPublishEntity.setStatus(Constant.PUBLISH_STATUS.NEW);
		zdPublishDao.updateById(zdPublishEntity);

    }

	@Override
	@Transactional
	public void deletePublish(ZdPublishForm zdPublishForm) {
		ZdPublishEntity zdPublishEntity=zdPublishDao.selectById(zdPublishForm.getPublishId());
		if(!Constant.PUBLISH_STATUS.NEW.equals(zdPublishEntity.getStatus())){
			throw new RRException("单据状态不允许删除");
		}
		zdPublishResourceDao.deleteByPublishId(zdPublishEntity.getId());
		zdPublishDao.deleteById(zdPublishEntity.getId());
	}

	@Override
	public PageUtils queryResourcePage1(Map<String, Object> params) {
		Page<ZdStockResourceVO> page=new Query<ZdStockResourceVO>(params).getPage();
		page.setRecords(subjectResourceDao.selectStockResourcePage1(page,params));
		return new PageUtils(page);
	}

	@Override
	public List<ZdStockResourceVO> queryOrderResourceAll1(Map<String, Object> params) {
		return subjectResourceDao.selectPublishResourcePage1(params);
	}

	@Override
	@Transactional
	public void confirmPublish(PublishConfirmForm publishConfirmForm) {
        boolean isModify=false;
    	ZdPublishEntity zdPublishEntity=baseMapper.selectById(publishConfirmForm.getPublishId());
    	if(Constant.PUBLISH_STATUS.FINISH.equals(zdPublishEntity.getStatus())||Constant.PUBLISH_STATUS.COMPLETE.equals(zdPublishEntity.getStatus())) {
    		throw new RRException("状态不允许确认！");
		}
    	for(ZdPublishResourceEntity zdPublishResourceEntity:publishConfirmForm.getResourceList()){
    		if(StringUtils.isBlank(zdPublishResourceEntity.getId())){
    			throw new RRException("参数错误");
			}
    		if(zdPublishResourceEntity.getRealNum()>zdPublishResourceEntity.getPublishNum()){
				throw new RRException("不允许大于发货数");
			}
    		if(zdPublishResourceEntity.getPublishNum()!=zdPublishResourceEntity.getRealNum()){
    		    isModify=true;
            }

		}
		zdPublishResourceService.updateBatchById(publishConfirmForm.getResourceList());
    	if(isModify){
            zdPublishEntity.setStatus(Constant.PUBLISH_STATUS.CONFIRM);
        }else{
            zdPublishEntity.setStatus(Constant.PUBLISH_STATUS.COMPLETE);
        }

		baseMapper.updateById(zdPublishEntity);
	}

    @Override
    @Transactional
    public void audit(List<String> ids) {
       SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        for(String id:ids){
            ZdPublishEntity zdPublishEntity=selectById(id);
            if(!Constant.PUBLISH_STATUS.CONFIRM.equals(zdPublishEntity.getStatus())){
                throw new RRException("状态不允许审核");
            }
            List<ZdPublishResourceEntity> list=zdPublishResourceDao.selectByPublishId(id);
            for(ZdPublishResourceEntity zdPublishResourceEntity:list){
                if(!zdPublishResourceEntity.getRealNum().equals(zdPublishResourceEntity.getPublishNum())){
                    Integer num=zdPublishResourceEntity.getPublishNum()-zdPublishResourceEntity.getRealNum();
                    if(num<=0){
                       continue;
                    }
                    //退回库存
                    ZdStockEntity zdStockEntity=new ZdStockEntity();
                    zdStockEntity.setResourceId(zdPublishResourceEntity.getResourceId());
                    zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
                    zdStockEntity.setTotalAmount(num);
                    zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.PUBLISH_REJECT);
                    //zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
                    //  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
                    zdStockService.saveStock(zdStockEntity,sysUserEntity.getOrgCode());
                }
            }
            zdPublishEntity.setStatus(Constant.PUBLISH_STATUS.COMPLETE);
            updateById(zdPublishEntity);
        }

//        zdStockService.saveStock();
    }

    @Override
    @Transactional
    public void reject(List<String> ids) {
        for(String id:ids){
            ZdPublishEntity zdPublishEntity=selectById(id);
            if(!Constant.PUBLISH_STATUS.CONFIRM.equals(zdPublishEntity.getStatus())){
                throw new RRException("状态不允许驳回");
            }
            zdPublishEntity.setStatus(Constant.PUBLISH_STATUS.REJECT);
            updateById(zdPublishEntity);
        }
    }


}
