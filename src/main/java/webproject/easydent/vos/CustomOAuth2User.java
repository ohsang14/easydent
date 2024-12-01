package webproject.easydent.vos;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import webproject.easydent.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final User user;
    private final Map<String,Object> attributeMap;

    public User getUser() {
        return user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributeMap;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> this.user.getEmail());
    }

    public String getName(){ //OAuth2User 메소드 implement -> 이메일 얻어오기
        return this.user.getEmail();
    }

    public String getUserName(){ //OAuth2User 메소드 implement -> 이름 얻어오기
        return this.user.getName();
    }
}
