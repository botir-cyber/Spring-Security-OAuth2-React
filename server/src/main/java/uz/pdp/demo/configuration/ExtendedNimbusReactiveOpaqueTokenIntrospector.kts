//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal
//import org.springframework.security.oauth2.server.resource.introspection.NimbusReactiveOpaqueTokenIntrospector
//import reactor.core.publisher.Mono
//
//class ExtendedNimbusReactiveOpaqueTokenIntrospector(introspectionUri: String, clientId: String, clientSecret: String) : NimbusReactiveOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret) {
//    override fun introspect(token: String?): Mono<OAuth2AuthenticatedPrincipal> {
//        return super.introspect(token).map { auth ->
//            val name = SecurityContextHolder.getContext().authentication?.name ?: "defaultPrincipal"
//            DefaultOAuth2AuthenticatedPrincipal(name, auth.attributes, auth.authorities)
//        }
//    }
//}