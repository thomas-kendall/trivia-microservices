package trivia.usermanagement.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import trivia.usermanagement.dto.AuthTokenDetailsDTO;

@FeignClient("trivia-token-service")
public interface TriviaTokenServiceClient {

	@RequestMapping(method = RequestMethod.POST)
	String create(@RequestBody AuthTokenDetailsDTO tokenDetails);

	@RequestMapping(value = "/{token}", method = RequestMethod.GET)
	AuthTokenDetailsDTO parseAndValidate(@PathVariable String token);
}
