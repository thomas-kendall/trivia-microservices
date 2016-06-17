package trivia.usermanagement.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import trivia.common.security.JsonWebTokenSecurityConfig;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "trivia.common.security")
public class TriviaUserManagementWebSecurityConfig extends JsonWebTokenSecurityConfig {

	@Override
	protected void setupAuthorization(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				// allow anonymous access to /authenticate endpoint
				.antMatchers("/authenticate").permitAll()

				// authenticate all other requests
				.anyRequest().authenticated();
	}
}
