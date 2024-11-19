package webproject.easydent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import webproject.easydent.entities.User;
import webproject.easydent.repositories.UserRepository;
import webproject.easydent.vos.CustomOAuth2User;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            User user = UserFactory.create(userRequest, oAuth2User);
            log.info("Created user: {}", user);

            User savedUser = userRepository.findByEmail(user.getEmail())
                    .orElseGet(() -> {
                        log.info("Saving new user: {}", user);
                        return userRepository.save(user);
                    });

            return new CustomOAuth2User(savedUser, oAuth2User.getAttributes());
        } catch (Exception e) {
            log.error("Error during OAuth2 authentication: ", e);
            throw new OAuth2AuthenticationException(new OAuth2Error("authentication_error"), "인증 처리 중 오류가 발생했습니다.");
        }
    }
}