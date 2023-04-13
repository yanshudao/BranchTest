package io.renren.modules.subject.cxf.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.thoughtworks.xstream.XStream;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DateUtils;
import io.renren.modules.subject.cxf.ZyddService;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.entity.SubjectResourceTypeEntity;
import io.renren.modules.subject.entity.request.*;
import io.renren.modules.subject.entity.response.ResponseBook;
import io.renren.modules.subject.entity.response.ResponseBookInfos;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.subject.service.SubjectResourceTypeService;
import io.renren.modules.subject.util.XStreamUtil;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.*;
import io.renren.modules.zd.form.ZdSourceIncomeForm;
import io.renren.modules.zd.form.ZdSourceResourceIncomeForm;
import io.renren.modules.zd.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@WebService(serviceName = "ZyddService"//服务名
        ,targetNamespace = "http://service.zydd.jeecg.com/"//报名倒叙，并且和接口定义保持一致
        ,endpointInterface = "io.renren.modules.subject.cxf.ZyddService")//包名
@Component
public class  ZyddServiceImpl implements ZyddService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private SubjectResourceService subjectResourceService;
    @Resource
    private SysSemesterService sysSemesterService;
    @Resource
    private ZdSourceResourceService zdSourceResourceService;
    @Resource
    private ZdStockIncomeService zdStockIncomeService;
    @Resource
    private ZdRefundProvinceService zdRefundProvinceService;
    @Resource
    private ZdRefundSupplierResourceService zdRefundSupplierResourceService;
    @Resource
    private ZdRefundSupplierService zdRefundSupplierService;
    @Resource
    private ZdRefundResourceService zdRefundResourceService;
    @Resource
    private ZdStockIncomeResourceService zdStockIncomeResourceService;
    @Resource
    private ZdSourceService zdSourceService;
    @Resource
    private SubjectResourceTypeService subjectResourceTypeService;

    @Resource
    private SysOrgService sysOrgService;
