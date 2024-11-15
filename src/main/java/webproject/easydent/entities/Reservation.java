package webproject.easydent.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Reservation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    @Id
    Long reserveId;

    LocalDateTime reservedAt;  //예약 생성일
    LocalDateTime modifiedAt;  //예약 수정일
    String content; //진료 내용


}
