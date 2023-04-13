package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.thoughtworks.xstream.XStream;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.search.vo.StatisticsSourceResourceDetailVO;
import io.renren.modules.search.vo.StatisticsSourceResourceVO;
import io.renren.modules.subject.dao.SubjectResourceDao;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.entity.request.Orderbill;
import io.renren.modules.subject.entity.request.OrderbillDataInfo;
import io.renren.modules.subject.entity.request.OrderbillList;
import io.renren.modules.subject.cxf.SyncNCService;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.subject.util.XStreamUtil;
import io.renren.modules.sys.dao.SysCompanyDao;
import io.renren.modules.sys.entity.SysCompanyEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.dao.ZdSourceDao;
import io.renren.modules.zd.dao.ZdSourceOrderResourceDao;
import io.renren.modules.zd.dao.ZdSourceResourceDao;
import io.renren.modules.zd.entity.ZdSourceEntity;
import io.renren.modules.zd.entity.ZdSourceOrderResourceEntity;
import io.renren.modules.zd.entity.ZdSourceResourceEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.form.ZdOrgSourceForm;
import io.renren.modules.zd.form.ZdSourceForm;
import io.renren.modules.zd.form.ZdSourceResourceForm;
import io.renren.modules.zd.service.ZdOrderResourceService;
import io.renren.modules.zd.service.ZdSourceService;
import io.renren.modules.zd.vo.ZdSourceDetailVO;
import io.renren.modules.zd.vo.ZdSourceIncomeVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Service("zdSourceService")
public class ZdSourceServiceImpl extends ServiceImpl<ZdSourceDao, ZdSourceEntity> implements ZdSourceService {