//   @Override
//    public String sayHello(String name) {
//    return "hello"+name;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    //@SysLog("NC保存教材")
    public String saveBook(String xml) {
        String message="同步教材";
        long l = System.currentTimeMillis();
        ResponseBook responseBook = new ResponseBook();
        ResponseBookInfos responseBookInfos = new ResponseBookInfos(responseBook);
        XStream xstream= XStreamUtil.getInstance();
        xstream.alias("datainfos", BookInfos.class);
        try {

            BookInfos bookInfos = (BookInfos) xstream.fromXML(xml);
            Book book = bookInfos.getBook();
            logger.info("NC同步教材:receive{},sn={}", bookInfos, l);
            if("delete".equals(bookInfos.getOperate()))
            {
                SubjectResourceEntity subjectResourceEntity=subjectResourceService.selectByCode(book.getBookcode());
                if(subjectResourceEntity!=null){
                    subjectResourceEntity.setDeleteFlag("1");
                    subjectResourceService.updateById(subjectResourceEntity);
                }

            }else
            {

                if("Y".equals(book.getIszxx()))
                {
                    SubjectResourceEntity ytsResource=new SubjectResourceEntity();
                    ytsResource.setResourceCode(book.getYtscode());
                    ytsResource.setForeignId(book.getYtsid());
                    ytsResource.setResourceName(book.getYtsname());
                    ytsResource.setOrgCode("ZYDD");
                    ytsResource.setSupplierId("f8ad206116ec46dfa394ba5a45f39455");
                    SubjectResourceEntity newResource=new SubjectResourceEntity();
                    newResource.setResourceCode(book.getBookcode());
                    newResource.setResourceName(book.getBookname());
                    newResource.setResourceVersion(book.getVandpno());
                    newResource.setPublishDate(book.getPubdate());
                    newResource.setPrice(book.getFixedprice().doubleValue());
                    if(StringUtils.isNotBlank(book.getInvclassname())){
                        SubjectResourceTypeEntity subjectResourceTypeEntity=subjectResourceTypeService.selectByName(book.getInvclassname());
                        if(subjectResourceTypeEntity==null){
                            subjectResourceTypeEntity=new SubjectResourceTypeEntity();
                            subjectResourceTypeEntity.setName(book.getInvclassname());
                            subjectResourceTypeService.insert(subjectResourceTypeEntity);
                        }
                        newResource.setCatalogId(subjectResourceTypeEntity.getId());
                    }else{
                        newResource.setCatalogId(null);
                    }
                    if("文字主".equals(book.getTextbooktypename())){
                        newResource.setResourceType(Constant.RESOURCE_TYPE.ZHU);
                    }else if("文字辅".equals(book.getTextbooktypename())){
                        newResource.setResourceType(Constant.RESOURCE_TYPE.FU);
                    }else{
                        newResource.setResourceType(Constant.RESOURCE_TYPE.OTHER);
                    }
                    System.out.println("新书价格："+book.getFixedprice());
                    newResource.setForeignId(book.getId());
                    newResource.setOrgCode("ZYDD");
                    newResource.setSupplierId("f8ad206116ec46dfa394ba5a45f39455");
                    subjectResourceService.updateCurrent(ytsResource,newResource);

                }else
                {
                    SubjectResourceEntity subjectResourceEntity =subjectResourceService.selectByCode(book.getBookcode());
                    if(subjectResourceEntity==null){
                        subjectResourceEntity=new SubjectResourceEntity();
                        subjectResourceEntity.setResourceScope(Constant.RESOURCE_SCOPE.ALL);
                        subjectResourceEntity.setIsShow(Constant.RESOURCE_SHOW.SHOW);
                    }
                    subjectResourceEntity.setForeignId(book.getId());
                    subjectResourceEntity.setPrice(book.getFixedprice().doubleValue());
                    subjectResourceEntity.setResourceCode(book.getBookcode());
                    subjectResourceEntity.setResourceName(book.getBookname());
                    subjectResourceEntity.setResourceVersion(book.getVandpno());
                    subjectResourceEntity.setAuthor(book.getAuthorname());
                    subjectResourceEntity.setIsbn(book.getIsbncode());
                    subjectResourceEntity.setPublishDate(book.getPubdate());
                    subjectResourceEntity.setBcBs(book.getPerpackbooks());
                    if(StringUtils.isNotBlank(book.getInvclassname())){
                        SubjectResourceTypeEntity subjectResourceTypeEntity=subjectResourceTypeService.selectByName(book.getInvclassname());
                        if(subjectResourceTypeEntity==null){
                            subjectResourceTypeEntity=new SubjectResourceTypeEntity();
                            subjectResourceTypeEntity.setName(book.getInvclassname());
                            subjectResourceTypeService.insert(subjectResourceTypeEntity);
                        }
                        subjectResourceEntity.setCatalogId(subjectResourceTypeEntity.getId());
                    }else{
                        subjectResourceEntity.setCatalogId(null);
                    }
                    if("文字主".equals(book.getTextbooktypename())){
                        subjectResourceEntity.setResourceType(Constant.RESOURCE_TYPE.ZHU);
                    }else if("文字辅".equals(book.getTextbooktypename())){
                        subjectResourceEntity.setResourceType(Constant.RESOURCE_TYPE.FU);
                    }else if("其他".equals(book.getTextbooktypename())){
                        subjectResourceEntity.setResourceType(Constant.RESOURCE_TYPE.OTHER);
                    }
                    /*if("T".equals(book.getVisibleflag())){
                        subjectResourceEntity.setIsShow(Constant.RESOURCE_SHOW.SHOW);
                    }else{
                        subjectResourceEntity.setIsShow(Constant.RESOURCE_SHOW.NOT_SHOW);
                    }*/
                    subjectResourceEntity.setCreateTime(new Date());
                    subjectResourceEntity.setUpdateTime(new Date());
                    subjectResourceEntity.setOrgCode("ZYDD");
                    subjectResourceEntity.setSupplierId("f8ad206116ec46dfa394ba5a45f39455");
                    subjectResourceService.insertOrUpdateAllColumn(subjectResourceEntity);
                   /* SubjectResourceEntity t = subjectResourceService.selectByCode(subjectResourceEntity.getResourceCode());
                    if (t != null) {
                        subjectResourceEntity.setId(t.getId());
                        //  MyBeanUtils.copyBeanNotNull2Bean(subjectResourceEntity, t);
                        subjectResourceService.updateById(subjectResourceEntity);
                    } else {
                        subjectResourceService.insert(subjectResourceEntity);
                    }*/
                }
                message+="成功";
                responseBook.setId(book.getId());

            }

            logger.info("NC保存教材:send{},sn={}", responseBookInfos, l);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            responseBook.setResult("失败");
            e.printStackTrace();
            message+="失败";
        }
        //systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        xstream.alias("datainfos", ResponseBookInfos.class);
        return xstream.toXML(responseBookInfos);
}

    /**
     * 书店发书
     * @param xml
     * @return
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    //@SysLog("NC书店发书")
    public String saveShip(String xml) {
        String message="书店发书";
        long l = System.currentTimeMillis();
        XStream xstream=XStreamUtil.getInstance();
        xstream.alias("datainfos", OrderbillDataInfo.class);
        OrderbillDataInfo orderbillDataInfo = new OrderbillDataInfo();
        try {
            orderbillDataInfo = (OrderbillDataInfo) xstream.fromXML(xml);
            logger.info("NC书店发书:receive{},sn={}", orderbillDataInfo, l);
//        ResponseOrderbillInfos responseOrderbillInfos=new ResponseOrderbillInfos();
//        OrderbillList orderbillList=new OrderbillList();
//        List<Orderbill> list=new ArrayList<>();
            for(Orderbill orderbill:orderbillDataInfo.getDatainfo().getOrderbill())
            {
                String bid=orderbill.getBid();
                ZdSourceResourceEntity zdSourceResourceEntity=zdSourceResourceService.selectById(bid);
                if(zdSourceResourceEntity!=null)
                {

                    orderbill.setResult("成功");
                    orderbill.setReason("成功");
                    zdSourceResourceEntity.setNitemdiscountrate(BigDecimal.valueOf(orderbill.getNitemdiscountrate()));
                   // zdOrderResourceEntity.setResult(orderbill.getResult());
                   // zdOrderResourceEntity.setReason(orderbill.getReason());
                    zdSourceResourceEntity.setStatus(orderbill.getStatus());
                    zdSourceResourceService.updateById(zdSourceResourceEntity);
                }else
                {
                    orderbill.setResult("失败");
                    orderbill.setReason("表体不存在");
                }

    //            list.add(orderbill);
            }

            message+="成功";
            logger.info("NC书店发书:{},sn={}", orderbillDataInfo, l);
        } catch (Exception e) {
            message+="失败";
            logger.error(e.getMessage(), e);
            e.printStackTrace();

        }
      //  systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        return xstream.toXML(orderbillDataInfo);
    }


    /**
     * 发货回告
     * @param xml
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    //@SysLog("NC发货回告")
    public String shipOrder(String xml) {
        String message="发货回告";
        long l = System.currentTimeMillis();
        XStream xstream=XStreamUtil.getInstance();
        xstream.alias("datainfos", OrderbillDataInfo.class);
        OrderbillDataInfo orderbillDataInfo = new OrderbillDataInfo();
      //  ZdStockIncomeEntity zdStockIncomeEntity=new ZdStockIncomeEntity();
        List<ZdStockIncomeResourceEntity> itemsEntityList=new ArrayList<>();
        Map<String,Object> incomeMap=new HashMap<>();
        try {
            String timeInSecond=DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
            orderbillDataInfo = (OrderbillDataInfo) xstream.fromXML(xml);
            logger.info("NC发货回告:receive{},sn={}", orderbillDataInfo, l);
            if("save".equals(orderbillDataInfo.getOperate()))
            {
                for(Orderbill orderbill:orderbillDataInfo.getDatainfo().getOrderbill())
                {
                    String bid=orderbill.getBid();
                    ZdSourceResourceEntity zdSourceResourceEntity=zdSourceResourceService.selectById(bid);
                    if(zdSourceResourceEntity!=null)
                    {
                        zdSourceResourceEntity.setCarrierno(orderbill.getCarrierno());
                        zdSourceResourceEntity.setStatus(Constant.SOURCE_RESOURCE_STATUS.SHIP_REPLY);
                        zdSourceResourceService.updateById(zdSourceResourceEntity);
                      //  ZdStockIncomeResourceEntity zdStockIncomeResourceEntity=new ZdStockIncomeResourceEntity();
                        orderbill.setResult("成功");
                        orderbill.setReason("操作成功");


                    }else
                    {
                        orderbill.setResult("失败");
                        orderbill.setReason("表体不存在");
                    }
                    if(incomeMap.get(orderbill.getErpcode())==null){
                        incomeMap.put(orderbill.getErpcode(),1);
                        List<ZdStockIncomeEntity> incomeEntities=zdStockIncomeService.selectList(new EntityWrapper<ZdStockIncomeEntity>().eq("erpcode",orderbill.getErpcode()));
                        if(incomeEntities.size()>0){
                            List<String> ids=incomeEntities.stream().map(ZdStockIncomeEntity::getId).collect(Collectors.toList());
                            ZdStockIncomeEntity zdStockIncomeEntity=new ZdStockIncomeEntity();
                            zdStockIncomeEntity.setCarrierno(orderbill.getCarrierno());
                            zdStockIncomeEntity.setSendDate(DateUtils.stringToDate(orderbill.getSenddate(),DateUtils.DATE_TIME_PATTERN));
                            zdStockIncomeEntity.setNum(orderbill.getNum());
                            zdStockIncomeService.update(zdStockIncomeEntity,new EntityWrapper<ZdStockIncomeEntity>().in("id_",ids));
                        }

                    }
                }

            }
           else if("update".equals(orderbillDataInfo.getOperate()))
            {
                for(Orderbill orderbill:orderbillDataInfo.getDatainfo().getOrderbill())
                {
                    //    zdStockIncomeEntity.setCarrierno(orderbill.getCarrierno());
                    String bid=orderbill.getBid();
                    ZdSourceResourceEntity zdSourceResourceEntity=zdSourceResourceService.selectById(bid);
                    if(zdSourceResourceEntity!=null)
                    {
                        zdSourceResourceEntity.setCarrierno(orderbill.getCarrierno());
                        zdSourceResourceEntity.setStatus(orderbill.getStatus());
                        zdSourceResourceService.updateById(zdSourceResourceEntity);
                        orderbill.setResult("成功");
                        orderbill.setReason("操作成功");
                    }else
                    {
                        orderbill.setResult("失败");
                        orderbill.setReason("表体不存在");
                    }
                    if(incomeMap.get(orderbill.getErpcode())==null){
                        incomeMap.put(orderbill.getErpcode(),1);
                        List<ZdStockIncomeEntity> incomeEntities=zdStockIncomeService.selectList(new EntityWrapper<ZdStockIncomeEntity>().eq("erpcode",orderbill.getErpcode()));
                        if(incomeEntities.size()>0){
                            List<String> ids=incomeEntities.stream().map(ZdStockIncomeEntity::getId).collect(Collectors.toList());
                            ZdStockIncomeEntity zdStockIncomeEntity=new ZdStockIncomeEntity();
                            zdStockIncomeEntity.setCarrierno(orderbill.getCarrierno());
                            zdStockIncomeEntity.setSendDate(DateUtils.stringToDate(orderbill.getSenddate(),DateUtils.DATE_TIME_PATTERN));
                            zdStockIncomeEntity.setNum(orderbill.getNum());
                            zdStockIncomeService.update(zdStockIncomeEntity,new EntityWrapper<ZdStockIncomeEntity>().in("id_",ids));
                        }

                    }
                }
            }else
            {
                for(Orderbill orderbill:orderbillDataInfo.getDatainfo().getOrderbill())
                {
                    //    zdStockIncomeEntity.setCarrierno(orderbill.getCarrierno());
                    String bid=orderbill.getBid();
                    ZdSourceResourceEntity zdSourceResourceEntity=zdSourceResourceService.selectById(bid);
                    if(zdSourceResourceEntity!=null)
                    {
                        zdSourceResourceEntity.setCarrierno(orderbill.getCarrierno());
                        zdSourceResourceEntity.setEnable(0);
                        zdSourceResourceEntity.setDelFlag("1");
                        zdSourceResourceEntity.setStatus(Constant.SOURCE_RESOURCE_STATUS.DELETE);
                        zdSourceResourceService.updateById(zdSourceResourceEntity);
                        orderbill.setResult("成功");
                        orderbill.setReason("操作成功");
                    }else
                    {
                        orderbill.setResult("失败");
                        orderbill.setReason("表体不存在");
                    }

                }
            }
            message+="成功";
            logger.info("NC发货回告:{},sn={}", orderbillDataInfo, l);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            message+="失败";
        }
      //  systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        return xstream.toXML(orderbillDataInfo);
    }

    /**
     * 保存客商账户
     * @param xml
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    //@SysLog("NC保存客商")
    public String saveCustomer(String xml) {
        String message="NC保存客商";
        long l = System.currentTimeMillis();
        XStream xstream=XStreamUtil.getInstance();
        xstream.alias("datainfos", CustomerInfos.class);
        CustomerInfos   orderbillDataInfo=new CustomerInfos();
        Customer customer= new Customer();
        customer.setResult("成功");
        message+="成功";
        try {
            orderbillDataInfo = (CustomerInfos) xstream.fromXML(xml);
            logger.info("NC保存客商:receive{},sn={}", JSON.toJSONString(orderbillDataInfo), l);
            customer = orderbillDataInfo.getCust();
            sysOrgService.saveOrUpdateCustomer(customer);
            customer.setResult("成功");
            message+="成功";
            logger.info("NC保存客商:send{},sn={}", JSON.toJSONString(orderbillDataInfo), l);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            customer.setResult("失败");
            customer.setReason(e.getMessage());
            message+="失败";
        }
        orderbillDataInfo.setCust(customer);
      //  systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        return xstream.toXML(orderbillDataInfo);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    //@SysLog("NC删除订单")
    public String delOrder(String xml) {
        String message="NC删除订单";
        long l = System.currentTimeMillis();
        XStream xstream=XStreamUtil.getInstance();
        xstream.alias("datainfos", OrderbillDataInfo.class);
        OrderbillDataInfo orderbillDataInfo=new OrderbillDataInfo();
        try {
            orderbillDataInfo = (OrderbillDataInfo) xstream.fromXML(xml);
            for(Orderbill orderbill:orderbillDataInfo.getDatainfo().getOrderbill()) {
                String sourceId=orderbill.getId();
                String sourceResourceId=orderbill.getBid();
                int count=zdSourceResourceService.selectLeftCount(sourceId);
                if(count==0){
                    ZdSourceEntity zdSourceEntity=zdSourceService.selectBySourceNo(sourceId);
                    if(zdSourceEntity!=null)
                    {
                        zdSourceEntity.setIsSync("3");
                        zdSourceService.updateById(zdSourceEntity);
                        orderbill.setResult("成功");
                    }else
                    {
                        orderbill.setResult("失败");
                        orderbill.setReason("未查到相关订单");
                    }

                }
                ZdSourceResourceEntity zdSourceResourceEntity=new ZdSourceResourceEntity();
                zdSourceResourceEntity.setId(sourceResourceId);
                zdSourceResourceEntity.setDelFlag("1");
                zdSourceResourceEntity.setStatus(Constant.SOURCE_RESOURCE_STATUS.DELETE);
                zdSourceResourceEntity.setUpdateTime(new Date());
                boolean delPass=zdSourceResourceService.updateById(zdSourceResourceEntity);
                if(delPass){
                    orderbill.setResult("成功");
                }else{
                    orderbill.setResult("失败");
                }
            }
            logger.info("NC删除预订单:send{},sn={}", orderbillDataInfo, l);
            message+="成功";
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            message+="失败";
        }
      //  systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        return xstream.toXML(orderbillDataInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    //@SysLog("NC退货回告")
    public String saveRefund(String xml) {
        String message="退货回告";
        long l = System.currentTimeMillis();
        logger.info("NC退货回告接收:receive{},sn={}", xml, l);
        XStream xstream=XStreamUtil.getInstance();
        xstream.alias("datainfos", BillhVODataInfo.class);
        xstream.alias("billhvo", BillhVO.class);
        BillhVODataInfo billhVODataInfo=new BillhVODataInfo();
        BillhVO billhVO=new  BillhVO();
        try {
              billhVODataInfo = (BillhVODataInfo) xstream.fromXML(xml);
                billhVO =billhVODataInfo.getBillhvo();
                ZdRefundSupplierEntity zdRefundEntity=zdRefundSupplierService.selectById(billhVO.getId());
            for(Book book:billhVO.getChildren().getBook())
            {

                ZdRefundSupplierResourceEntity zdRefundResourceEntity=zdRefundSupplierResourceService.selectById(book.getBid());
               // TShopRefundItemEntity tShopRefundItemEntity=systemService.getEntity(TShopRefundItemEntity.class,book.getBid());
                if(zdRefundResourceEntity!=null)
                {
                    zdRefundResourceEntity.setRefundNum(book.getNdecimal());
                   // zdRefundResourceEntity.setw(book.getNwastnum());
                    zdRefundResourceEntity.setRealNum(book.getNhaoshunum());
                    zdRefundResourceEntity.setCsNum(book.getNwastnum());
                    zdRefundResourceEntity.setNitemdiscountrate(BigDecimal.valueOf(book.getNitemdiscountrate()));
                    zdRefundResourceEntity.setStatus(Constant.NC_REFUND_RESOURCE_STATUS.SHIP);
                    zdRefundSupplierResourceService.updateById(zdRefundResourceEntity);
                }

            }
            zdRefundEntity.setAvgdiscount(new BigDecimal(billhVO.getAvgdiscount()));
            zdRefundEntity.setShiyang(new BigDecimal(billhVO.getTotalshiyang()));
            zdRefundEntity.setNcStatus(Constant.NC_REFUND_RESOURCE_STATUS.SHIP);
            zdRefundSupplierService.updateById(zdRefundEntity);
            message+="成功";
            billhVO.setResult("成功");
            billhVO.setReason("");
            billhVODataInfo.setSendtime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            logger.info("NC退货回告发送:send{},sn={}", xstream.toXML(billhVODataInfo), l);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            message+="失败";
            billhVO.setResult("失败");
            billhVO.setReason("异常");
            e.printStackTrace();
        }

       // systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        return xstream.toXML(billhVODataInfo);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
//    //@SysLog("NC发货单入库")
    public String saveIncome(String xml) {
        long l = System.currentTimeMillis();
        XStream xstream=XStreamUtil.getInstance();
        xstream.alias("datainfos", BillhVODataInfo.class);
        xstream.alias("billhvo", BillhVO.class);
        logger.info("NC发货单入库:receive{},sn={}", xml, l);
        BillhVODataInfo billhVODataInfo=new BillhVODataInfo();
        BillhVO billhVO=new  BillhVO();
        try {
            billhVODataInfo = (BillhVODataInfo) xstream.fromXML(xml);
            billhVO =billhVODataInfo.getBillhvo();
           // String customerCode=billhVO.getCustcode();

           ZdSourceEntity zdSourceEntity=zdSourceService.selectBySourceNo(billhVO.getSourceno());
           if(zdSourceEntity==null){
               billhVO.setResult("失败");
               billhVO.setReason("未找到采购单！");
               billhVODataInfo.setSendtime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
               return xstream.toXML(billhVODataInfo);
           }
            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(zdSourceEntity.getOrgCode());
//            ZdStockIncomeEntity
           /*if(sysOrgEntity==null)
            {
                message+="失败";
                billhVO.setResult("未找到相关订书依据的单位");
                billhVO.setReason("");
                billhVODataInfo.setSendtime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
                return xstream.toXML(billhVODataInfo);
            }*/
