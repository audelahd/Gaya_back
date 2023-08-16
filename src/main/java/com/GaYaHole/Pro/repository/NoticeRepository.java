package com.GaYaHole.Pro.repository;

import com.GaYaHole.Pro.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    @Query(value="select * from notice  order by n_num desc", nativeQuery = true)
    List<Notice> NOTICE_LIST();


}
