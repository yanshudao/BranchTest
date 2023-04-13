package io.renren.common.utils;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import io.renren.modules.zd.entity.ZdSemesterRegEntity;
import org.apache.commons.lang.StringUtils;

import java.util.Optional;

/**
 * @author by jueyue on 18-8-3.
 */
public class ExcelDictHandlerImpl implements IExcelDictHandler {

    @Override
    public String toName(String dict, Object obj, String name, Object value) {
        if ("studentType".equals(dict)) {
            return Constant.studentTypeMap.get(StringUtils.trim((String)value));
        }
        if ("majorType".equals(dict)) {
            return Constant.majorTypeMap.get(StringUtils.trim((String)value));
        }
        return null;
    }

    @Override
    public String toValue(String dict, Object obj, String name, Object value) {
        if ("studentType".equals(dict)) {
            value=Constant.studentTypeMap.getKey(StringUtils.trim((String)value));
            System.out.println(value);
            return (String)value;
        }
        if ("majorType".equals(dict)) {
            value=Constant.majorTypeMap.getKey(StringUtils.trim((String)value));
            return (String)value;
        }
        return null;
    }
}
