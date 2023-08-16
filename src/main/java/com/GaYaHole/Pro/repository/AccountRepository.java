package com.GaYaHole.Pro.repository;

import com.GaYaHole.Pro.entity.Accounting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Accounting, Integer> {
    //호텔 전체 매출
    @Query(value = "select sum(price) from accounting", nativeQuery = true)
    String totalAccounting ();

    //특정 사용자의 소비액
    @Query(value="select sum(price) from accounting where id =  :userid", nativeQuery = true)
    int userinfo(@Param("userid") String id);
}
