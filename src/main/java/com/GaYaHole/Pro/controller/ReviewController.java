package com.GaYaHole.Pro.controller;


import com.GaYaHole.Pro.entity.Review;
import com.GaYaHole.Pro.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/review")
@Log4j2
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/write")  // 리뷰 추가
    public String regreview (@RequestBody Review review) {
        reviewService.regreview(review);
        return "작성되었습니다";
    }

    @PutMapping("/update")  // 리뷰 수정
    public String ureview (@RequestBody Review review) {
        reviewService.ureview(review);
        return "수정 완료";
    }

    @DeleteMapping("/remove/{reviewnum}")   // 리뷰 삭제
    public String rdel(@PathVariable("review_num") int review_num) {
        reviewService.rdel(review_num);
        return "삭제 완료";
    }

    @GetMapping("/read")    // 리뷰 상세
    public Optional<Review> rdet(@RequestBody Review review) {
        Optional<Review> reviewdetail = reviewService.rdet(review.getReview_num());
        return reviewdetail;
    }

}
