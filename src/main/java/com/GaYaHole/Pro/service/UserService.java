package com.GaYaHole.Pro.service;

import com.GaYaHole.Pro.entity.Reservation;
import com.GaYaHole.Pro.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void join(User user); //회원가입
    public String login(User user); //로그인
    public int idCheck(User user); //아이디 중복 체크
    public List<Reservation> mypage (User user); //사용자 상세 페이지
    public Optional<User> gradeCheck (User user); //사용자 등급 체크
    public User userinfo(User user); //사용자 정보
    public int countbyidandpwd(String id,String pwd); //정보 수정시 아이디&비밀번호 매칭
    public Optional<User> findById(String id);
    public void save(User user);
}
