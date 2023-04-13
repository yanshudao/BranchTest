package io.renren.modules.subject.cxf.impl;


import com.thoughtworks.xstream.XStream;
import io.renren.common.exception.RRException;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.RedisUtils;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.subject.dao.SubjectResourceDao;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.entity.request.*;
import io.renren.modules.subject.cxf.SyncNCService;
import io.renren.modules.subject.util.ArithUtil;
import io.renren.modules.subject.util.CXFClient;
import io.renren.modules.subject.util.XStreamUtil;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.dao.SysOrgSettingDao;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.dao.ZdRefundDao;
import io.renren.modules.zd.dao.ZdSourceDao;
import io.renren.modules.zd.dao.ZdSourceResourceDao;
import io.renren.modules.zd.entity.ZdRefundSupplierEntity;
import io.renren.modules.zd.entity.ZdRefundSupplierResourceEntity;
import io.renren.modules.zd.entity.ZdSourceEntity;
import io.renren.modules.zd.entity.ZdSourceResourceEntity;
import io.renren.modules.zd.service.ZdRefundProvinceService;
import io.renren.modules.zd.service.ZdRefundSupplierService;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SyncNCServiceImpl implements SyncNCService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZdSourceDao zdSourceDao;
    @Autowired
    private ZdRefundDao zdRefundDao;
    @Autowired
    private ZdSourceResourceDao zdSourceResourceDao;
    @Autowired
    private SysOrgDao sysOrgDao;
    @Autowired
    private SysOrgSettingDao sysOrgSettingDao;
    @Autowired
    private SubjectResourceDao subjectResourceDao;
    @Autowired
    private ZdRefundProvinceService zdRefundProvinceService;
    @Autowired
    private ZdRefundSupplierService zdRefundSupplierService;
    @Autowired
    private RedisUtils redisUtils;
    @Value("${webservice.service.url}")
    private String cxfUrl;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrderbill(String id) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        ZdSourceEntity zdSourceEntity = zdSourceDao.selectById(id);
        boolean lock = redisUtils.tryLock(id, "1");
        if (lock) {
            try {

                //        TSDepart tsDepart=tsUser.getCurrentDepart();

                List<ZdSourceResourceEntity> zdSourceResourceEntities = zdSourceResourceDao.selectBySourceId(zdSourceEntity.getId());
                SysOrgSettingEntity sysOrgEntity = sysOrgSettingDao.selectByOrg(zdSourceEntity.getToOrgCode());
                SysOrgEntity toOrgEntity = sysOrgDao.selectByOrgCode(zdSourceEntity.getToOrgCode());
                Orderbill orderbill = new Orderbill();
                orderbill.setId(zdSourceEntity.getSourceNo());
                if (StringUtils.isBlank(zdSourceEntity.getToOrgCode())) {
                    SysOrgSettingEntity shengOrg = sysOrgSettingDao.selectByOrg(zdSourceEntity.getOrgCode());
                    orderbill.setBookstoreid(shengOrg.getCustCode());
                    orderbill.setCoperatorid(shengOrg.getUsercode());
                } else {
                    orderbill.setBookstoreid(sysOrgEntity.getCustCode());
                    orderbill.setCoperatorid(sysOrgEntity.getUsercode());
                }

                orderbill.setCorpcode("304");
                if (toOrgEntity != null) {
                    orderbill.setStaplerbasis(toOrgEntity.getOrgName() + "-" + toOrgEntity.getOrgCode());
                }
                orderbill.setAddress(zdSourceEntity.getAddress());
                orderbill.setLinkman(zdSourceEntity.getLinkman());
                orderbill.setTelephone(zdSourceEntity.getTelephone());
                orderbill.setStaplerbasis(zdSourceEntity.getNote());
                orderbill.setRemark(zdSourceEntity.getRemarks());
                orderbill.setZipcode(zdSourceEntity.getZipCode());
//        orderbill.setNote(zdSourceEntity.getNote());
                int total = 0;
                BigDecimal totalmayang = BigDecimal.ZERO;
//        Double totalmayang=new Double(0);
                List<Book> books = new ArrayList<>();
                List<Orderbill> orderbills = new ArrayList<>();
                DecimalFormat myformat = new DecimalFormat("##.00");
                for (ZdSourceResourceEntity zdSourceResourceEntity : zdSourceResourceEntities) {

                    SubjectResourceEntity subjectResourceEntity = subjectResourceDao.selectById(zdSourceResourceEntity.getResourceId());
                    Book book = new Book();
                    book.setBid(zdSourceResourceEntity.getId());
                    book.setBookcode(subjectResourceEntity.getResourceCode());
                    book.setBookname(subjectResourceEntity.getResourceName());
                    book.setUnitprice(subjectResourceEntity.getPrice());
                    book.setScrappedvol(zdSourceResourceEntity.getSourceNum());
                    total += zdSourceResourceEntity.getSourceNum();
                    BigDecimal price = new BigDecimal(String.valueOf(subjectResourceEntity.getPrice()));
                    BigDecimal num = new BigDecimal(String.valueOf(zdSourceResourceEntity.getSourceNum()));
//            totalmayang= ArithUtil.add(totalmayang,(subjectResourceEntity.getPrice()*zdSourceResourceEntity.getSourceNum()));
                    totalmayang = totalmayang.add(price.multiply(num));
                    System.out.println("totalmayang=" + totalmayang.toPlainString());
//            System.out.println("totalmayang="+totalmayang.doubleValue());
//            System.out.println("totalmayang="+myformat.format(totalmayang));
                    books.add(book);
                }
                OrderbillChildren orderbillChildren = new OrderbillChildren();
                orderbillChildren.setBook(books);
                orderbill.setChildren(orderbillChildren);
                orderbill.setTotalnum(total);
                orderbill.setTotalmayang(totalmayang);
                orderbill.setDmakedate(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));

                Object[] result = new Object[0];
                OrderbillDataInfo orderbillDataInfo = new OrderbillDataInfo();
                OrderbillList orderbillList = new OrderbillList();
                orderbills.add(orderbill);
                orderbillList.setOrderbill(orderbills);
                orderbillDataInfo.setDatainfo(orderbillList);
                XStream xStream = XStreamUtil.getInstance();
                xStream.alias("datainfos", OrderbillDataInfo.class);
                xStream.alias("book", Book.class);
                orderbillDataInfo.setSendtime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
                logger.debug("AddProsc=url:{},send:{}", cxfUrl, xStream.toXML(orderbillDataInfo));
                Client client = CXFClient.getInstance(cxfUrl);
                //    systemService.addLog(xStream.toXML(orderbillDataInfo), Globals.Log_Type_OTHER,Globals.Log_Leavel_INFO);
                result = client.invoke("AddProsc", xStream.toXML(orderbillDataInfo));
                //  systemService.addLog(result[0].toString(), Globals.Log_Type_OTHER,Globals.Log_Leavel_INFO);
                logger.debug("AddProsc=url:{},receive:{}", cxfUrl, result[0]);
                //zdSourceEntity.setIsSync("1");
                // zdSourceDao.updateById(zdSourceEntity);
                return result[0].toString();
            } catch (Exception e) {
                zdSourceEntity.setIsSync("2");
                logger.error(e.getMessage(), e);
                zdSourceDao.updateById(zdSourceEntity);
                throw new RRException("同步失败！");
                //  systemService.addLog(e.getMessage(), Globals.Log_Type_OTHER,Globals.Log_Leavel_ERROR);
            } finally {
                redisUtils.unLock(id, "1");
            }
        } else {
            throw new RRException("请勿重复同步！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String refundBook(String id) {
        boolean lock = redisUtils.tryLock(id, "1");
        ZdRefundSupplierEntity zdRefundEntity = zdRefundSupplierService.selectById(id);
        if (lock) {
            try {

                long l = System.currentTimeMillis();
                Backbill backbill = new Backbill();
                if (StringUtils.isBlank(zdRefundEntity.getCountyOrgCode())) {
                    SysOrgSettingEntity shengOrg = sysOrgSettingDao.selectByOrg(zdRefundEntity.getFromOrgCode());
                    backbill.setCcustomerid(shengOrg.getCustCode());
                    backbill.setCoperatorid(shengOrg.getUsercode());
                } else {
                    SysOrgSettingEntity sysOrgEntity = sysOrgSettingDao.selectByOrg(zdRefundEntity.getCountyOrgCode());
                    backbill.setCcustomerid(sysOrgEntity.getCustCode());
                    backbill.setCoperatorid(sysOrgEntity.getUsercode());
                }
                //TSUser tsUser= ResourceUtil.getSessionUserName();
//        TSDepart tsDepart=tsUser.getCurrentDepart();
                // TShopRefundEntity shopRefundEntity=systemService.get(TShopRefundEntity.class,id);
                // String hql = "from TShopRefundItemEntity where refundId =?";
                List<ZdRefundSupplierResourceEntity> zdRefundResourceEntities = zdRefundSupplierService.selectByRefundId(id);
                backbill.setId(zdRefundEntity.getRefundCode());
                backbill.setCorpcode("304");
                int total = 0;
                Double totalmayang = new Double(0);
                List<Book> books = new ArrayList<>();
                List<Backbill> backBillList = new ArrayList<>();
                String dateString = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
                for (ZdRefundSupplierResourceEntity zdRefundResourceEntity : zdRefundResourceEntities) {
                    SubjectResourceEntity subjectResourceEntity = subjectResourceDao.selectById(zdRefundResourceEntity.getResourceId());
                    Book book = new Book();
                    book.setBid(zdRefundResourceEntity.getId());
                    book.setBookcode(subjectResourceEntity.getResourceCode());
                    book.setBookname(subjectResourceEntity.getResourceName());
                    book.setUnitprice(subjectResourceEntity.getPrice());
//            book.setMayang(tShopRefundItemEntity.getUnitprice()*tShopRefundItemEntity.getNum());
                    book.setNdecimal(zdRefundResourceEntity.getRealNum());
                    book.setNhaoshunum(zdRefundResourceEntity.getRealNum());
                    book.setNwastnum(0);
                    // book.setScrappedvol(tShopRefundItemEntity.getNum());
                    total += zdRefundResourceEntity.getRealNum();
                    totalmayang += subjectResourceEntity.getPrice() * zdRefundResourceEntity.getRealNum();
                    books.add(book);
                }
                BackbillChildren backbillChildren = new BackbillChildren();
                backbillChildren.setBook(books);
                backbill.setChildren(backbillChildren);
                backbill.setTotaldecimal(total);
                backbill.setTotalmayang(totalmayang);
                backbill.setDmakedate(DateUtils.format(new Date(), DateUtils.DATE_PATTERN));
//        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();


                Object[] result = new Object[0];
                BackbillDataInfo backbillDataInfo = new BackbillDataInfo();
                backbillDataInfo.setSendtime(dateString);
                BackbillList orderbillList = new BackbillList();
                backBillList.add(backbill);
                orderbillList.setBackbill(backBillList);
                backbillDataInfo.setDatainfo(orderbillList);
                XStream xStream = XStreamUtil.getInstance();
                xStream.alias("datainfos", BackbillDataInfo.class);
                xStream.alias("book", Book.class);
                logger.debug("refundBook=url:{},send:{}", cxfUrl, xStream.toXML(backbillDataInfo));
                // systemService.addLog(xStream.toXML(backbillDataInfo), Globals.Log_Type_OTHER,Globals.Log_Leavel_INFO);
                Client client = CXFClient.getInstance(cxfUrl);
                result = client.invoke("refundBook", xStream.toXML(backbillDataInfo));
                //systemService.addLog(result[0].toString(), Globals.Log_Type_OTHER,Globals.Log_Leavel_INFO);
                logger.info("refundBook={},sn={}", result[0], l);
                return result[0].toString();
            } catch (Exception e) {
                e.printStackTrace();
                zdRefundEntity.setIsSync("2");
                logger.error(e.getMessage(), e);
                zdRefundSupplierService.updateById(zdRefundEntity);
                //   systemService.addLog(e.getMessage(), Globals.Log_Type_OTHER,Globals.Log_Leavel_ERROR);
                throw new RRException("同步失败！");
            } finally {
                redisUtils.unLock(id, "1");
            }
        } else {
            throw new RRException("请勿重复提交！");
        }
        //  return null;
    }
}
