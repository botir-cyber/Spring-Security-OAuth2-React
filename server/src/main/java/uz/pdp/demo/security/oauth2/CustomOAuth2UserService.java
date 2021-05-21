package uz.pdp.demo.security.oauth2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uz.pdp.demo.entity.User;
import uz.pdp.demo.entity.enums.AuthProvider;
import uz.pdp.demo.entity.enums.RoleName;
import uz.pdp.demo.exception.OAuth2AuthenticationProcessingException;
import uz.pdp.demo.repository.RoleRepository;
import uz.pdp.demo.repository.UserRepository;
import uz.pdp.demo.security.oauth2.user.OAuth2UserInfo;
import uz.pdp.demo.security.oauth2.user.OAuth2UserInfoFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken("oauth2", "oauth2")
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println( oAuth2User.getName());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }
        Map<String, Object> objectMap=oAuth2User.getAttributes();
        Map<String,String> obStringStringMap=new HashMap<>();
        objectMap.keySet().forEach(key->{
            obStringStringMap.put(key,objectMap.get(key).toString());

        });
       user.setAttributes(obStringStringMap );
      user= userRepository.save(user);
        return user;
    }
    public User processOAuth2UserToMobile(String registrationId, Map<String, Object> attributes) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(AuthProvider.valueOf(registrationId))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUserMobile(registrationId, oAuth2UserInfo);
        }
        Map<String,String> obStringStringMap=new HashMap<>();
        attributes.keySet().forEach(key->{
            obStringStringMap.put(key, attributes.get(key).toString());

        });
       user.setAttributes(obStringStringMap );
        return user;
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setRoles(Collections.singletonList(roleRepository.findByName(RoleName.USER).get()));
        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId((String) oAuth2UserInfo.getAttributes().get("sub"));
        user.setFullName((String) oAuth2UserInfo.getAttributes().get("name"));
        user.setUsername((String) oAuth2UserInfo.getAttributes().get("given_name"));
        user.setEmail((String) oAuth2UserInfo.getAttributes().get("email"));
        user.setImageUrl((String) oAuth2UserInfo.getAttributes().get("picture"));
//        user.setVerified(true);
        return userRepository.save(user);
    }
    private User registerNewUserMobile(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
//        "locale" -> "ru"
//        "given_name" -> "Aziz"
//        "email_verified" -> {Boolean@13949} true
//        "sub" -> "114686587642021733660"
//        "picture" -> "https://lh5.googleusercontent.com/-Fj7oYhyT3sU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmN7WeEsdjN7LSyUTZMOPL8oIo2Sw/s96-c/photo.jpg"
//        "email" -> "azizjon990415@gmail.com"
//        "name" -> "Aziz jon"
//        "family_name" -> "jon"
        user.setRoles(Collections.singletonList(roleRepository.findByName(RoleName.USER).get()));
        user.setProvider(AuthProvider.valueOf(registrationId));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setFullName(oAuth2UserInfo.getName());
        user.setUsername(oAuth2UserInfo.getEmail());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
//        user.setVerified(true);
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setFullName((String) oAuth2UserInfo.getAttributes().get("name"));
        existingUser.setImageUrl((String) oAuth2UserInfo.getAttributes().get("picture"));
        return userRepository.save(existingUser);
    }

}
