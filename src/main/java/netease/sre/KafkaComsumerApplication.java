package netease.sre;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaComsumerApplication implements CommandLineRunner {

	@Override
	public void run(String ... args) throws Exception {
		DataStreamConsumer dataStreamConsumer = new DataStreamConsumer();
		dataStreamConsumer.doPrint();
		dataStreamConsumer.doTeest();
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaComsumerApplication.class, args);
	}
}
