package webproject.easydent.vos;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import webproject.easydent.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    User user;
    Map<String,Object> attributeMap;

    public User getUser(){
        return this.user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributeMap;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> this.user.getEmail());
    }

    public String getName(){
        return this.user.getEmail();
    }
}
