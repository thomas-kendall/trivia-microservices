package trivia.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TriviaUserManagementServiceApplication {

	public static void main(String[] args) {
		DataInitializer.initialize();
		SpringApplication.run(TriviaUserManagementServiceApplication.class, args);
	}

}
