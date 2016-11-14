package netease.sre;

import com.google.common.base.Stopwatch;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by lyp on 2016/11/14.
 */
public class DataStreamConsumer {
    public void doTeest() {
        // Kafka-0.9 消费者参数说明：https://kafka.apache.org/090/documentation.html#newconsumerconfigs

        Properties props = new Properties();
        // Kafka集群地址
        props.put("bootstrap.servers", "datastream0.lt.163.org:2181,datastream1.lt.163.org:2181,datastream2.lt.163.org:2181/kafka_lt_online");
        // 消费组
        props.put("group.id", "test");
        // 是否自动提交Offset
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        // 订阅的Topic名称
        consumer.subscribe(Collections.singletonList("datastream.-_-.appops.httpdns_server.1"));

        Stopwatch stopwatch = Stopwatch.createStarted();
        while(stopwatch.elapsed(TimeUnit.SECONDS) < 5) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            System.out.println("Pulled " + records.count() + " records");
            System.out.println("cpu count  " + Runtime.getRuntime().availableProcessors());

            for (ConsumerRecord<String, String> record: records) {
                System.out.println("offset = " + record.offset() + ", key=" + record.key() + ", value = " +
                        record.value() + ", topic = " + record.topic() + ",partition = " + record.partition( ));
            }
        }
    }

    public void doPrint() {
        System.out.println("hello to see you");
    }
}
