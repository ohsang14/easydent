package webproject.easydent.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import webproject.easydent.domain.RefreshToken;

import java.util.Optional;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    //11.17
    Optional<RefreshToken> findByEmail(String email);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
