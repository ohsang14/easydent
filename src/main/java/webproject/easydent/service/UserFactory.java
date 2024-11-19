package webproject.easydent.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import webproject.easydent.entities.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
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
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

        if (kakaoAccount == null) {
            throw new IllegalArgumentException("카카오 계정 정보를 찾을 수 없습니다.");
        }

        String email = (String) kakaoAccount.get("email");
        String name = (String) kakaoAccount.get("name");
        String phoneNumber = (String) kakaoAccount.get("phone_number");

        // 생년월일 조합
        String birthYear = (String) kakaoAccount.get("birthyear");
        String birthday = (String) kakaoAccount.get("birthday");
        LocalDate birthDay = null;
        if (birthYear != null && birthday != null) {
            try {
                birthDay = LocalDate.parse(birthYear + birthday,
                        DateTimeFormatter.ofPattern("yyyyMMdd"));
            } catch (Exception e) {
                log.warn("생년월일 파싱 실패: ", e);
            }
        }

        return User.builder()
                .email(email)
                .name(name)
                .phoneNumber(phoneNumber)
                .birthDay(birthDay)
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
                String dateStr = birthYear + birthDay;
                // 생년월일 형식이 "YYYYMMDD"인지 확인
                if (dateStr.length() == 8) {
                    return LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}