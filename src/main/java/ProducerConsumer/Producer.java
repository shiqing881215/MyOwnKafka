package ProducerConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.google.common.io.Resources;

public class Producer {
	
	public static void main(String[] args) throws IOException {
		// Set up the producer from producer.property file
		// First generic type is for key and second is for value
		KafkaProducer<String, String> producer = null;
		try {
			// Consumer needs to serialize
			InputStream pros = Resources.getResource("producer.property").openStream();
			Properties properties = new Properties();
			properties.load(pros);
			producer = new KafkaProducer<String, String>(properties);
			
			for (int i = 0; i < 100; i++) {
				// Send fast message
				// First param is the name of the topic, second param is the value
				// You can also set ProducerRecord(String topic, K key, V value) 
				producer.send(new ProducerRecord<String, String>("fast-topic", String.valueOf(i)));
				System.out.println("Send to fast-topic with " + i);
				
				// Send slow message
				if (i%10 == 0) {
					producer.send(new ProducerRecord<String, String>("slow-topic", String.valueOf(i)));
					System.out.println("Send to slow-topic with " + i);
				}
			}
		} finally {
			if (producer != null) {
				producer.close();
			}
		}
	}
}