//            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester(zdSourceEntity.getOrgCode(),sysOrgEntity.getParentCodes());
            ZdSourceIncomeForm zdSourceIncomeForm=new ZdSourceIncomeForm();
            zdSourceIncomeForm.setSemesterCode(zdSourceEntity.getSemesterCode());
            zdSourceIncomeForm.setSupplierId("f8ad206116ec46dfa394ba5a45f39455");
            zdSourceIncomeForm.setErpcode(billhVO.getErpcode());
            zdSourceIncomeForm.setForeignId(billhVO.getId());
            zdSourceIncomeForm.setStaplerbasis(zdSourceEntity.getToOrgCode());
            List<ZdSourceResourceIncomeForm> zdSourceResourceIncomeForms=new ArrayList<>();
            for(Book book:billhVO.getChildren().getBook())
            {
                ZdSourceResourceIncomeForm zdSourceResourceIncomeForm=new ZdSourceResourceIncomeForm();
                zdSourceResourceIncomeForm.setNum(book.getNdecimal());
                zdSourceResourceIncomeForm.setResourceCode(book.getBookcode());
                zdSourceResourceIncomeForm.setMayang(BigDecimal.valueOf(book.getMayang()));
                zdSourceResourceIncomeForm.setShiyang(BigDecimal.valueOf(book.getShiyang()));
                zdSourceResourceIncomeForm.setNitemdiscountrate(BigDecimal.valueOf(book.getNitemdiscountrate()));
                zdSourceResourceIncomeForm.setIncomePrice(BigDecimal.valueOf(book.getNprice()));
                zdSourceResourceIncomeForm.setForeignId(book.getBid());
                zdSourceResourceIncomeForm.setFxwbids(book.getFxwbids());
                zdSourceResourceIncomeForms.add(zdSourceResourceIncomeForm);
                String[] sourceResourceIds=book.getFxwbids().split(",");
                if(sourceResourceIds.length>0){
                    ZdSourceResourceEntity zdSourceResourceEntity=new ZdSourceResourceEntity();
                    zdSourceResourceEntity.setStatus(Constant.SOURCE_RESOURCE_STATUS.SHIP_READY);
                    zdSourceResourceService.update(zdSourceResourceEntity,new EntityWrapper<ZdSourceResourceEntity>().in("id_",sourceResourceIds));

                }

            }
            zdSourceIncomeForm.setIncomeForms(zdSourceResourceIncomeForms);
            zdStockIncomeService.saveIncomeByNC(zdSourceIncomeForm,sysOrgEntity);
            billhVO.setResult("成功");
            billhVO.setReason("");
            billhVODataInfo.setSendtime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            logger.info("NC发货单入库:send{},sn={}", billhVO, l);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            billhVO.setResult("失败");
            billhVO.setReason("异常");
            e.printStackTrace();
        }

        // systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        return xstream.toXML(billhVODataInfo);
    }

}
