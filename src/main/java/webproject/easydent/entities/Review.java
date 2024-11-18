package webproject.easydent.entities;

import java.time.LocalDateTime;

public class Review {
    //리뷰 아이디 구성

    LocalDateTime reviewCreatedAt;
    LocalDateTime reviewUpdateAt;

    Double reviewScore;
    String reviewContent;
    String reviewURL;
}
