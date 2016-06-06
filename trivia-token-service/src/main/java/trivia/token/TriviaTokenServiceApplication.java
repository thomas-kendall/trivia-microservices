package trivia.token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
// TODO add circuit breaker
public class TriviaTokenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TriviaTokenServiceApplication.class, args);
	}
}
