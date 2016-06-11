package trivia.ui.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JsonWebTokenAuthenticationProvider authenticationProvider;

	@Autowired
	private JsonWebTokenAuthenticationFilter jsonWebTokenFilter;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// This method is here with the @Bean annotation so that Spring can
		// autowire it
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// disable CSRF, http basic, form login
				.csrf().disable() //
				.httpBasic().disable() //
				.formLogin().disable()

				// ReST is stateless, no sessions
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //

				.and()

				// return 403 when not authenticated
				.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())

				.and() //
				.authorizeRequests()

				// allow anonymous access to /authenticate endpoint
				.antMatchers("/authenticate").permitAll()

				// allow anonymous to common static resources
				.antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js")
				.permitAll()

				// authenticate all other requests
				.anyRequest().authenticated();

		http.addFilterBefore(jsonWebTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
