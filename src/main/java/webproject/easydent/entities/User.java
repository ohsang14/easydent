package webproject.easydent.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "users")
public class User{
    @Column(name = "user_id")
    @Id
    String email;

    String name;

    String phoneNumber;

    LocalDate birthDay;

    String accountType; // 어떤 계정으로 로그인 했는지

    LocalDate createdAt; // 계정 생성일

    String zip; //우편번호

    String address; // 주소

    // OAuth2 관련 필드 추가
    String provider;        // google, kakao 등 로그인 제공자
    String providerId;      // OAuth2 제공자가 제공하는 고유 ID


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<DigitalWallet> DigitalWalletList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<WantedLocation> wantedLocationList;
}
