package io.renren.common.utils;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 常量
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {

    public static final String ALLOW_REFUND_KEY="ZD_ORG_REFUND";
    public static final BigDecimal DEFAULT_REFUND=new BigDecimal("5.00");
    public static final String ZYDD_SUPPILER_ID="f8ad206116ec46dfa394ba5a45f39455";
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;
    /** 缓存命名空间 */
    public static final String CACHE_NAMESPACE = "ZYDD_PLATFORM:";


    /**
	 * 菜单类型11
	 * 
	 * @author chenshun
	 * @email sunlightcs@gmail.com
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     * 
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    public interface COMMON_STATUS {
        /**
         * 日志表状态，初始状态，插入
         */
        public static final String CLOSE = "0";
        /**
         * 日志表状态，成功
         */
        public static final String OPEN = "1";

    }
    public interface ORG_TYPE{
        String ZY="0";
        String SHENG="1";
        String SHI="2";
        String XIAN="3";
        String GYS="4";
    }
    public interface ORDER_STATUS {
        /**
         * 日志表状态，初始状态，插入
         */
        /**
         * 日志表状态，成功
         */
        public static final String NEW = "1";
        public static final String CONFIRM = "2";
        public static final String COMPLETE = "3";

    }
    public interface PUBLISH_STATUS {

        public static final String NEW = "1";
        public static final String CONFIRM = "2";
        public static final String COMPLETE = "3";
        public static final String FINISH = "4";
        public static final String REJECT = "5";
    }
    public interface REFUND_ORG_STATUS {

        public static final String NEW = "1";
        public static final String CONFIRM = "2";
        public static final String COMPLETE = "3";
        public static final String FINISH = "4";
        public static final String AUDIT_PASS = "5";
        public static final String AUDIT_FAIL = "6";
        public static final String SHIP = "7";

    }
    public interface REFUND_STATUS {

        public static final String NEW = "1";
        public static final String CONFIRM = "2";
        public static final String COMPLETE = "3";
        public static final String FINISH = "4";
        public static final String AUDIT_PASS = "5";
        public static final String AUDIT_FAIL = "6";
//        public static final String SHIP = "7";

    }
    public interface SOURCE_STATUS {

        public static final String NEW = "0";//县级新增
        public static final String SUBMIT= "1";//上报
        public static final String COMPLETE = "2";//完成
        public static final String REJECT = "3";//审核拒绝

    }
    public interface SOURCE_RESOURCE_STATUS {

        public static final String NEW = "0";//新建
        public static final String SHIP_SUBMIT= "1";//已报订
        public static final String DELETE= "4";//删除
        public static final String SHIP_READY = "2";//配货中
        public static final String SHIP_REPLY = "3";//发货回告

    }
    public interface SOURCE_TYPE {
        /**
         * 1 报订采购 2 报订转采购
         */

        public static final String RC = "1";
        public static final String ZD_CG = "2";

    }
    public interface STOCK_STATUS {
        /**
         * 1 增加 2 减少
         */

        public static final String FINISH = "4";
        public static final String SURE = "2";
        public static final String AUDIT = "1";
        public static final String NEW = "0";

    }
    public interface STOCK_RECORD_TYPE {
        /**
         * 1 增加 2 减少
         */

        public static final String INCOME = "1";
        public static final String PUBLISH = "2";
        public static final String PUBLISH_REJECT = "5";
        public static final String REFUND_ORG = "3";
        public static final String REFUND_SUPPIER = "4";

    }public interface DELETE_FLAG {
        /**
         * 1 增加 2 减少
         */

        public static final String NOMAIL = "0";
        public static final String DELETE = "1";

    }


    public interface NC_REFUND_RESOURCE_STATUS{
        public static final String SHIP="3";
        public static final String NEW="1";
    }
    public interface PUBLISH_SOURCE_TYPE{
        public static final String INCOME="income";
    }
    public interface RESOURCE_TYPE{
        public static final String ZHU="1";
        public static final String FU="2";
        public static final String OTHER="3";
    }
    public interface RESOURCE_SCOPE{
        public static final Integer CUSTOM=1;
        public static final Integer ALL=0;
    }
   public interface RESOURCE_SHOW{
        public static final Integer SHOW=1;
        public static final Integer NOT_SHOW=0;
    }
    public interface ZMCR_ARCHIVES{
        public static final Integer ARCHIVES=1;
        public static final Integer NOT_ARCHIVES=0;
    }
    public  static BidiMap<String,String> majorTypeMap=null;
    public  static BidiMap<String,String> studentTypeMap=null;
    public  static BidiMap<String,String> resourceType2Map=null;
    static
    {
        majorTypeMap = new DualHashBidiMap<>();
        majorTypeMap.put("2", "本科");
        majorTypeMap.put("4", "专科");
        majorTypeMap.put("5", "本科（专科起点）");
        majorTypeMap.put("6", "本科（高中起点）");
        majorTypeMap.put("8", "中专");
        studentTypeMap = new DualHashBidiMap<>();
        studentTypeMap.put("01","开放");
        studentTypeMap.put("02","普招");
        studentTypeMap.put("03","成招");
        studentTypeMap.put("04","一村一");
        studentTypeMap.put("05","课程开放");
        studentTypeMap.put("06","助力计划");
        studentTypeMap.put("07","农民大学生");
        studentTypeMap.put("08","专本衔接");
        studentTypeMap.put("09","中高职衔接");
        studentTypeMap.put("10","乡村教师学历提升计划");
        studentTypeMap.put("11","网考");
        studentTypeMap.put("19","中专");
        resourceType2Map= new DualHashBidiMap<>();
        resourceType2Map.put("1","统必主");
        resourceType2Map.put("0","其他");

    }

    public static final String RATE_LOCK="RATE_LOCK";
}