    @Autowired
    private ZdSourceDao zdSourceDao;
    @Autowired
    private SubjectResourceDao subjectResourceDao;
    @Autowired
    private SubjectResourceService subjectResourceService;
    @Autowired
    private ZdSourceResourceDao zdSourceResourceDao;
    @Autowired
    private SyncNCService syncNCService;
    @Autowired
    private SysCompanyDao sysCompanyDao;
    @Autowired
    private ZdSourceOrderResourceDao zdSourceOrderResourceDao;
    @Autowired
    private ZdOrderResourceService zdOrderResourceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdSourceEntity> page = this.selectPage(
                new Query<ZdSourceEntity>(params).getPage(),
                new EntityWrapper<ZdSourceEntity>()
        );
        return new PageUtils(page);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSource(ZdSourceForm zdSource) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        Map<String, List<ZdSourceResourceEntity>> stringListMap = new HashMap<>();
        for (ZdSourceResourceForm zdSourceResourceForm : zdSource.getResourceForm()) {
            SubjectResourceEntity subjectResourceEntity = subjectResourceDao.selectById(zdSourceResourceForm.getResourceId());
            if (subjectResourceEntity != null) {
                ZdSourceResourceEntity zdSourceResourceEntity = new ZdSourceResourceEntity();
//                zdSourceResourceEntity.setSourceId(zdSourceEntity.getId());
                zdSourceResourceEntity.setItemStatus(Constant.SOURCE_STATUS.NEW);
                zdSourceResourceEntity.setResourceId(zdSourceResourceForm.getResourceId());
                zdSourceResourceEntity.setSourceNum(zdSourceResourceForm.getNum());
                zdSourceResourceEntity.setResourcePrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                zdSourceResourceEntity.setIds(zdSourceResourceForm.getIds());
//                zdSourceResourceDao.insert(zdSourceResourceEntity);
                if (stringListMap.containsKey(subjectResourceEntity.getSupplierId())) {
                    List<ZdSourceResourceEntity> list = stringListMap.get(subjectResourceEntity.getSupplierId());
                    if (list == null || list.size() == 0) {
                        list = new ArrayList<>();
                    }
                    list.add(zdSourceResourceEntity);
                    stringListMap.put(subjectResourceEntity.getSupplierId(), list);
                } else {
                    List<ZdSourceResourceEntity> list = new ArrayList<>();
                    list.add(zdSourceResourceEntity);
                    stringListMap.put(subjectResourceEntity.getSupplierId(), list);

                }

            }

        }
        for (Map.Entry<String, List<ZdSourceResourceEntity>> entry : stringListMap.entrySet()) {

            String supplierId = entry.getKey();
            SysCompanyEntity sysCompanyEntity = sysCompanyDao.selectById(supplierId);
            List<ZdSourceResourceEntity> subjectResourceEntities = entry.getValue();
            String timeInSecond = DateUtils.format(new Date(), DateUtils.DATE_TIME_IN_SECOND);
            String orgCode=zdSource.getOrgCode()==null?sysUserEntity.getOrgCode():zdSource.getOrgCode();
            ZdSourceEntity zdSourceEntity = new ZdSourceEntity();
            zdSourceEntity.setRemarks(zdSource.getRemark());
            zdSourceEntity.setOrgCode(orgCode);
            zdSourceEntity.setToOrgCode(zdSource.getToOrgCode());
            zdSourceEntity.setSourceName("采购单-" + sysCompanyEntity.getCompanyName() + timeInSecond);
            zdSourceEntity.setSourceType(zdSource.getSourceType());
            zdSourceEntity.setSupplierId(supplierId);
            zdSourceEntity.setSemesterCode(zdSource.getSemesterCode());
            zdSourceEntity.setSourceNo("CG" + sysUserEntity.getOrgCode() + timeInSecond);
            zdSourceEntity.setStatus(zdSource.getStatus());
            zdSourceDao.insert(zdSourceEntity);
            for (ZdSourceResourceEntity subjectResourceEntity : subjectResourceEntities) {
                subjectResourceEntity.setSourceId(zdSourceEntity.getId());
                subjectResourceEntity.setMayang(subjectResourceEntity.getResourcePrice().multiply(BigDecimal.valueOf(subjectResourceEntity.getSourceNum())));
                zdSourceResourceDao.insert(subjectResourceEntity);
                if(Constant.SOURCE_TYPE.ZD_CG.equals(zdSource.getSourceType())){
                    String[] ids = subjectResourceEntity.getIds().split(",");
                    if (ids.length == 0) {
                        throw new RRException("无法找到报订详情单");
                    }
                    for (String orderResourceId : ids) {
                        ZdSourceOrderResourceEntity zdSourceOrderResourceEntity = new ZdSourceOrderResourceEntity();
                        zdSourceOrderResourceEntity.setOrderResourceId(orderResourceId);
                        zdSourceOrderResourceEntity.setSourceId(zdSourceEntity.getId());
                        zdSourceOrderResourceDao.insert(zdSourceOrderResourceEntity);
                    }
                }

            }
        }

    }

    @Override
    public PageUtils queryListPage(Map<String, Object> params) {
        Page<ZdSourceEntity> page = new Query<ZdSourceEntity>(params).getPage();
        page.setRecords(zdSourceDao.selectListPage(page, params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryResourceListPage(Map<String, Object> params) {
        Page<ZdSourceDetailVO> page = new Query<ZdSourceDetailVO>(params).getPage();
        page.setRecords(zdSourceResourceDao.selectListPage(page, params));
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completeOrder(List<String> ids) {
        for (String sourceId : ids) {
            ZdSourceEntity sourceEntity = zdSourceDao.selectById(sourceId);
            if (sourceEntity != null) {
                SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
                sourceEntity.setStatus(Constant.SOURCE_STATUS.COMPLETE);
                sourceEntity.setCompleteBy(String.valueOf(sysUserEntity.getUserId()));
                sourceEntity.setCompleteTime(new Date());
                zdSourceDao.updateById(sourceEntity);
            }

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String syncOrder(String ids) {
        StringBuffer stringBuffer = new StringBuffer();
        String xml = syncNCService.createOrderbill(ids);
        if (xml == null) {
            ZdSourceEntity sourceEntity = zdSourceDao.selectById(ids);
//            TShopOrderEntity tShopOrderEntity=get(TShopOrderEntity.class,id);
            sourceEntity.setResult("失败");
            sourceEntity.setReason("服务器异常");
            sourceEntity.setIsSync("2");
            zdSourceDao.updateById(sourceEntity);
            stringBuffer.append("订单号：" + ids + ",结果：" + sourceEntity.getResult() + ",原因：" + sourceEntity.getReason() + "<br>");

        } else {
            XStream stream = XStreamUtil.getInstance();
            stream.alias("datainfos", OrderbillDataInfo.class);
            OrderbillDataInfo orderbillDataInfo = (OrderbillDataInfo) stream.fromXML(xml);
            OrderbillList orderbillList = orderbillDataInfo.getDatainfo();
            for (Orderbill orderbill : orderbillList.getOrderbill()) {
                ZdSourceEntity sourceEntity = zdSourceDao.selectById(ids);
                if (sourceEntity != null) {
                    if (StringUtils.isNotBlank(orderbill.getErpcode())) {
                        sourceEntity.setIsSync("1");
                        zdSourceResourceDao.updateStatus(Constant.SOURCE_RESOURCE_STATUS.SHIP_SUBMIT,sourceEntity.getId());
                    }
                    sourceEntity.setErpcode(orderbill.getErpcode());
                    sourceEntity.setResult(orderbill.getResult());
                    sourceEntity.setReason(orderbill.getReason());
                    zdSourceDao.updateById(sourceEntity);
                }

            }
        }

        return stringBuffer.toString();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSourceResource(List<ZdSourceResourceForm> zdSourceResourceForms) {
        for (ZdSourceResourceForm zdSourceResourceForm : zdSourceResourceForms) {
            ZdSourceResourceEntity zdSourceResourceEntity = zdSourceResourceDao.selectById(zdSourceResourceForm.getId());
            if (zdSourceResourceEntity != null) {
                zdSourceResourceEntity.setSourceNum(zdSourceResourceForm.getNum());
                zdSourceResourceEntity.setMayang(zdSourceResourceEntity.getResourcePrice().multiply(BigDecimal.valueOf(zdSourceResourceEntity.getSourceNum())));
                zdSourceResourceDao.updateById(zdSourceResourceEntity);
            }

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSourceResource(List<String> ids) {
        zdSourceResourceDao.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSource(List<String> ids) {
        zdSourceDao.deleteBatchIds(ids);
        zdSourceResourceDao.deleteBySourceIds(ids);
        zdSourceOrderResourceDao.deleteBySourceIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSourceByOrg(ZdOrgSourceForm zdOrgSourceForm) {
        Map<String, List<ZdSourceResourceEntity>> stringListMap = new HashMap<>();
        for (ZdSourceResourceForm zdSourceResourceForm : zdOrgSourceForm.getResourceForm()) {
            SubjectResourceEntity subjectResourceEntity = subjectResourceDao.selectById(zdSourceResourceForm.getResourceId());
            int orderSum=zdOrderResourceService.queryOrderSumByIds(zdSourceResourceForm.getIds().split(","));
            if (subjectResourceEntity != null) {
                ZdSourceResourceEntity zdSourceResourceEntity = new ZdSourceResourceEntity();
//                zdSourceResourceEntity.setSourceId(zdSourceEntity.getId());
                zdSourceResourceEntity.setItemStatus(Constant.SOURCE_STATUS.NEW);
                zdSourceResourceEntity.setResourceId(zdSourceResourceForm.getResourceId());
                if(orderSum!=zdSourceResourceForm.getNum()){
                    throw new RRException("合并报订单出错，教材数量不一致，请联系管理员查找原因!");
                }
                zdSourceResourceEntity.setSourceNum(zdSourceResourceForm.getNum());
                zdSourceResourceEntity.setResourcePrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                zdSourceResourceEntity.setMayang(BigDecimal.valueOf(subjectResourceEntity.getPrice()).multiply(BigDecimal.valueOf(zdSourceResourceForm.getNum())));
                zdSourceResourceEntity.setIds(zdSourceResourceForm.getIds());
                //                zdSourceResourceDao.insert(zdSourceResourceEntity);
                if (stringListMap.containsKey(subjectResourceEntity.getSupplierId())) {
                    List<ZdSourceResourceEntity> list = stringListMap.get(subjectResourceEntity.getSupplierId());
                    if (list == null || list.size() == 0) {
                        list = new ArrayList<>();
                    }
                    list.add(zdSourceResourceEntity);
                    stringListMap.put(subjectResourceEntity.getSupplierId(), list);
                } else {
                    List<ZdSourceResourceEntity> list = new ArrayList<>();
                    list.add(zdSourceResourceEntity);
                    stringListMap.put(subjectResourceEntity.getSupplierId(), list);

                }

            } else {
                throw new RRException("教材库中未找到教材!");
            }

        }
        for (Map.Entry<String, List<ZdSourceResourceEntity>> entry : stringListMap.entrySet()) {

            String supplierId = entry.getKey();
            SysCompanyEntity sysCompanyEntity = sysCompanyDao.selectById(supplierId);
            List<ZdSourceResourceEntity> subjectResourceEntities = entry.getValue();
            String timeInSecond = DateUtils.format(new Date(), DateUtils.DATE_TIME_IN_SECOND);
            SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
            ZdSourceEntity zdSourceEntity = new ZdSourceEntity();
            zdSourceEntity.setNote(zdOrgSourceForm.getNote());
            zdSourceEntity.setRemarks(zdOrgSourceForm.getRemark());
            zdSourceEntity.setToOrgCode(zdOrgSourceForm.getOrgCode());
            zdSourceEntity.setOrgCode(sysUserEntity.getOrgCode());
            zdSourceEntity.setSourceName("采购单-" + sysCompanyEntity.getCompanyName() + timeInSecond);
            zdSourceEntity.setSourceType(Constant.SOURCE_TYPE.ZD_CG);
            zdSourceEntity.setSupplierId(supplierId);
            zdSourceEntity.setSemesterCode(zdOrgSourceForm.getSemesterCode());
            zdSourceEntity.setSourceNo("CG" + zdOrgSourceForm.getOrgCode() + timeInSecond);
            zdSourceEntity.setStatus(zdOrgSourceForm.getStatus());
            zdSourceDao.insert(zdSourceEntity);
            for (ZdSourceResourceEntity subjectResourceEntity : subjectResourceEntities) {
                subjectResourceEntity.setSourceId(zdSourceEntity.getId());
                zdSourceResourceDao.insert(subjectResourceEntity);
                if(subjectResourceEntity.getIds()==null){
                    throw new RRException("缺少参数报订详情单ID");
                }
                String[] ids = subjectResourceEntity.getIds().split(",");
                if (ids.length == 0) {
                    throw new RRException("无法找到报订详情单");
                }
                for (String orderResourceId : ids) {
                    ZdSourceOrderResourceEntity zdSourceOrderResourceEntity = new ZdSourceOrderResourceEntity();
                    zdSourceOrderResourceEntity.setSourceResourceId(subjectResourceEntity.getId());
                    zdSourceOrderResourceEntity.setOrderResourceId(orderResourceId);
                    zdSourceOrderResourceEntity.setSourceId(zdSourceEntity.getId());
                    zdSourceOrderResourceDao.insert(zdSourceOrderResourceEntity);
                }
            }
        }

    }

    @Override
    public ZdSourceEntity selectBySourceNo(String id) {
        return zdSourceDao.selectBySourceNo(id);
    }

    @Override
    public PageUtils queryResourceIncomePage(Map<String, Object> params) {
        Page<ZdSourceIncomeVO> page = new Query<ZdSourceIncomeVO>(params).getPage();
        page.setRecords(subjectResourceDao.selectIncomePage(page, params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryStatisticsByMap(Map<String, Object> params) {
        Page<StatisticsSourceResourceVO> page = new Query<StatisticsSourceResourceVO>(params).getPage();
        page.setRecords(zdSourceDao.selectStatisticsByMap(page, params));
        return new PageUtils(page);
    }

    @Override
    public List<StatisticsSourceResourceVO> queryStatisticsAllByMap(Map<String, Object> params) {
        return zdSourceDao.selectStatisticsByMap(params);
    }

    @Override
    public int countByMap(Map map) {
        return zdSourceDao.countByMap(map);
    }

    @Override
    public List<StatisticsSourceResourceDetailVO> queryStatisticsDetailByMap(Map<String, Object> params) {
        return zdSourceDao.selectStatisticsDetailByMap(params);
    }

    @Override
    public List<ZdSourceDetailVO> listResourceAll(SearchForm searchForm) {
        return zdSourceResourceDao.selectResourceAll(searchForm);
    }

    @Override
    @Transactional
    public void updateVersion(String id, String id1, List<String> orgList, String semesterCode,String zmcrId) {
        zdSourceResourceDao.updateResourceVersion(id,id1,orgList,semesterCode,zmcrId);
    }

    @Override
    @Transactional
    public int updateAddress(ZdSourceEntity zdSourceEntity) {
        return baseMapper.updateAddress(zdSourceEntity);
    }

}
