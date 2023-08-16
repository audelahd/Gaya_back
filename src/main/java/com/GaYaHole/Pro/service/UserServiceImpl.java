package com.GaYaHole.Pro.service;

import com.GaYaHole.Pro.config.JwtAuthenticationFilter;
import com.GaYaHole.Pro.config.TokenProvider;
import com.GaYaHole.Pro.entity.Option;
import com.GaYaHole.Pro.entity.Reservation;
import com.GaYaHole.Pro.entity.User;
import com.GaYaHole.Pro.repository.ReservationRepository;
import com.GaYaHole.Pro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    public void join(User user) { //회원가입

        BCryptPasswordEncoder changePwd = new BCryptPasswordEncoder();
        user.setPwd(changePwd.encode(user.getPwd()));
        //비밀번호를 BCrypt 형식으로 DB에 저장한다

        userRepository.save(user);
    }
    @Override
    public void save(User user) { //회원가입
        userRepository.save(user);
    }
    @Override
    public String login(User user) { //로그인
        Optional<User> user2 = userRepository.findById(user.getId());
        if(user2.isPresent()) {
            BCryptPasswordEncoder hashPwd = new BCryptPasswordEncoder();
            //로그인 시 비밀번호를 BCrypt 형식으로 바꿔서 비교한다
            System.out.println(hashPwd.matches(user.getPwd(), user2.get().getPwd()));
            if (hashPwd.matches(user.getPwd(), user2.get().getPwd())) {
                //비밀번호가 일치하면 토큰 발급
                String token = tokenProvider.create(user2);
                return token;
            } else {
                //일치하지 않으면 발급하지 않는다
                return null;
            }
        }else {
            return null;
        }

    }

    @Override
    public int idCheck(User user) { //아이디 중복체크
       int result;
       result = userRepository.idtest(user.getId());
       return result;
    }

    @Override
    public List<Reservation> mypage(User user) { //사용자 마이페이지
        List<Reservation> info = reservationRepository.userinfo(user.getId());
        return info;
    }

    @Override
    public Optional<User> gradeCheck(User user) { //사용자 등급 확인
        Optional<User> user1 = userRepository.findById(user.getId());
        //아이디를 기준으로 사용자의 정보를 가져와서 확인한다
        return user1;
    }

    @Override
    public User userinfo(User user) { //사용자 존재 여부 확인
       Optional<User> user1= userRepository.findById(user.getId());
       if(user1.isPresent()) {
          user1.get().setPwd(null);
          return user1.get();
       }
       return null;
    }

    @Override
    public int countbyidandpwd(String id, String pwd) {
        Optional<User> user2 = userRepository.findById(id);
        BCryptPasswordEncoder hashPwd = new BCryptPasswordEncoder();

        if(hashPwd.matches(pwd, user2.get().getPwd() )){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}
