/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.renren.common.utils;

import io.renren.common.exception.RRException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Map工具类
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0
 */
public class MapUtils<T> extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuilder(strLen)
                .append(Character.toTitleCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }

    public List<T> ListMap2JavaBean(List<Map<String, Object>> datas, Class<T> beanClass) throws RRException {
        // 返回数据集合
        List<T> list = null;
        // 对象字段名称
        String fieldname = "";
        // 对象方法名称
        String methodname = "";
        // 对象方法需要赋的值
        Object methodsetvalue = "";
        try {
            list = new ArrayList<T>();
            // 得到对象所有字段
            Field fields[] = beanClass.getDeclaredFields();
            // 遍历数据
            for (Map<String, Object> mapdata : datas) {
                // 创建一个泛型类型实例
                T t = beanClass.newInstance();
                // 遍历所有字段，对应配置好的字段并赋值
                for (Field field : fields) {
                    if(null != field) {
                        // 全部转化为大写
                        String dbfieldname = field.getName().toUpperCase();
                        // 获取字段名称
                        fieldname = field.getName();
                        // 拼接set方法
                        methodname = "set" + capitalize(fieldname);
                        // 获取data里的对应值
                        methodsetvalue = mapdata.get(dbfieldname);
                        // 赋值给字段
                        Method m = beanClass.getDeclaredMethod(methodname, field.getType());
                        m.invoke(t, methodsetvalue);
                    }
                }
                // 存入返回列表
                list.add(t);
            }
        } catch (InstantiationException e) {
            throw new RRException("创建beanClass实例异常");
        } catch (IllegalAccessException e) {
            throw new RRException("创建beanClass实例异常");
        } catch (SecurityException e) {
            throw new RRException("获取[" + fieldname + "] getter setter 方法异常");
        } catch (NoSuchMethodException e) {
            throw new RRException("获取[" + fieldname + "] getter setter 方法异常");
        } catch (IllegalArgumentException e) {
            throw new RRException("[" + methodname + "] 方法赋值异常");
        } catch (InvocationTargetException e) {
            throw new RRException("[" + methodname + "] 方法赋值异常");
        }
        // 返回
        return list;
    }
    public static Map<String, Object> convertBeanToMap(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    public static List<Map<String, Object>> convertListBeanToListMap(List<Object> beanList) throws Exception {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (int i = 0, n = beanList.size(); i < n; i++) {
            Object bean = beanList.get(i);
            Map map = convertBeanToMap(bean);
            mapList.add(map);
        }
        return mapList;
    }

    /*public static Map<String, Object> listToMap(List<?> list, String getkey) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < list.size(); i++) {
            Class cl = list.get(i).getClass();
            Method m1 = cl.getDeclaredMethod(getkey);
            String dvcd = (String) m1.invoke(list.get(i));
            map.put(dvcd, list.get(i));

        }
        return map;
    }*/

}
