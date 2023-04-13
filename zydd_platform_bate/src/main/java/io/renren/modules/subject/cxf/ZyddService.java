package io.renren.modules.subject.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://service.zydd.jeecg.com/")
public interface  ZyddService {
//    public String sayHello(@WebParam(name = "name") String name);

    /**
     * 图书档案接口
     * @param xml
     * @return
     */
    public String saveBook(@WebParam(name = "xml") String xml);



    /**
     * 发货回告
     * @param xml
     * @return
     */
    String shipOrder(@WebParam(name = "xml") String xml);

    /**
     * 保存客商账户
     * @param xml
     * @return
     */
    String saveCustomer(@WebParam(name = "xml") String xml);

    /**
     * 书店发书
     * @param xml
     * @return
     */
    String saveShip(@WebParam(name = "xml") String xml);

    /**
     * NC删除订单
     * @param xml
     * @return
     */
    String delOrder(@WebParam(name = "xml") String xml);

    /**
     * 退货回告
     * @param xml
     * @return
     */
    String saveRefund(@WebParam(name = "xml") String xml);

    /**
     * 发货单入库
     * @param xml
     * @return
     */
    String saveIncome(@WebParam(name = "xml") String xml);

}
