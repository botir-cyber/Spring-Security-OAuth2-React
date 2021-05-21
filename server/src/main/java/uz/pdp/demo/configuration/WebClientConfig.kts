//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager
//import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager
//import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder
//import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService
//import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository
//import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction
//import org.springframework.web.reactive.function.client.WebClient
//
//@Configuration
//open class WebClientConfig @Autowired constructor() {
//
//    @Bean
//    open fun authorizedClientManager(
//            clientRegistrationRepository: ReactiveClientRegistrationRepository?,
//            authorizedClientService: ReactiveOAuth2AuthorizedClientService?
//    ): ReactiveOAuth2AuthorizedClientManager? {
//        val authorizedClientProvider = ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
//                .clientCredentials()
//                .build()
//        val authorizedClientManager = AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientService)
//        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider)
//        return authorizedClientManager
//    }
//
//    @Bean
//    open fun webClientBuilder(authorizedClientManager: ReactiveOAuth2AuthorizedClientManager?): WebClient.Builder? {
//        val oauth = ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager)
//        oauth.setDefaultClientRegistrationId("rewe")
//        return WebClient.builder()
//                .filter(oauth)
//    }
//
//    @Bean
//    open fun authorizedWebClient(webClientBuilder: WebClient.Builder, @Value("\${host}") host: String): WebClient {
//        return webClientBuilder.baseUrl(host).build()
//    }
//}