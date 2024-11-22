package webproject.easydent.review.review;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webproject.easydent.review.comment.Comment;
import webproject.easydent.review.comment.CommentForm;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/list")
    public String list(Model model){
        List<Review> reviewList = this.reviewService.getList();
        model.addAttribute("reviewList", reviewList);
        return "review_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, CommentForm commentForm) {
        Review review = this.reviewService.getReview(id);
        model.addAttribute("review", review);
        return "review_detail";
    }


    @GetMapping("/create")
    public String reviewCreate(Model model) {
        model.addAttribute("reviewForm", new ReviewForm());
        return "review_form";
    }



//    @GetMapping("/create")
//    public String reviewCreate(ReviewForm reviewForm){
//        return "review_form";
//    }

    @PostMapping("/create")
    public String reviewCreate(@Valid ReviewForm reviewForm, BindingResult
            bindingResult) {
        if (bindingResult.hasErrors()) { //잘못입력했으니 다시 question_form 호출
            return "review_form";
        }
        this.reviewService.create(reviewForm.getSubject(), reviewForm.getContent());
        return "redirect:/review/list";
    }
}
