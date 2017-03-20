package com.test.ops;

import java.util.*;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;



/**
  server.  kafka_2.11-0.8.2.1/config/server.properties
  message.max.bytes=10485760
replica.fetch.max.bytes=10485760   修改消息包大小。
 * Created by hzchenkj on 2017/3/17.
 */
public class TestProducer {
    public static void main(String[] args) {
        Random rnd = new Random();

        Properties props = new Properties();
        props.put("metadata.broker.list", "192.168.3.41:9092,192.168.3.42:9092,192.168.3.43:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");

        ProducerConfig config = new ProducerConfig(props);

        Producer<String, String> producer = new Producer<String, String>(config);

            long runtime = new Date().getTime();
            String ip = "192.168.3.41";
            StringBuffer msg = new StringBuffer(runtime + ",www.example.com," + ip);

            for (int i=0;i<20000;i++){
                msg.append("ddddddddkkdkkkkkkkjjjjhhjiuddddddddkkdkkkkkkkjjjjhhjiuddddddddkkdkkkkkkkjjjjhhjiuddddddddkk" +
                        "dkkkkkkkjjjjhhjiuddddddddkkdkkkkkkkjjjjhhjiuddddddddkkdkkkkkkkjjjjhhjiuddddddddkkdkkkkkkkjjjjhhjiuddddddddkkdkkkkkkkjjjjhhjiu");
            }
            System.out.println(msg.toString().length());
        System.out.println(msg.toString().getBytes().length);
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("test.topic1", ip, msg.toString());
            producer.send(data);
        producer.close();
    }
}
