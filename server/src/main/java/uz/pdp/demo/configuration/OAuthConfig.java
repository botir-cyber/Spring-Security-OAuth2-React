///* Created by IntelliJ IDEA.
// User: Mirzaahmatov Aziz
//Date: 4/7/2021
//Time: 10:45 PM
// To change this template use File | Settings | File Templates.
//*/
//package uz.pdp.demo.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//
//@Configuration
//@EnableWebFluxSecurity
//class OAuthConfig {
//
//            @Bean
//            public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity):  {
//                return http
//                        .oauth2Client {
//
//                }
//            .authorizeExchange { authExchange ->
//                        authExchange.pathMatchers(
//                                "/apidoc",
//                                "/swagger-ui.html",
//                                "/webjars/**",
//                                "/swagger-resources/**",
//                                "/v2/api-docs")
//                                .permitAll()
//                                .anyExchange()
//                                .permitAll()
//                }
//            .oauth2Client {}
//            .oauth2ResourceServer {
//                    it.opaqueToken {opaqueTokenSpec ->
//                            opaqueTokenSpec.introspector(ReweNimbusReactiveOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret))
//                    }
//                }
//            .build()
//            }