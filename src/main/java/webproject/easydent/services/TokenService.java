package webproject.easydent.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webproject.easydent.configuration.jwt.TokenProvider;
import webproject.easydent.entities.User;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken){
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("Unexpected token");
        }

        String email = refreshTokenService.findByRefreshToken(refreshToken).getEmail();
        User user = userService.findByEmail(email);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }

}
