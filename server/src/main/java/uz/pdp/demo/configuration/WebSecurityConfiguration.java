///* Created by IntelliJ IDEA.
// User: Mirzaahmatov Aziz
//Date: 4/7/2021
//Time: 11:10 PM
// To change this template use File | Settings | File Templates.
//*/
//package uz.pdp.demo.configuration;
//
//import com.nimbusds.jose.proc.SimpleSecurityContext;
//import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
//import com.nimbusds.jwt.proc.DefaultJWTProcessor;
//import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtValidators;
//import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
//import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
//import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
//import uz.pdp.demo.entity.Role;
//
//import java.net.URI;
//import java.util.Collections;
//import java.util.Map;
//
//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
//public class WebSecurityConfiguration {
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.authorizeExchange(exchangeSpec -> exchangeSpec.anyExchange().authenticated())
//                .oauth2ResourceServer(oAuth2 -> oAuth2.jwt(jwt -> jwt.jwtDecoder(jwtDecoder())));
//        http.oauth2Login();
//        return http
//                // .csrf().disable()
//                .authorizeExchange()
//                .matchers(PathRequest.toStaticResources().atCommonLocations())
//                .permitAll()
//                .anyExchange()
//                .authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin()
//                .and()
//                .logout()
//                .logoutSuccessHandler(logoutSuccessHandler())
//                .and()
//                .build();
//
//    }
//    public static class UsernameSubClaimAdapter implements Converter<Map<String, Object>, Map<String, Object>> {
//        private final MappedJwtClaimSetConverter delegate =
//                MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());
//
//        public Map<String, Object> convert(Map<String, Object> claims) {
//            Map<String, Object> convertedClaims = this.delegate.convert(claims);
//
//            String username = "anonymous_user";
//            convertedClaims.put("sub", username);
//
//            return convertedClaims;
//        }
//    }
//    @Bean
//        public PasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//    @Bean
//    public NimbusReactiveJwtDecoder jwtDecoder() {
////        ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
//        NimbusReactiveJwtDecoder jwtDecoder = new NimbusReactiveJwtDecoder("MyApiSupperMupperKey");
//        jwtDecoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(""));
//        jwtDecoder.setClaimSetConverter(new UsernameSubClaimAdapter());
//        return jwtDecoder;
//    }
//
//    @Bean
//    public ServerLogoutSuccessHandler logoutSuccessHandler() {
//        RedirectServerLogoutSuccessHandler logoutSuccessHandler =
//                new RedirectServerLogoutSuccessHandler();
//        logoutSuccessHandler.setLogoutSuccessUrl(URI.create("/books"));
//        return logoutSuccessHandler;
//    }
//}
