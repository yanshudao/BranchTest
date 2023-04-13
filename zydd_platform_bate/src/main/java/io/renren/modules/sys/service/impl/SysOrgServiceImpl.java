package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.subject.entity.request.Customer;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.dao.SysOrgSettingDao;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.OrgCodeUpdateForm;
import io.renren.modules.sys.form.OrgForm;
import io.renren.modules.sys.service.SysOrgAddressService;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdOrderEntity;
import io.renren.modules.zd.entity.ZdSourceEntity;
import io.renren.modules.zd.service.*;
import io.renren.modules.zd.vo.*;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service("sysOrgService")
public class SysOrgServiceImpl extends ServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {
    @Autowired
    private SysOrgDao sysOrgDao;

    @Autowired
    private SysOrgSettingDao sysOrgSettingDao;
    @Autowired
    private SysOrgSettingService sysOrgSettingService;
    @Autowired
    private SysOrgAddressService sysOrgAddressService;

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private ZdMajorCourseOrgService zdMajorCourseOrgService;
    @Autowired
    private ZdMajorOrgService zdMajorOrgService;
    @Autowired
    private ZdMajorOrgDisableService zdMajorOrgDisableService;

    @Autowired
    private SysSemesterService sysSemesterService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysOrgEntity> page = this.selectPage(
                new Query<SysOrgEntity>(params).getPage(),
                new EntityWrapper<SysOrgEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = Constant.CACHE_NAMESPACE + "GET_PARENT_ORG", key = "'GET_PARENT_ORG:'+#p0", unless = "#result==null")
    public SysOrgEntity selectParentByOrgCode(String orgCode) {
        return sysOrgDao.selectParentByOrgCode(orgCode);
    }

