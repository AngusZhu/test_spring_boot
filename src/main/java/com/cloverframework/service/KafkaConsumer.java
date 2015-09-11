package com.cloverframework.service;

import com.cloverframework.utils.KafkaSetting;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaConsumer extends Thread
{
    private final ConsumerConnector consumer;
    private final String topic;

    @Autowired
    private KafkaSetting kafkaSetting;

    public KafkaConsumer(String topic)
    {
        consumer =  kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
        this.topic = topic;
    }
    private ConsumerConfig createConsumerConfig()
    {
        Properties props = new Properties();
        props.put("zookeeper.connect", kafkaSetting.getZkConnect());
        props.put("group.id", kafkaSetting.getGroupId());
        props.put("zookeeper.session.timeout.ms", kafkaSetting.getConnectTimeout());
        props.put("zookeeper.sync.time.ms", kafkaSetting.getReconnectInterval());
        props.put("auto.commit.interval.ms",  kafkaSetting.getReconnectInterval());
        return new ConsumerConfig(props);
    }
    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            System.out.println("receive£º" + new String(it.next().message()));
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}