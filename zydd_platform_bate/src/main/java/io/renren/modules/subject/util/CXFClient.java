package io.renren.modules.subject.util;

import io.renren.common.exception.RRException;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;


public class CXFClient {

    private static JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
    private static Client client=null;
    public static Client getInstance(String url){
        try {
        if (client == null) {
            client= clientFactory.createClient(url);
                HTTPConduit http = (HTTPConduit) client.getConduit();
                HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
                httpClientPolicy.setConnectionTimeout(30000);  //连接超时
                httpClientPolicy.setAllowChunking(false);    //取消块编码
                httpClientPolicy.setReceiveTimeout(200000);     //响应超时
                http.setClient(httpClientPolicy);
        }
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("同步失败!无法找到NC服务！");
        }
    }

    public static void main(String[] args) throws Exception {
        Object[] res = CXFClient.getInstance("").invoke("delOrder", "<?xml version='1.0' encoding='UTF-8'?><datainfos datasource=\"DDZS\" syscode=\"1001\" company=\"1001\" sender=\"0001X610000000005MFT\" operate=\"delete\" sendtime=\"2018-04-25 11:17:13\"><orderbill><id>CGJSDD20180424230022</id></orderbill></datainfos>");
        System.out.println(res[0]);
    }

}
