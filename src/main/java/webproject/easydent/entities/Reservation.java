package webproject.easydent.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clinicName;
    private String address;
    private String telephone;
    private LocalDateTime reservedAt; // 예약 생성일
    private String reservedTime; // 예약을 한 시간인데 날짜도 포함되어야함
    private String customerName;

    // Dentistry와의 관계 설정 (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "dentistry_id", referencedColumnName = "den_id")
    private Dentistry dentistry;

    //이메일 참조
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
