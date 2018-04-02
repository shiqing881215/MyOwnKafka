package ProducerConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.google.common.io.Resources;

public class Consumer {

	public static void main(String[] args) throws IOException {
		// Set up the producer from producer.property file
		// Two parameters are key and value
		KafkaConsumer<String, String> consumer;
		
		// Consumer needs to deserialize
		InputStream pros = Resources.getResource("consumer.property").openStream();
		Properties properties = new Properties();
		properties.load(pros);
		
		consumer = new KafkaConsumer<String, String>(properties);
		
		// Subscribe to both topics
		// Note you need to create these two topics first in Kafka
		// Producer or Consumer just make use of it
		consumer.subscribe(Arrays.asList("fast-topic", "slow-topic"));
		
		while (true) {
			// Fetch data from offset, 100 is timeout
			// It will fetch as many available records as possible
			// This will keep running until you kill it
			// since consumer needs to keep this connection to receive new message
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("Partition: " + record.partition() + " Offset: " + record.offset()
	            + " Value: " + record.value() + " ThreadID: " + Thread.currentThread().getId());
			}
		}
	}

}
