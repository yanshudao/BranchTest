package io.renren.modules.subject.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DoubleConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import com.thoughtworks.xstream.converters.basic.LongConverter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import io.renren.common.utils.DateUtils;
import io.renren.modules.subject.entity.BaseInfos;
import io.renren.modules.subject.entity.request.OrderbillDataInfo;
import org.springframework.util.StringUtils;

import java.util.Date;

public class XStreamUtil {

    /**
     * Java对象转Xml字符串（序列化）
     * @param object
     * @return
     */
    public static XStream getInstance()
    {
        XStream xstream = new XStream(new StaxDriver());
        xstream.registerConverter(new DoubleConverter()
        {

            @Override
            public Object fromString(String str) {
                if (StringUtils.isEmpty(str)) {
                    return null;
                }
                return super.fromString(str);
            }
        });
        xstream.registerConverter(new LongConverter()
        {

            @Override
            public Object fromString(String str) {
                if (StringUtils.isEmpty(str)) {
                    return null;
                }
                return super.fromString(str);
            }
        });
        xstream.registerConverter(new IntConverter()
        {

            @Override
            public Object fromString(String str) {
                if (StringUtils.isEmpty(str)) {
                    return null;
                }
                return super.fromString(str);
            }
        });
        //引入一行代码，进行注解的检测
        xstream.autodetectAnnotations(true);

//        xstream.ignoreUnknownElements();
        return xstream;
    }

    public static void main(String[] args)
    {
        OrderbillDataInfo baseInfos=new OrderbillDataInfo();
        baseInfos.setSendtime(DateUtils.format(new Date()));
        System.out.println(XStreamUtil.getInstance().toXML(baseInfos));
    }
}