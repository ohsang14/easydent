package webproject.easydent.review.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webproject.easydent.entities.User;
import webproject.easydent.review.review.Review;
import webproject.easydent.review.review.ReviewRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(Review review, String content, User author){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setReview(review);
        comment.setAuthor(author);
        this.commentRepository.save(comment);
    }
}
