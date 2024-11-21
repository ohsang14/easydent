package webproject.easydent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import webproject.easydent.review.comment.Comment;
import webproject.easydent.review.comment.CommentRepository;
import webproject.easydent.review.review.Review;
import webproject.easydent.review.review.ReviewRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EasydentApplicationTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CommentRepository commentRepository;
//    @Test
    void testJpa1(){
        Review r1 = new Review();
        r1.setSubject("여기 참 친절하고 좋습니다");
        r1.setContent("치과가 아주 훌륭합니다");
        r1.setCreateDate(LocalDateTime.now());
        this.reviewRepository.save(r1);

        Review r2 = new Review();
        r2.setSubject("여기 참 불편하네요");
        r2.setContent("치과가 아주 나쁩니다.");
        r2.setCreateDate(LocalDateTime.now());
        this.reviewRepository.save(r2);
    }



}
