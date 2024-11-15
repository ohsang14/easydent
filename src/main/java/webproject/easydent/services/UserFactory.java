package webproject.easydent.services;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import webproject.easydent.entities.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class UserFactory {
    public static User create(OAuth2UserRequest userRequest, OAuth2User oAuth2User){
        return switch (userRequest.getClientRegistration().getRegistrationId()){
            case "kakao" -> {
                Map<String, Object> attributeMap = oAuth2User.getAttribute("kakao_account");
                yield  User.builder()
                        .email((String) attributeMap.get("email"))
                        .name((String) attributeMap.get("name"))
                        .phoneNumber((String) attributeMap.get("phone_number"))
                        .birthDay(getBirthDay(attributeMap))
                        .accountType("카카오")
                        .createdAt(LocalDate.now())
                        .build();
            }
            case "google" -> {
                Map<String, Object> attributeMap = oAuth2User.getAttributes();
                yield User.builder()
                        .email((String) attributeMap.get("email"))
                        .name((String) attributeMap.get("name"))
                        .accountType("구글")
                        .createdAt(LocalDate.now())
                        .build();
            }
            default -> throw new IllegalArgumentException("연결되지 않은 서비스 입니다.");
        };


    }
    private static LocalDate getBirthDay(Map<String, Object> attributeMap) {
        String birthYear = (String) attributeMap.get("birthyear");
        String birthDay = (String) attributeMap.get("birthday"); //yyyymmdd

        return LocalDate.parse(birthYear + birthDay, DateTimeFormatter.BASIC_ISO_DATE);
    }
}
