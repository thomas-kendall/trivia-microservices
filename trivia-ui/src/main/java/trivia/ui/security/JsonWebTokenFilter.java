// package trivia.ui.security;
//
// import java.io.IOException;
//
// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import
// org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//
// import trivia.ui.api.TriviaUserManagementServiceAPI;
//
// public class JsonWebTokenFilter extends
// AbstractAuthenticationProcessingFilter {
//
// @Autowired
// private TriviaUserManagementServiceAPI userManagementClient;
//
// public JsonWebTokenFilter() {
// super("/api/**");
// }
//
// @Override
// public Authentication attemptAuthentication(HttpServletRequest request,
// HttpServletResponse response)
// throws AuthenticationException, IOException, ServletException {
// Authentication authentication = null;
// // Header will look like: Authorization: Bearer <jwt token>
// String header = request.getHeader("Authorization");
//
// if (header != null && header.startsWith("Bearer ")) {
// String token = header.substring("Bearer ".length());
// // TODO Finish this stuff
// }
//
// return authentication;
// }
//
// @Override
// protected boolean requiresAuthentication(HttpServletRequest request,
// HttpServletResponse response) {
// return true;
// }
//
// @Override
// protected void successfulAuthentication(HttpServletRequest request,
// HttpServletResponse response, FilterChain chain,
// Authentication authResult) throws IOException, ServletException {
// // Continue with normal processing after the successful authentication
// super.successfulAuthentication(request, response, chain, authResult);
// chain.doFilter(request, response);
// }
// }
