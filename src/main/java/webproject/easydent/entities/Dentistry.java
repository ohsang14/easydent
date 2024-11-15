package webproject.easydent.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Dentistry {

    @Column(name = "den_id")
    @Id
    String businessNum; //사업자 번호

    String zip; // 우편번호
    String address;

    String telephone;

    LocalDateTime openAtweekday; //주중 오픈 시간
    LocalDateTime closeAtweekday; //주중 진료 종료
    LocalDateTime openAtweekend;  //
    LocalDateTime closeAtweekend;
    String lunchTime; //점심시간
    String category; //진료항목
    String comment;
}
