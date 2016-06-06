package trivia.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
// TODO: Add circuit breaker
public class TriviaUserManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TriviaUserManagementServiceApplication.class, args);
	}

}
