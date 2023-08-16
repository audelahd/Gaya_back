package com.GaYaHole.Pro.repository;

import com.GaYaHole.Pro.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OptionRepository extends JpaRepository<Option, String> {
    //옵션 코드를 기준으로 옵션 정보 받아오기
    @Query(value = "select * from option where option_code= :optioncode ", nativeQuery = true)
    Option optionselect  (@Param("optioncode") String optioncode);

    // 방 종류에 따른 옵션 종류들 출력 ( 디럭스 / 스탠다드 / 패밀리 / 스위트)
    @Query(value = "select * from option where option_type <= :type", nativeQuery = true)
    List<Option> optype(int type);
}
