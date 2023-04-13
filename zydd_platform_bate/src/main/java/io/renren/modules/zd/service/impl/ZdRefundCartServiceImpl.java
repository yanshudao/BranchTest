package io.renren.modules.zd.service.impl;

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
import io.renren.modules.sys.entity.SysCompanyEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.dao.ZdRefundCartDao;
import io.renren.modules.zd.entity.ZdRefundCartEntity;
import io.renren.modules.zd.entity.ZdRefundResourceEntity;
import io.renren.modules.zd.form.*;
import io.renren.modules.zd.service.ZdRefundCartService;
import io.renren.modules.zd.service.ZdRefundCountryService;
import io.renren.modules.zd.service.ZdRefundProvinceService;
import io.renren.modules.zd.vo.RefundLimitInfoVO;
import io.renren.modules.zd.vo.ZdRefundCartVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service("zdRefundCartService")
public class ZdRefundCartServiceImpl extends ServiceImpl<ZdRefundCartDao, ZdRefundCartEntity> implements ZdRefundCartService {
    @Resource
    private ZdRefundCountryService zdRefundCountryService;
    @Resource
    private SysSemesterService sysSemesterService;
    @Resource
    private SysOrgSettingService sysOrgSettingService;
    @Resource
    private ZdRefundProvinceService zdRefundProvinceService;
    @Resource
    private SubjectResourceService subjectResourceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdRefundCartEntity> page = this.selectPage(
                new Query<ZdRefundCartEntity>(params).getPage(),
                new EntityWrapper<ZdRefundCartEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    @Deprecated
    public void saveCartForm(ZdRefundCartFrom cartFrom) {
/*

        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgSettingEntity sysOrgSettingEntity= sysOrgSettingService.selectByOrg(sysUserEntity.getOrgCode());
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();
        for(ZdRefundCartResourceFrom zdRefundCartResourceFrom:cartFrom.getCatForms()){
            ZdRefundCartEntity zdRefundCartEntity=baseMapper.selectByOwnAndResourceID(sysUserEntity.getUserId(),zdRefundCartResourceFrom.getResourceId());
            int allowRefundNum=zdRefundCountryService.getRefundResourceLimit(sysUserEntity.getOrgCode(),
                    semesterEntityList,zdRefundCartResourceFrom.getResourceId(),sysSemesterEntity.getSemesterCode(),sysOrgSettingEntity.getToOrgCode());
            if(allowRefundNum<0||(allowRefundNum-zdRefundCartResourceFrom.getCatNum()<0))
            {
                throw new RRException("超出可退数量！");
            }
            if(zdRefundCartEntity==null) {
                zdRefundCartEntity=new ZdRefundCartEntity();
                zdRefundCartEntity.setCatNum(zdRefundCartResourceFrom.getCatNum());
            }else{
                zdRefundCartEntity.setCatNum(zdRefundCartEntity.getCatNum()+zdRefundCartResourceFrom.getCatNum());
            }

            zdRefundCartEntity.setOrgCode(sysUserEntity.getOrgCode());
            zdRefundCartEntity.setSupplierId(cartFrom.getSupplierId());
            zdRefundCartEntity.setResourceId(zdRefundCartResourceFrom.getResourceId());
            if(StringUtils.isBlank(zdRefundCartEntity.getId())) {
                insert(zdRefundCartEntity);
            }else {
                updateById(zdRefundCartEntity);
            }
        }
*/

    }

    @Override
    public PageUtils selectListPage(Map<String, Object> params) {
        Page<ZdRefundCartVO> page=new Query<ZdRefundCartVO>(params).getPage();
        page.setRecords(baseMapper.selectListPage(page,params));
        return new PageUtils(page);
    }

    @Override
    public List<ZdRefundCartVO> selectList(Map<String, Object> params) {
        return baseMapper.selectListPage(params);
    }

    @Override
    @Transactional
    public void submitOrder(SubmitZdRefundCartFrom orderForm) {
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        if(orderForm.getIds()!=null&&orderForm.getIds().size()>0)
        {
            //部分提交
            List<ZdRefundCartEntity> zdRefundCartEntities=baseMapper.selectByIdsAndOwn(orderForm.getSupplierId(),orderForm.getIds(),sysUserEntity.getUserId());
            List<ZdRefundResourceEntity> resourceList=new ArrayList<>();
            for(ZdRefundCartEntity zdRefundCartEntity:zdRefundCartEntities)
            {
                ZdRefundResourceEntity zdRefundResourceEntity=new ZdRefundResourceEntity();
                zdRefundResourceEntity.setRefundNum(zdRefundCartEntity.getCatNum());
                zdRefundResourceEntity.setResourceId(zdRefundCartEntity.getResourceId());
                resourceList.add(zdRefundResourceEntity);
            }
            ZdRefundCreateOrderFrom zdRefundForm=new ZdRefundCreateOrderFrom();
            zdRefundForm.setResourceList(resourceList);
            if(Constant.REFUND_ORG_STATUS.CONFIRM.equals(orderForm.getStatus()))
            {
                zdRefundForm.setStatus(orderForm.getStatus());
            }else
            {
                zdRefundForm.setStatus(Constant.REFUND_ORG_STATUS.NEW);
            }
            zdRefundForm.setFromOrgCode(sysUserEntity.getOrgCode());
            zdRefundCountryService.insertRefundOrder(zdRefundForm);
            deleteBatchIds(orderForm.getIds());

        }else
        {
            //全部提交
            List<ZdRefundCartEntity> zdRefundCartEntities=baseMapper.selectByIdsAndOwn(orderForm.getSupplierId(),null,sysUserEntity.getUserId());
            List<ZdRefundResourceEntity> resourceList=new ArrayList<>();
            for(ZdRefundCartEntity zdRefundCartEntity:zdRefundCartEntities)
            {
                ZdRefundResourceEntity zdRefundResourceEntity=new ZdRefundResourceEntity();
                zdRefundResourceEntity.setRefundNum(zdRefundCartEntity.getCatNum());
                zdRefundResourceEntity.setResourceId(zdRefundCartEntity.getResourceId());
                resourceList.add(zdRefundResourceEntity);
            }
            ZdRefundCreateOrderFrom zdRefundForm=new ZdRefundCreateOrderFrom();
            zdRefundForm.setResourceList(resourceList);
            if(Constant.REFUND_ORG_STATUS.CONFIRM.equals(orderForm.getStatus()))
            {
                zdRefundForm.setStatus(orderForm.getStatus());
            }else
            {
                zdRefundForm.setStatus(Constant.REFUND_ORG_STATUS.NEW);
            }

            zdRefundForm.setFromOrgCode(sysUserEntity.getOrgCode());
            zdRefundCountryService.insertRefundOrder(zdRefundForm);
            baseMapper.deleteByCreateBy(sysUserEntity.getUserId());
        }
    }

    @Override
    @Transactional
    public void submitOrder2(SubmitZdRefundCartFrom orderForm) {
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        //全部提交
        List<ZdRefundCartEntity> zdRefundCartEntities=baseMapper.selectByIdsAndOwn(orderForm.getSupplierId(),null,sysUserEntity.getUserId());
        Map<String,List<ZdRefundCartEntity>> map=zdRefundCartEntities.stream().collect(Collectors.groupingBy(ZdRefundCartEntity::getRefundSemesterCode));
        map.keySet().forEach(semesterCode->{
            List<ZdRefundResourceEntity> resourceList=new ArrayList<>();
            for(ZdRefundCartEntity zdRefundCartEntity:map.get(semesterCode))
            {
                ZdRefundResourceEntity zdRefundResourceEntity=new ZdRefundResourceEntity();
                zdRefundResourceEntity.setRefundNum(zdRefundCartEntity.getCatNum());
                zdRefundResourceEntity.setResourceId(zdRefundCartEntity.getResourceId());
                resourceList.add(zdRefundResourceEntity);
            }
            ZdRefundCreateOrderFrom zdRefundForm=new ZdRefundCreateOrderFrom();
            zdRefundForm.setRefundSemesterCode(semesterCode);
            zdRefundForm.setResourceList(resourceList);
            if(Constant.REFUND_ORG_STATUS.CONFIRM.equals(orderForm.getStatus()))
            {
                zdRefundForm.setStatus(orderForm.getStatus());
            }else
            {
                zdRefundForm.setStatus(Constant.REFUND_ORG_STATUS.NEW);
            }
            zdRefundForm.setFromOrgCode(sysUserEntity.getOrgCode());
            zdRefundCountryService.submitRefundOrder(zdRefundForm);
        });
        baseMapper.deleteByCreateBy(sysUserEntity.getUserId());
    }

    @Override
    public BigDecimal selectCartTotal(Map map,String semesterCode) {
        return baseMapper.selectCartTotal(map,semesterCode);
    }

    @Override
    @Transactional
    public void saveProvinceCartForm(ZdRefundCartFrom cartFrom) {
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
//        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();
        boolean refundStatus= semesterEntityList.stream().anyMatch(s -> s.getSemesterCode().equals(cartFrom.getRefundSemesterCode()));
        if(!refundStatus){
            throw new RRException("选择的报订季已关闭退货!");
        }

        RefundLimitInfoVO refundLimitInfoVO=zdRefundProvinceService.getRefundLimitInfo2(cartFrom.getRefundSemesterCode(),sysUserEntity.getOrgCode(),cartFrom.getSupplierId());
        BigDecimal mayang=new BigDecimal("0.00");
        for(ZdRefundCartResourceFrom zdRefundCartResourceFrom:cartFrom.getCatForms())
        {
            SubjectResourceEntity subjectResourceEntity=subjectResourceService.selectById(zdRefundCartResourceFrom.getResourceId());
            ZdRefundCartEntity zdRefundCartEntity=baseMapper.selectByOwnAndResourceID(cartFrom.getRefundSemesterCode(),sysUserEntity.getUserId(),zdRefundCartResourceFrom.getResourceId());
            Integer allowRefundNum=zdRefundProvinceService.getRefundResourceLimit2(sysUserEntity.getOrgCode(),
                    cartFrom.getRefundSemesterCode(),zdRefundCartResourceFrom.getResourceId());
            if(allowRefundNum==null){
                allowRefundNum=0;
            }
            if(allowRefundNum<0||(allowRefundNum-zdRefundCartResourceFrom.getRefundNum()<0))
            {
                throw new RRException("超出可退数量！");
            }
            if(zdRefundCartEntity==null) {
                zdRefundCartEntity=new ZdRefundCartEntity();
                zdRefundCartEntity.setCatNum(zdRefundCartResourceFrom.getRefundNum());
            }else{
                zdRefundCartEntity.setCatNum(zdRefundCartEntity.getCatNum()+zdRefundCartResourceFrom.getRefundNum());
            }
            mayang=mayang.add(new BigDecimal(zdRefundCartResourceFrom.getRefundNum()*subjectResourceEntity.getPrice()));
            if(mayang.compareTo(refundLimitInfoVO.getAllowPublishMayang())>0){
                throw new RRException("超出可退码洋！");
            }
            zdRefundCartEntity.setRefundSemesterCode(cartFrom.getRefundSemesterCode());
            zdRefundCartEntity.setOrgCode(sysUserEntity.getOrgCode());
            zdRefundCartEntity.setSupplierId(cartFrom.getSupplierId());
            zdRefundCartEntity.setResourceId(zdRefundCartResourceFrom.getResourceId());
            if(StringUtils.isBlank(zdRefundCartEntity.getId())) {
                insert(zdRefundCartEntity);
            }else {
                updateById(zdRefundCartEntity);
            }
        }
    }

    @Override
    @Transactional
    public void provinceSubmitOrder(SubmitZdRefundCartFrom orderForm) {
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        List<String> suppliers=baseMapper.selectSuppliers(sysUserEntity.getUserId());
        for(String supplierId:suppliers){
            ZdRefundForm zdRefundForm=new ZdRefundForm();
            List<ZdRefundResourceForm> list=new ArrayList<>();
            zdRefundForm.setSupplierId(supplierId);
            zdRefundForm.setSemesterCode(sysSemesterEntity.getSemesterCode());
            List<ZdRefundCartEntity> zdCartEntities=baseMapper.selectByIdsAndOwn(supplierId,null,sysUserEntity.getUserId());
            for(ZdRefundCartEntity zdRefundCartEntity:zdCartEntities){
                ZdRefundResourceForm zdRefundResourceForm=new ZdRefundResourceForm();
                zdRefundResourceForm.setResourceId(zdRefundCartEntity.getResourceId());
                zdRefundResourceForm.setRefundNum(zdRefundCartEntity.getCatNum());
                zdRefundResourceForm.setPrice(zdRefundCartEntity.getPrice());
                list.add(zdRefundResourceForm);
            }
            zdRefundForm.setResourceForm(list);
            zdRefundProvinceService.saveRefund(zdRefundForm);
        }

        baseMapper.deleteByCreateBy(sysUserEntity.getUserId());
    }

    @Override
    @Transactional
    public void provinceSubmitOrder2(SubmitZdRefundCartFrom orderForm) {

        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
//        List<String> suppliers=baseMapper.selectSuppliers(sysUserEntity.getUserId());
//        for(String supplierId:suppliers){
            ZdRefundForm zdRefundForm=new ZdRefundForm();
            zdRefundForm.setSupplierId(orderForm.getSupplierId());
            zdRefundForm.setSemesterCode(sysSemesterEntity.getSemesterCode());
            List<ZdRefundCartEntity> zdCartEntities=baseMapper.selectByIdsAndOwn(orderForm.getSupplierId(),null,sysUserEntity.getUserId());
            Map<String,List<ZdRefundCartEntity>> map=zdCartEntities.stream().collect(Collectors.groupingBy(ZdRefundCartEntity::getRefundSemesterCode));
            map.keySet().forEach(semesterCode-> {
                List<ZdRefundResourceForm> list=new ArrayList<>();
                zdRefundForm.setRefundSemesterCode(semesterCode);
                for(ZdRefundCartEntity zdRefundCartEntity:map.get(semesterCode)){
                    ZdRefundResourceForm zdRefundResourceForm=new ZdRefundResourceForm();
                    zdRefundResourceForm.setResourceId(zdRefundCartEntity.getResourceId());
                    zdRefundResourceForm.setRefundNum(zdRefundCartEntity.getCatNum());
                    zdRefundResourceForm.setPrice(zdRefundCartEntity.getPrice());
                    list.add(zdRefundResourceForm);
                }
                zdRefundForm.setResourceForm(list);
                zdRefundProvinceService.saveRefund(zdRefundForm);
            });

//        }

        baseMapper.deleteByCreateBy(sysUserEntity.getUserId());

    }

    @Override
    @Transactional
    public void saveRefundForm(RefundCartFrom refundCartFrom) {


        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        zdRefundCountryService.getRefundLimitInfo2(sysUserEntity.getOrgCode(),refundCartFrom.getRefundSemesterCode());
        SysOrgSettingEntity sysOrgSettingEntity= sysOrgSettingService.selectByOrg(sysUserEntity.getOrgCode());
        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();
//        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();

        boolean refundStatus= semesterEntityList.stream().anyMatch(s -> s.getSemesterCode().equals(refundCartFrom.getRefundSemesterCode()));
        if(!refundStatus){
            throw new RRException("选择的报订季已关闭退货!");
        }

//            ZdRefundCartFrom zdRefundCartFrom=cartFrom.getCardForm();
        for(ZdRefundCartResourceFrom zdRefundCartResourceFrom:refundCartFrom.getCatForms())
        {
            ZdRefundCartEntity zdRefundCartEntity=baseMapper.selectByOwnAndResourceID(refundCartFrom.getRefundSemesterCode(),sysUserEntity.getUserId(),zdRefundCartResourceFrom.getResourceId());
            int allowRefundNum=zdRefundCountryService.getRefundResourceLimit2(refundCartFrom.getRefundSemesterCode(),sysUserEntity.getOrgCode(),sysOrgSettingEntity.getToOrgCode(),zdRefundCartResourceFrom.getResourceId());
            if(allowRefundNum<0||(allowRefundNum-zdRefundCartResourceFrom.getCatNum()<0))
            {
                throw new RRException("超出可退数量！");
            }
            if(zdRefundCartEntity==null) {
                zdRefundCartEntity=new ZdRefundCartEntity();
                zdRefundCartEntity.setCatNum(zdRefundCartResourceFrom.getCatNum());
            }else{
                zdRefundCartEntity.setCatNum(zdRefundCartEntity.getCatNum()+zdRefundCartResourceFrom.getCatNum());
            }
            zdRefundCartEntity.setRefundSemesterCode(refundCartFrom.getRefundSemesterCode());
            zdRefundCartEntity.setOrgCode(sysUserEntity.getOrgCode());
            zdRefundCartEntity.setSupplierId(refundCartFrom.getSupplierId());
            zdRefundCartEntity.setResourceId(zdRefundCartResourceFrom.getResourceId());
            if(StringUtils.isBlank(zdRefundCartEntity.getId())) {
                insert(zdRefundCartEntity);
            }else {
                updateById(zdRefundCartEntity);
            }
        }





    }

    @Override
    public List<SysCompanyEntity> selectSuppliers(Long userId) {
        return baseMapper.selectCompany(userId);
    }


}
