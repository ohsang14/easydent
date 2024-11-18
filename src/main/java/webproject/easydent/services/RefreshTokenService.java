package webproject.easydent.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webproject.easydent.domain.RefreshToken;
import webproject.easydent.repositories.RefreshTokenRepository;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
