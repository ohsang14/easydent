package webproject.easydent.services;


import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import webproject.easydent.entities.User;
import webproject.easydent.repositories.UserRepository;
import webproject.easydent.vos.CustomOAuth2User;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       OAuth2User oAuth2User = super.loadUser(userRequest);

       String email = oAuth2User.getAttribute("email");
       User user = userRepository.findByEmail(email)
               .orElseGet(() -> {
                   User newUser = UserFactory.create(userRequest, oAuth2User);
                   return userRepository.save(newUser);
               });

       return new CustomOAuth2User(user, oAuth2User.getAttributes());
    }
}
