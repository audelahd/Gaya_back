package com.GaYaHole.Pro.repository;

import com.GaYaHole.Pro.entity.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@JsonIgnoreProperties()
public interface ReviewRepository extends JpaRepository<Review, Integer >{

    // ReservController에서 리뷰 출력할 때 사용 - 방 상세페이지에 사용되기때문에 방 번호를 받아서 사용
    @Query(value = "select * from review where r_num=:rno ",nativeQuery = true)
    List<Review> test111(int rno);
}
