package webproject.easydent.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    @Id
    Long reserveId;

    LocalDateTime reservedAt;  //예약 생성일
    LocalDateTime modifiedAt;  //예약 수정일
    String reserveContent; //예약 내용 내용


}
