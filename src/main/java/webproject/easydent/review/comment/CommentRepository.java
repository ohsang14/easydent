package webproject.easydent.review.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import webproject.easydent.review.review.Review;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
