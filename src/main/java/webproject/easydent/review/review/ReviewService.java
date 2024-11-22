package webproject.easydent.review.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import webproject.easydent.DataNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {



    private final ReviewRepository reviewRepository;

    public List<Review> getList(){
        return this.reviewRepository.findAll();
    }

    public Review getReview(Integer id){
        Optional<Review> review = this.reviewRepository.findById(id);
        if(review.isPresent()){
            return review.get();
        }else{
            throw new DataNotFoundException("리뷰를 찾을 수 없습니다");
        }
    }

    public void create(String subject, String content){
        Review r = new Review();
        r.setSubject(subject);
        r.setContent(content);
        r.setCreateDate(LocalDateTime.now());
        this.reviewRepository.save(r);
    }

}
