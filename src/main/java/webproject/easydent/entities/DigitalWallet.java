//package webproject.easydent.entities;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//
//@Entity
//public class DigitalWallet {
//
//    @Column(name = "card_id")
//    @Id
//    String cardId;
//
//    String bankName;
//
//    LocalDate createdAt;
//    LocalDate modifiedAt;
//
//    //이메일 참조
//    @ManyToOne
//    @JoinColumn(name = "user_id") //실제 데이터베이스에 생성될 외래 키 컬럼 이름
//    private User user;
//}
