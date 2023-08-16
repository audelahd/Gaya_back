package com.GaYaHole.Pro.repository;

import com.GaYaHole.Pro.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    //날짜 계산 메소드 (체크인 체크아웃 둘다)
    @Query(value="select * from room where r_num not in (" +
            "(select r_num from reservation r2 where r2.check_in "+
            " between date( :cidate) and date ( :codate)-1 "+
            " union "+
            "(select r_num from reservation r2 where r2.check_out "+
            "between date( :cidate)+1 and date ( :codate) )) )", nativeQuery = true)
    List<Room> dateCal (@Param("cidate") Date checkin, @Param("codate") Date checkout);

    //방 번호를 기준으로 방 정보 가져오기
    @Query(value="select* from room where r_num = :rnum", nativeQuery = true)
    Room roominfo (@Param("rnum") int r_num);

    //방 가격 수정하기 (관리자)
    @Query(value = "update room set r_price= :rprice  where r_num = :rnum", nativeQuery = true)
    void roomUpdate (@Param ("rnum") int r_num, @Param ("rprice") int r_price);
}
