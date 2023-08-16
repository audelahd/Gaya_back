package com.GaYaHole.Pro.repository;

import com.GaYaHole.Pro.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    //예약된 수
    @Query (value = "select count(reservation_num) from reservation", nativeQuery = true)
    int numCount();

    //특정 사용자가 예약한 정보
    @Query (value="select * from reservation where id = :userid", nativeQuery = true)
    List<Reservation> userinfo(@Param("userid") String id);
}
