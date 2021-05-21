package uz.pdp.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import uz.pdp.demo.security.AuthService;
import uz.pdp.demo.security.JwtAuthenticationEntryPoint;
import uz.pdp.demo.security.JwtAuthenticationFilter;
import uz.pdp.demo.security.oauth2.CustomOAuth2UserService;
import uz.pdp.demo.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import uz.pdp.demo.security.oauth2.OAuth2AuthenticationFailureHandler;
import uz.pdp.demo.security.oauth2.OAuth2AuthenticationSuccessHandler;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthService authService;


    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    private static List<String> clients = Arrays.asList("google", "facebook");


    //    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthService authService;

        @Resource
        public Environment env;
        @Autowired
        private CustomOAuth2UserService customOAuth2UserService;

        @Autowired
        private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

        @Autowired
        private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

        @Autowired
        private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

        public App1ConfigurationAdapter() {
            super();
        }


        @Bean
        public JwtAuthenticationFilter jwtAuthenticationFilter() {
            return new JwtAuthenticationFilter();
        }

        @Autowired
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Bean
        public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
            return new HttpCookieOAuth2AuthorizationRequestRepository();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


        @Override
        public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder
                    .userDetailsService(authService)
                    .passwordEncoder(passwordEncoder());
        }

        @Bean
        public ClientRegistrationRepository clientRegistrationRepository() {
            List<ClientRegistration> registrations = clients.stream()
                    .map(c -> getRegistration(c))
                    .filter(registration -> registration != null)
                    .collect(Collectors.toList());

            return new InMemoryClientRegistrationRepository(registrations);
        }

        private static String CLIENT_PROPERTY_KEY
                = "spring.security.oauth2.client.registration.";



        private ClientRegistration getRegistration(String client) {
            String clientId = env.getProperty(
                    CLIENT_PROPERTY_KEY + client + ".client-id");

            if (clientId == null) {
                return null;
            }

            String clientSecret = env.getProperty(
                    CLIENT_PROPERTY_KEY + client + ".client-secret");


            String clientScope = env.getProperty(
                    CLIENT_PROPERTY_KEY + client + ".scope");

            if (client.equals("google")) {
                return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                        .clientId(clientId).clientSecret(clientSecret).build();
            }
            if (client.equals("facebook")) {
                return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                        .clientId(clientId).clientSecret(clientSecret).build();
            }
            return null;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .cors()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf()
                    .disable()
                    .formLogin()
                    .disable()
                    .httpBasic()
                    .disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                    .and()
                    .authorizeRequests()
                    .antMatchers("/",
                            "/favicon.ico",
                            "/**/*.png",
                            "/**/*.gif",
                            "/**/*.svg",
                            "/**/*.jpg",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js",
                            "/swagger-ui.html",
                            "/swagger-resources/**",
                            "/v2/**",
                            "/csrf",
                            "/webjars/**")
                    .permitAll()
                    .antMatchers("/api/auth/**", "/api/file/**", "/api/click/**", "/oauth2/**", "/api/order/getOrderSum")
                    .permitAll().antMatchers("/api/**").permitAll()
                    .and()
                    .oauth2Login()
                    .clientRegistrationRepository(clientRegistrationRepository())
                    .authorizedClientService(authorizedClientService())
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorize")
                    .authorizationRequestRepository(cookieAuthorizationRequestRepository())


//                .and()
//                .redirectionEndpoint()
//                .baseUri("/oauth2/callback/*")
                    .and()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                    .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler);
            ;

            // Add our custom JWT security filter
            http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        }
        @Bean
        public OAuth2AuthorizedClientService authorizedClientService() {

            return new InMemoryOAuth2AuthorizedClientService(
                    clientRegistrationRepository());
        }

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //@formatter:off
        super.configure(web);
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

}
