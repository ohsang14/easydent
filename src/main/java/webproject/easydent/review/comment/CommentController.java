package webproject.easydent.review.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webproject.easydent.entities.User;
import webproject.easydent.review.review.Review;
import webproject.easydent.review.review.ReviewForm;
import webproject.easydent.review.review.ReviewService;
import webproject.easydent.service.UserService;

import java.security.Principal;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final ReviewService reviewService;
    private final CommentService commentService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id,
                                @Valid CommentForm commentForm, BindingResult bindingResult,
                                Principal principal){ //현재 로그인한 사용자의 정보 제공
        Review review = this.reviewService.getReview(id);
        User user = this.userService.getUser(principal.getName()); //사용자의 아이디를 알 수 있음
        if(bindingResult.hasErrors()){
            model.addAttribute("review", review);
            return "review_detail";
        }
        this.commentService.create(review, commentForm.getContent() ,user);
        return String.format("redirect:/review/detail/%s", id);
    }
}
