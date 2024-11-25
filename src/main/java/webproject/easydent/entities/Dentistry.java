package webproject.easydent.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Dentistry {

    @Column(name = "den_id")
    @JsonProperty("businessNum")
    @Id
    String businessNum; //사업자 번호

    @JsonProperty("clinicName")
    String clinicName; //치과명

    String zip; // 우편번호

    @JsonProperty("address")
    String address;

    @JsonProperty("telephone")
    String telephone;

    @JsonProperty("openAtweekday")
    String openAtweekday; //주중 오픈 시간
    @JsonProperty("closeAtweekday")
    String closeAtweekday; //주중 진료 종료

    String openAtweekend;  //
    String closeAtweekend;

    @JsonProperty("lunchTime")
    String lunchTime; //점심시간

    @JsonProperty("category")
    String category; //진료항목
    String comment;
}
