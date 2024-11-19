package webproject.easydent.service;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import webproject.easydent.entities.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class UserFactory {
    public static User create(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        return switch (registrationId) {
            case "kakao" -> createKakaoUser(oAuth2User);
            case "google" -> createGoogleUser(oAuth2User);
            default -> throw new IllegalArgumentException("연결되지 않은 서비스 입니다.");
        };
    }

    private static User createKakaoUser(OAuth2User oAuth2User) {
        Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return User.builder()
                .email((String) kakaoAccount.get("email"))
                .name((String) profile.get("nickname"))
                .phoneNumber((String) kakaoAccount.get("phone_number"))
                .birthDay(getBirthDay(kakaoAccount))
                .accountType("카카오")
                .createdAt(LocalDate.now())
                .build();
    }

    private static User createGoogleUser(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return User.builder()
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .accountType("구글")
                .createdAt(LocalDate.now())
                .build();
    }

    private static LocalDate getBirthDay(Map<String, Object> kakaoAccount) {
        try {
            String birthYear = (String) kakaoAccount.get("birthyear");
            String birthDay = (String) kakaoAccount.get("birthday");
            if (birthYear != null && birthDay != null) {
                return LocalDate.parse(birthYear + birthDay, DateTimeFormatter.BASIC_ISO_DATE);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
