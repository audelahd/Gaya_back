package com.GaYaHole.Pro.service;

import com.GaYaHole.Pro.entity.Review;

import java.util.Optional;

public interface ReviewService {

    public void regreview(Review review);   // 리뷰 작성
    public void ureview(Review review);     // 리뷰 수정
    public void rdel(int review_num);       // 리뷰 삭제

    public Optional rdet(int review_num);   // 리뷰 상세

}
