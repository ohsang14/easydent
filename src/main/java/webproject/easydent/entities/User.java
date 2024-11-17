package webproject.easydent.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<DigitalWallet> DigitalWalletList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<WantedLocation> wantedLocationList;
}