    @Override
    @Cacheable(value = Constant.CACHE_NAMESPACE + "GET_ORG", key = "'GET_ORG:'+#p0", unless = "#result==null")
    public SysOrgEntity selectByOrgCode(String orgCode) {
        return sysOrgDao.selectByOrgCode(orgCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {Constant.CACHE_NAMESPACE + "GET_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "LIST_ORG_TYPE",
            Constant.CACHE_NAMESPACE + "TREE_LIST",
            Constant.CACHE_NAMESPACE + "LIST_CHILDREN_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PROVINCE",
            Constant.CACHE_NAMESPACE + "ZYDD_LIST_ORG",
            Constant.CACHE_NAMESPACE + "GET_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG"}, allEntries = true)

    public void saveOrg(OrgForm sysOrg) {
        SysOrgEntity sysOrgEntity = new SysOrgEntity();
        sysOrgEntity.setOrgCode(sysOrg.getOrgCode());
        sysOrgEntity.setOrgName(sysOrg.getOrgName());
        sysOrgEntity.setOrgType(sysOrg.getOrgType());
        sysOrgEntity.setAreaCode(sysOrg.getAreaCode());
        sysOrgEntity.setParentId(sysOrg.getParentId());
        if (StringUtils.isBlank(sysOrg.getParentId())) {
            sysOrg.setParentId("1");
        }
        SysOrgEntity orgEntity = sysOrgDao.selectById(sysOrg.getParentId());
        if (orgEntity != null) {
            sysOrgEntity.setParentCodes(orgEntity.getParentCodes() + "," + orgEntity.getOrgCode());
            sysOrgEntity.setParentIds(orgEntity.getParentIds() + "," + orgEntity.getId());
            sysOrgEntity.setFullCodes(orgEntity.getParentCodes() + "," + orgEntity.getOrgCode() + "," + sysOrg.getOrgCode());
        }
        //  sysOrgEntity.setParentIds(sysOrg.getParentIds());
        //  sysOrgEntity.setParentCodes(sysOrg.getParentCodes());
        sysOrgDao.insert(sysOrgEntity);
        SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingDao.selectByOrg(sysOrg.getOrgCode());
        if (sysOrgSettingEntity != null && sysOrgSettingEntity.getId() != null) {
            BeanUtils.copyProperties(sysOrg, sysOrgSettingEntity);
            SysOrgEntity toOrg = selectById(sysOrg.getToOrgId());
            if (toOrg != null) {
                sysOrgSettingEntity.setToOrgCode(sysOrg.getOrgCode());
            }
            sysOrgSettingDao.updateById(sysOrgSettingEntity);
        } else {
            sysOrgSettingEntity = new SysOrgSettingEntity();
            BeanUtils.copyProperties(sysOrg, sysOrgSettingEntity);
            SysOrgEntity toOrg = selectById(sysOrg.getToOrgId());
            if (toOrg != null) {
                sysOrgSettingEntity.setToOrgCode(sysOrg.getOrgCode());
            }
            sysOrgSettingDao.insert(sysOrgSettingEntity);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {Constant.CACHE_NAMESPACE + "GET_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "LIST_CHILDREN_ORG",
            Constant.CACHE_NAMESPACE + "TREE_LIST",
            Constant.CACHE_NAMESPACE + "LIST_ORG_TYPE",
            Constant.CACHE_NAMESPACE + "LIST_PROVINCE",
            Constant.CACHE_NAMESPACE + "GET_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "ZYDD_LIST_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG"}, allEntries = true)
    public void updateOrg(OrgForm sysOrg) {
        SysOrgEntity sysOrgEntity = sysOrgDao.selectByOrgCode(sysOrg.getOrgCode());
        BeanUtils.copyProperties(sysOrg, sysOrgEntity);
        if (StringUtils.isNotBlank(sysOrg.getParentId())) {
            SysOrgEntity orgEntity = sysOrgDao.selectById(sysOrg.getParentId());
            if (orgEntity != null) {
                sysOrgEntity.setParentCodes(orgEntity.getParentCodes() + "," + orgEntity.getOrgCode());
                sysOrgEntity.setParentIds(orgEntity.getParentIds() + "," + orgEntity.getId());
                sysOrgEntity.setFullCodes(orgEntity.getParentCodes() + "," + orgEntity.getOrgCode() + "," + sysOrg.getOrgCode());
            }
        }
        sysOrgDao.updateById(sysOrgEntity);
        SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingDao.selectByOrg(sysOrg.getOrgCode());
        if (sysOrgSettingEntity == null) {
            sysOrgSettingEntity = new SysOrgSettingEntity();
            sysOrgSettingEntity.setOrgCode(sysOrg.getOrgCode());
        }
        if (StringUtils.isNotBlank(sysOrg.getAddress())) {
            sysOrgSettingEntity.setAddress(sysOrg.getAddress());
        }
        sysOrgSettingEntity.setCustCode(sysOrg.getCustCode());
        sysOrgSettingEntity.setSalesmancode(sysOrg.getSalesmancode());
        sysOrgSettingEntity.setUsercode(sysOrg.getUsercode());
        if (StringUtils.isNotBlank(sysOrg.getTelPhone())) {
            sysOrgSettingEntity.setTelPhone(sysOrg.getTelPhone());
        }
        if (StringUtils.isNotBlank(sysOrg.getZipCode())) {
            sysOrgSettingEntity.setZipCode(sysOrg.getZipCode());
        }
        if (StringUtils.isNotBlank(sysOrg.getPerson())) {
            sysOrgSettingEntity.setPerson(sysOrg.getPerson());
        }
        if (StringUtils.isNotBlank(sysOrg.getOrderType())) {
            sysOrgSettingEntity.setOrderType(sysOrg.getOrderType());
        }
        if (StringUtils.isNotBlank(sysOrg.getToOrgCode())) {
            sysOrgSettingEntity.setToOrgCode(sysOrg.getToOrgCode());
        }
        if (StringUtils.isNotBlank(sysOrg.getPayOrgCode())) {
            sysOrgSettingEntity.setPayOrgCode(sysOrg.getPayOrgCode());
        }
        if (sysOrg.getPayDiscount() != null) {
            sysOrgSettingEntity.setPayDiscount(sysOrg.getPayDiscount());
        }
        sysOrgSettingEntity.setIsConfigRate1(sysOrg.getIsConfigRate1());
        sysOrgSettingEntity.setIsConfigRate2(sysOrg.getIsConfigRate2());
        sysOrgSettingEntity.setIsConfigRate3(sysOrg.getIsConfigRate3());
        SysOrgEntity toOrg = selectById(sysOrg.getToOrgId());
        if (toOrg != null) {
            sysOrgSettingEntity.setToOrgCode(toOrg.getOrgCode());
        }
        if (StringUtils.isNotBlank(sysOrgSettingEntity.getId())) {
            sysOrgSettingDao.updateAllColumnById(sysOrgSettingEntity);
        } else {
            sysOrgSettingDao.insert(sysOrgSettingEntity);
        }

    }

    @Override
    @Cacheable(value = Constant.CACHE_NAMESPACE + "LIST_CHILDREN_ORG", key = "'LIST_CHILDREN_ORG:'+#params[orgCode]")
    public List selectChildren(Map params) {
        return sysOrgDao.selectChildren(params);
    }

    @Override
    @Cacheable(value = Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG", key = "'LIST_PARENT_ORG:'+#params[orgCode]")
    public List selectParent(Map<String, Object> params) {
        return sysOrgDao.selectParent(params);
    }

    @Override
    @CacheEvict(value = {
            Constant.CACHE_NAMESPACE + "GET_ORG",
            Constant.CACHE_NAMESPACE + "ZYDD_LIST_ORG",
            Constant.CACHE_NAMESPACE + "LIST_ORG_TYPE",
            Constant.CACHE_NAMESPACE + "TREE_LIST",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PROVINCE",
            Constant.CACHE_NAMESPACE + "LIST_CHILDREN_ORG",
            Constant.CACHE_NAMESPACE + "GET_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG"}, allEntries = true)
    @Transactional
    public void saveOrUpdateCustomer(Customer customer) {
        List<SysOrgEntity> sysOrgEntityList = sysOrgDao.selectByCustomCode(customer.getCustcode());
        if (sysOrgEntityList.size() > 1) {
            for (SysOrgEntity sysOrgEntity : sysOrgEntityList) {
                //更新关联客商属性 因为县级会携带省级客商代码
                SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingDao.selectByOrg(sysOrgEntity.getOrgCode());
                if (sysOrgSettingEntity == null) {
                    sysOrgSettingEntity = new SysOrgSettingEntity();
                    sysOrgSettingEntity.setOrgCode(sysOrgEntity.getOrgCode());
                }
                sysOrgSettingEntity.setPayDiscount(BigDecimal.valueOf(customer.getDiscountrate()));
                sysOrgSettingEntity.setUsercode(customer.getUsercode());
                sysOrgSettingEntity.setSalesmancode(customer.getSalesmancode());
                sysOrgSettingService.insertOrUpdate(sysOrgSettingEntity);
                //只修改省级
                if (Constant.ORG_TYPE.SHENG.equals(sysOrgEntity.getOrgType())) {
                    sysOrgEntity.setUpdateTime(new Date());
                    sysOrgEntity.setOrgName(customer.getCustname());
                    sysOrgDao.updateById(sysOrgEntity);
                }

            }

        } else if (sysOrgEntityList.size() == 1) {
            SysOrgEntity sysOrgEntity = sysOrgEntityList.get(0);
            SysOrgEntity update = new SysOrgEntity();
            update.setId(sysOrgEntity.getId());
            update.setUpdateTime(new Date());
            update.setOrgName(customer.getCustname());
            sysOrgDao.updateById(update);
            SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingDao.selectByOrg(sysOrgEntity.getOrgCode());
            if (sysOrgSettingEntity != null && sysOrgSettingEntity.getId() != null) {
//            CustomerChildren customerChildren=customer.getChildren();
//            sysOrgSettingEntity.setAddress();
                sysOrgSettingEntity.setPayDiscount(BigDecimal.valueOf(customer.getDiscountrate()));
                sysOrgSettingEntity.setCustCode(customer.getCustcode());
                sysOrgSettingEntity.setUsercode(customer.getUsercode());
                sysOrgSettingEntity.setSalesmancode(customer.getSalesmancode());
                sysOrgSettingDao.updateById(sysOrgSettingEntity);
            } else {
                sysOrgSettingEntity = new SysOrgSettingEntity();
                sysOrgSettingEntity.setCustCode(customer.getCustcode());
                sysOrgSettingEntity.setOrgCode(sysOrgEntity.getOrgCode());
                sysOrgSettingEntity.setPayDiscount(BigDecimal.valueOf(customer.getDiscountrate()));
                sysOrgSettingEntity.setUsercode(customer.getUsercode());
                sysOrgSettingEntity.setSalesmancode(customer.getSalesmancode());
                sysOrgSettingDao.insert(sysOrgSettingEntity);
            }
        } else {
            //如果不存在新建 客商代码 为 单位代码
            SysOrgEntity sysOrgEntity = sysOrgDao.selectByOrgCode(customer.getCustcode());
            String tempCode = "CODE" + System.currentTimeMillis();
            if (sysOrgEntity != null) {
                sysOrgEntity.setOrgCode(tempCode);
            } else {
                //客商代码不存在，单位代码存在
                sysOrgEntity = new SysOrgEntity();
                sysOrgEntity.setOrgCode(customer.getCustcode());
            }
            sysOrgEntity.setId(null);
            sysOrgEntity.setParentId("1");
            sysOrgEntity.setCreateTime(new Date());
            sysOrgEntity.setOrgName(customer.getCustname());
            SysOrgEntity orgEntity = sysOrgDao.selectById(sysOrgEntity.getParentId());
            if (orgEntity != null) {
                sysOrgEntity.setParentCodes(orgEntity.getParentCodes() + "," + orgEntity.getOrgCode());
                sysOrgEntity.setParentIds(orgEntity.getParentIds() + "," + orgEntity.getId());
                sysOrgEntity.setFullCodes(orgEntity.getParentCodes() + "," + orgEntity.getOrgCode() + "," + sysOrgEntity.getOrgCode());
            }
            //  sysOrgEntity.setParentIds(sysOrg.getParentIds());
            //  sysOrgEntity.setParentCodes(sysOrg.getParentCodes());
            sysOrgDao.insert(sysOrgEntity);
            SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingDao.selectByOrg(sysOrgEntity.getOrgCode());
            if(sysOrgSettingEntity==null){
                sysOrgSettingEntity=new SysOrgSettingEntity();
            }
            sysOrgSettingEntity.setCustCode(sysOrgEntity.getOrgCode());
            sysOrgSettingEntity.setOrgCode(sysOrgEntity.getOrgCode());
            sysOrgSettingEntity.setPayDiscount(BigDecimal.valueOf(customer.getDiscountrate()));
            sysOrgSettingEntity.setUsercode(customer.getUsercode());
            sysOrgSettingEntity.setSalesmancode(customer.getSalesmancode());
            if(StringUtils.isBlank(sysOrgSettingEntity.getId())){
                sysOrgSettingDao.insert(sysOrgSettingEntity);
            }else{
                sysOrgSettingDao.updateById(sysOrgSettingEntity);
            }

           /* if (StringUtils.isNotBlank(sysOrgEntity.getId())) {
                sysOrgDao.updateById(sysOrgEntity);
            } else {

            }
            SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingDao.selectByOrg(sysOrgEntity.getOrgCode());
            if (sysOrgSettingEntity != null && sysOrgSettingEntity.getId() != null) {
//            CustomerChildren customerChildren=customer.getChildren();
//            sysOrgSettingEntity.setAddress();
                sysOrgSettingEntity.setPayDiscount(BigDecimal.valueOf(customer.getDiscountrate()));
                sysOrgSettingEntity.setCustCode(customer.getCustcode());
                sysOrgSettingEntity.setUsercode(customer.getUsercode());
                sysOrgSettingEntity.setSalesmancode(customer.getSalesmancode());
                sysOrgSettingDao.updateById(sysOrgSettingEntity);
            } else {

            }*/
        }
        //更新主客商属性
        SysUserEntity sysUserEntity = sysUserDao.queryByUserName(customer.getSalesmancode());
        if (sysUserEntity == null) {
            sysUserEntity = new SysUserEntity();
            sysUserEntity.setUsername(customer.getSalesmancode());
            String salt = RandomStringUtils.randomAlphanumeric(20);
            sysUserEntity.setPassword(new Sha256Hash(customer.getSalesmancode(), salt).toHex());
            sysUserEntity.setSalt(salt);
            sysUserEntity.setRealname(customer.getSalesmanname());
            sysUserEntity.setOrgCode("ZYDD");
            sysUserEntity.setStatus(1);
            sysUserDao.insert(sysUserEntity);
        }
        //保存客商地址
        /*List<CustomerAddress> addresses=customer.getChildren().getAddr();
        for(CustomerAddress address:addresses){
            SysOrgAddressEntity sysOrgAddressEntity=sysOrgAddressService.selectById(address.getId());
            if(sysOrgAddressEntity==null){
                 sysOrgAddressEntity=new SysOrgAddressEntity(address.getId(),sysOrgEntity.getOrgCode(),address.getVcustomer(),address.getLinkman(),address.getAddrname(),address.getBareaclcode(),address.getBareaclname(),address.getPhone(),address.getVyb());
                sysOrgAddressService.insert(sysOrgAddressEntity);
            }else{
                sysOrgAddressEntity=new SysOrgAddressEntity(address.getId(),sysOrgEntity.getOrgCode(),address.getVcustomer(),address.getLinkman(),address.getAddrname(),address.getBareaclcode(),address.getBareaclname(),address.getPhone(),address.getVyb());
                sysOrgAddressService.updateById(sysOrgAddressEntity);
            }
        }*/

    }

    @Override
    public List<SysOrgEntity> selectByCustomCode(String customerCode) {
        return sysOrgDao.selectByCustomCode(customerCode);
    }

    /**
     * 中央电大使用 获取结算单位列表
     *
     * @param params
     * @return
     */
    @Override
    public List<RefundOrgCountVO> listAll(Map<String, Object> params) {
        return sysOrgDao.listAll(params);
    }

    @Override
    public List<ZdOrderOrgVO> listOrderOrg(Map<String, Object> params) {
        return sysOrgDao.listOrderOrg(params);
    }

    @Override
    public List<StatisticProvinceTOPVO> queryStatisticsTOPByMap(Map<String, Object> params) {
        return sysOrgDao.selectStatisticsTOPByMap(params);
    }


    @Override
    public List<StatisticProvinceBalanceVO> queryStatisticsBalanceByMap(Map<String, Object> params) {
        return sysOrgDao.selectStatisticsBalanceByMap(params);
    }

    @Override
    public PageUtils queryStatisticsPage(Map<String, Object> params) {
        Page<StatisticProvinceTOPVO> page = new Query<StatisticProvinceTOPVO>(params).getPage();
        page.setRecords(sysOrgDao.selectStatisticsTOPByMap(page, params));
        return new PageUtils(page);
    }

    @Override
    public List<StatisticProvinceTOPVO> queryStatisticsCountryTOPByMap(Map<String, Object> params) {
        return sysOrgDao.selectStatisticsCountryTOPByMap(params);
    }

    @Override
    public PageUtils queryStatisticsCountryPage(Map<String, Object> params) {
        Page<StatisticProvinceTOPVO> page = new Query<StatisticProvinceTOPVO>(params).getPage();
        page.setRecords(sysOrgDao.selectStatisticsCountryTOPByMap(page, params));
        return new PageUtils(page);
    }

    @Override
    public List<StatisticProvinceBalanceVO> queryStatisticsBalanceCountryByMap(Map<String, Object> params) {
        return sysOrgDao.selectStatisticsBalanceCountryByMap(params);
    }

    @Override
    public List<OrderOrgCount> queryOrderList(Map<String, Object> params) {
        return sysOrgDao.selectOrderList(params);
    }

    @Override
    public List<OrderOrgCount> queryNotOrderList(Map<String, Object> params) {
        return baseMapper.selectNotOrderList(params);
    }

    @Override
    public List<StatisticProvinceTOPVO> queryAllStatisticsCountry(Map<String, Object> params) {
        return sysOrgDao.selectStatisticsCountryTOPByMap(params);
    }

    @Override
    @CacheEvict(value = {Constant.CACHE_NAMESPACE + "GET_ORG",
//            Constant.CACHE_NAMESPACE + "ZYDD_LIST_ORG",
            Constant.CACHE_NAMESPACE + "LIST_ORG_TYPE",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "TREE_LIST",
            Constant.CACHE_NAMESPACE + "LIST_CHILDREN_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PROVINCE",
            Constant.CACHE_NAMESPACE + "GET_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG"}, allEntries = true)
    @Transactional
    public boolean deleteOrg(List ids) {
        List<SysOrgEntity> orgList = selectBatchIds(ids);
        if (orgList.size() <= 0) {
            throw new RRException("未找到单位信息！");
        }
        orgList.stream().forEach(item -> {
            List<SysOrgEntity> childOrg = selectList(new EntityWrapper<SysOrgEntity>()
                    .eq("parent_id", item.getId()));
            if (childOrg.size() > 0) {
                List<String> childList = childOrg.stream().map(SysOrgEntity::getId).collect(Collectors.toList());
                List<String> orgCodeList = childOrg.stream().map(SysOrgEntity::getOrgCode).collect(Collectors.toList());
                super.deleteBatchIds(childList);
                sysOrgSettingDao.delete(new EntityWrapper<SysOrgSettingEntity>().in("org_code", orgCodeList));
            }

        });
        return super.deleteBatchIds(ids);
    }

    @Override
//    @Cacheable(value = Constant.CACHE_NAMESPACE + "LIST_ORG_TYPE", key = "'LIST_ORG_TYPE:'+#p0")
    public List selectByType(String orgType) {
        return baseMapper.selectByType(orgType);
    }

    @Override
    @Cacheable(value = Constant.CACHE_NAMESPACE + "LIST_PROVINCE", unless = "#result==null")
    public List<SysOrgEntity> listProvince() {
        SysOrgEntity zydd = selectByOrgCode("ZYDD");
        Map map = new HashMap();
//        map.put("parentId", zydd.getId());
//        map.put("orgType", Constant.ORG_TYPE.SHENG);
        map.put("orgTypeList", Lists.newArrayList(Constant.ORG_TYPE.SHENG,Constant.ORG_TYPE.SHI));
        List list = baseMapper.selectChildren(map);
        list.add(zydd);
        return list;
    }


    @Override
    @CacheEvict(value = {Constant.CACHE_NAMESPACE + "GET_ORG",
//            Constant.CACHE_NAMESPACE + "ZYDD_LIST_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "TREE_LIST",
            Constant.CACHE_NAMESPACE + "LIST_ORG_TYPE",
            Constant.CACHE_NAMESPACE + "LIST_CHILDREN_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PROVINCE",
            Constant.CACHE_NAMESPACE + "GET_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG"}, allEntries = true)
    @Transactional
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        return super.deleteBatchIds(idList);
    }

    @Override
    public int syncSemesterCourse(String semesterCode, String orgCode) {
        String preSemesterCode="";
        //获得上个学期报订季节code
        if(semesterCode.contains("09")){
            preSemesterCode=semesterCode.replace("09","03");
        }else{
            Integer semester=Integer.valueOf(semesterCode.replace("03",""));
            Integer preSemester=semester-1;
            preSemesterCode=preSemester+"09";
        }
       /* int count=zdMajorCourseOrgService.selectCount(new EntityWrapper<ZdMajorCourseOrg>().eq("org_code",orgCode).eq("semester_code",preSemesterCode));
        if(count<=0){
            return R.error("同步失败，未查找到上学期的开设数据");
        }*/
        //开设本学期所有专业的报订规则
        SysOrgEntity sysOrgEntity = sysOrgDao.selectByOrgCode(orgCode);
        Map<String, Object> insertMap = new HashMap<>();
        insertMap.put("orgCode", sysOrgEntity.getOrgCode());
        insertMap.put("parentCodes", sysOrgEntity.getParentCodes());
        insertMap.put("semesterCode", semesterCode);
        insertMap.put("preSemesterCode",preSemesterCode);
        int resultCount = zdMajorOrgService.selectInsert(insertMap);
        //同步上学期屏蔽的专业到本学期
        int disableCount = zdMajorOrgDisableService.selectInsert(insertMap);
        //同步上学期屏蔽的课程到本学期
        int courseCount = zdMajorCourseOrgService.selectInsert(insertMap);
        return resultCount;
    }

    @Autowired
    private ZdOrderService zdOrderService;
    @Autowired
    private ZdSourceService zdSourceService;

    @Override
    @Transactional
    @CacheEvict(value = {Constant.CACHE_NAMESPACE + "GET_ORG",
//            Constant.CACHE_NAMESPACE + "ZYDD_LIST_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "LIST_ORG_TYPE",
            Constant.CACHE_NAMESPACE + "LIST_CHILDREN_ORG",
            Constant.CACHE_NAMESPACE + "TREE_LIST",
            Constant.CACHE_NAMESPACE + "LIST_PROVINCE",
            Constant.CACHE_NAMESPACE + "GET_PARENT_ORG",
            Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG"}, allEntries = true)
    public R updateOrgCode(OrgCodeUpdateForm sysOrg) {
        if ("ZYDD".equals(sysOrg.getOrgCode())) {
            return R.error("不允许修改中央电大单位代码！");
        }
        SysOrgEntity sysOrgEntity = selectById(sysOrg.getId());
        int orderCount = zdOrderService.selectCount(new EntityWrapper<ZdOrderEntity>().eq("to_org_code", sysOrgEntity.getOrgCode()).or().eq("from_org_code", sysOrgEntity.getOrgCode()));
        int sourceCount = zdSourceService.selectCount(new EntityWrapper<ZdSourceEntity>().eq("org_code", sysOrgEntity.getOrgCode()).or().eq("to_org_code", sysOrgEntity.getOrgCode()));
        if (orderCount > 0 || sourceCount > 0) {
            return R.error("已经有报订或者采购数据，不允许修改！");
        }
        int orgCount = selectCount(new EntityWrapper<SysOrgEntity>().eq("org_code", sysOrg.getOrgCode()));
        if (orgCount > 0) {
            return R.error("单位代码已存在！");
        }
        SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingDao.selectByOrg(sysOrgEntity.getOrgCode());
        sysOrgEntity.setOrgCode(sysOrg.getOrgCode());
        baseMapper.updateById(sysOrgEntity);
        if (sysOrgSettingEntity != null) {
            SysOrgSettingEntity update = new SysOrgSettingEntity();
            update.setOrgCode(sysOrgSettingEntity.getOrgCode());
            update.setId(sysOrgSettingEntity.getId());
            sysOrgSettingDao.updateById(update);
        }

        return R.ok("操作成功");
    }

    @Override
    @Cacheable(value = Constant.CACHE_NAMESPACE + "TREE_LIST", key = "'TREE_LIST:'+#p0")
    public List treeList(String pid) {
        return sysOrgDao.selectChildrenByPId(pid);
    }

    @Override
    public SysOrgEntity selectChildrenById(String id) {
        return sysOrgDao.selectChildrenById(id);
    }
}
