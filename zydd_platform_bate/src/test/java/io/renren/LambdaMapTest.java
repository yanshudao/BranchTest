package io.renren;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LambdaMapTest {

    private Map<String, Object> map = new HashMap<>();

    @Before
    public void initData() {
        for (int i = 1; i<1000;++i){
            map.put(String.valueOf(i), i);
        }
    }

    /**
     * 遍历Map的方式一
     * 通过Map.keySet遍历key和value
     */
    @Test
    public void testOne() {
        System.out.println("---------------------Before JAVA8 -----------------------");
        for (String key : map.keySet()) {
            System.out.println("map.get(" + key + ") = " + map.get(key));
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        map.keySet().forEach(key -> System.out.println("map.get(" + key + ") = " + map.get(key)));
    }

    /**
     * 遍历Map第二种
     * 通过Map.entrySet使用Iterator遍历key和value
     */
    @Test
    public void testTwo() {
        System.out.println("---------------------Before JAVA8 -------------------------");
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("---------------------JAVA8 --------------------------------");
        map.entrySet().iterator().forEachRemaining(item -> System.out.println("key:value=" + item.getKey() + ":" + item.getValue()));
    }

    /**
     * 遍历Map第三种
     * 通过Map.entrySet遍历key和value，在大容量时推荐使用
     */
    @Test
    public void testThree() {
        System.out.println("---------------------Before JAVA8 ---------------------------");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        long time = System.currentTimeMillis();
        System.out.println(time);
        map.entrySet().forEach(entry -> System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue()));
        System.out.println("遍历完花费总时间为："+(System.currentTimeMillis()-time));
    }

    /**
     * 遍历Map第四种
     * 通过Map.values()遍历所有的value，但不能遍历key
     */
    @Test
    public void testFour() {
        System.out.println("---------------------Before JAVA8 -----------------------------");
        for (Object value : map.values()) {
            System.out.println("map.value = " + value);
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        map.values().forEach(System.out::println); // 等价于map.values().forEach(value -> System.out.println(value));
    }

    /**
     * 遍历Map第五种
     * 通过k,v遍历，Java8独有的
     */
    @Test
    public void testFive() {
        System.out.println("---------------------Only JAVA8 ------------------------------");
        long time = System.currentTimeMillis();
        System.out.println(time);
        map.forEach((k, v) -> {System.out.println("key:value = " + k + ":" + v);});
        System.out.println("遍历完花费总时间为："+(System.currentTimeMillis()-time));
    }
}