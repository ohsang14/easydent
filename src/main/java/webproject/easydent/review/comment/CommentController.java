package webproject.easydent.review.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webproject.easydent.review.review.Review;
import webproject.easydent.review.review.ReviewForm;
import webproject.easydent.review.review.ReviewService;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final ReviewService reviewService;
    private final CommentService commentService;

    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id, @Valid
    CommentForm commentForm, BindingResult bindingResult){
        Review review = this.reviewService.getReview(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("review", review);
            return "review_detail";
        }
        this.commentService.create(review, commentForm.getContent());
        return String.format("redirect:/review/detail/%s", id);
    }
}
