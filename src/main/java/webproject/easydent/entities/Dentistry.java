package webproject.easydent.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "dentistry")
public class Dentistry {

    @Id
    @Column(name = "den_id")
    @JsonProperty("businessNum")
    String businessNum; // 사업자 번호

    @JsonProperty("clinicName")
    String clinicName; // 치과명

    String zip; // 우편번호

    @JsonProperty("address")
    String address;

    @Column(nullable = false)
    @JsonProperty("telephone")
    String telephone;

    @JsonProperty("openAtweekday")
    String openAtweekday; // 주중 오픈 시간

    @JsonProperty("closeAtweekday")
    String closeAtweekday; // 주중 진료 종료

    String openAtweekend;
    String closeAtweekend;

    @JsonProperty("lunchTime")
    String lunchTime; // 점심시간

    @JsonProperty("category")
    @Column(nullable = false)
    String category; // 진료 항목

    String comment;

    @JsonProperty("doctors")
    @Column(nullable = false)
    Integer doctors;

    // 예약과의 관계 추가
    @OneToMany(mappedBy = "dentistry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;
}
