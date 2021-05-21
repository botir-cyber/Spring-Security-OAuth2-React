//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
//import org.springframework.security.config.web.server.ServerHttpSecurity
//import org.springframework.security.web.server.SecurityWebFilterChain
//
//@Configuration
//@EnableWebFluxSecurity
//open class OAuthConfig
//@Autowired constructor(@Value("\${spring.security.oauth2.registration.registraedirectUri}") private val introspectionUri: String,
//                       @Value("\${spring.security.oauth2.registration.client-id}") private val clientId: String,
//                       @Value("\${spring.security.oauth2.registration.client-secret}") private val clientSecret: String){
//
//    @Bean
//    open fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
//        return http
//                .oauth2Client {
//
//                }
//                .authorizeExchange { authExchange ->
//                    authExchange.pathMatchers(
//                            "/apidoc",
//                            "/swagger-ui.html",
//                            "/webjars/**",
//                            "/swagger-resources/**",
//                            "/v2/api-docs")
//                            .permitAll()
//                            .anyExchange()
//                            .permitAll()
//                }
//                .oauth2Client {}
//                .oauth2ResourceServer {
//                    it.opaqueToken {opaqueTokenSpec ->
//                        opaqueTokenSpec.introspector( ExtendedNimbusReactiveOpaqueTokenIntrospector.ExtendedNimbusReactiveOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret))
//                    }
//                }
//                .build()
//    }}