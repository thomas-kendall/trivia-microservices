package trivia.ui.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import trivia.ui.dto.AuthTokenDetailsDTO;

@FeignClient("trivia-token-service")
public interface TriviaTokenServiceAPI {

	@RequestMapping(value = "/tokens", method = RequestMethod.POST)
	String create(@RequestBody AuthTokenDetailsDTO tokenDetails);

	@RequestMapping(value = "/tokens/{token}", method = RequestMethod.GET)
	AuthTokenDetailsDTO parseAndValidate(@PathVariable("token") String token);
}
