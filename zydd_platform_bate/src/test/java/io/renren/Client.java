package io.renren;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.ScheduledExecutorService;

public class Client {

    public static final String HOST = "tcp://lbsmqtt.airm2m.com:1884";
    public static final String TOPIC = "/BsStatus/Signal";
    private static final String clientid = "client6";
    private MqttClient client;
    private MqttConnectOptions options;
    private String userName = "";
    private String passWord = "";

    private ScheduledExecutorService scheduler;

    private void start() {
        try {
            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, clientid, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
//            options.setUserName(userName);
            // 设置连接的密码
//            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调
            client.setCallback(new PushCallback());
            MqttTopic topic = client.getTopic(TOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            options.setWill(topic, "close".getBytes(), 2, true);

            client.connect(options);
            //订阅消息
            int[] Qos  = {1};
            String[] topic1 = {TOPIC};
            client.subscribe(topic1, Qos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MqttException {
        String str="http://10.217.8.18:25000/http://localhost:9090/problem-building-overview?pageFrom=NEW_WYPT&amp;token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJub2tpYSIsImV4cCI6MTU5OTYzNTgwMCwidXNlcm5hbWUiOiJsaXhpYW5nIn0.-u8CRMOAqZGs_7gmLir7R9kd0R7NAOPp6tFzHHICX3A";
        System.out.println(str.split("http"));
       /* Client client = new Client();
        client.start();*/
    }
}