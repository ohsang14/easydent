package webproject.easydent.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import webproject.easydent.configuration.jwt.TokenProvider;
//import webproject.easydent.configuration.oauth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import webproject.easydent.configuration.oauth.OAuth2SuccessHandler;
import webproject.easydent.repositories.RefreshTokenRepository;
import webproject.easydent.services.UserService;
//import webproject.easydent.configuration.jwt.TokenProvider;
//import webproject.easydent.repositories.RefreshTokenRepos itory;

@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {

    private final OAuth2UserService oAuth2UserService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**", "/oauth2/**", "/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserService)
                        )
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler(TokenProvider tokenProvider, RefreshTokenRepository refreshTokenRepository){
        return new OAuth2SuccessHandler(tokenProvider,
                refreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieReposiotry(),
                userService);
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(TokenProvider tokenProvider){
        return new TokenAuthenticationFilter(tokenProvider);
    }

    @Bean
    public webproject.easydent.configuration.OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieReposiotry(){
        return new webproject.easydent.configuration.OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

}
