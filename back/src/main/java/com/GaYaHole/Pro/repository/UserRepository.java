package com.GaYaHole.Pro.repository;

import com.GaYaHole.Pro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String>{
    //아이디 중복 검사
    @Query(value="select count(id) from user where id = :testid" , nativeQuery = true)
    int idtest(@Param("testid") String testID);
    //로그인
    @Query(value="select count(id) from user where id = :loginid and pwd = :loginpwd", nativeQuery = true)
    int logintest(@Param("loginid") String loginID, @Param("loginpwd") String loginPwd);
    //아이디를 기준으로 사용자 정보 가져오기
    @Query(value="select * from user where id = :idid", nativeQuery = true)
    User userinfo(@Param("idid") String id);
    //사용자 등급 조정하기 (블랙 / 일반)
    @Query(value="update user set grade = :modgrade where id = :idid", nativeQuery = true)
    void moduser(@Param("idid") String id, @Param("modgrade") int mgrade);

     int countByIdAndPwd(String id,String pwd);
}
