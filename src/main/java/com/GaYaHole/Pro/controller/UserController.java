package com.GaYaHole.Pro.controller;

import com.GaYaHole.Pro.entity.*;
import com.GaYaHole.Pro.repository.*;
import com.GaYaHole.Pro.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    OptionRepository optionRepository;

    //http://localhost:8095/user/join
    @PostMapping("/user/join") //회원가입
    public String join (@RequestBody User user){
        //비밀번호를 암호화할 필요가 있어서 따로 서비스에서 호출한다
        userService.join(user);
        return "회원가입 완료";
    }

    //http://localhost:8095/user/login
    @PostMapping("/user/login") //로그인
    public String login(@RequestBody User user){
        String result = userService.login(user);
        //로그인하며 토큰 스트링을 받아오기 때문에 스트링으로 선언
        if(result!=null){
            //토큰을 받아오지 못했다면 로그인 실패, 받아왔다면 성공
        }
        else{
            result="실패";
        }
        return result;
    }

    //http://localhost:8095/user/idcheck
    @PostMapping("/user/idcheck") //아이디 중복체크
    public int idcheck(@RequestBody User user){
        int result;
        result = userService.idCheck(user);
        return result; //반환값이 없어야 통과된다
    }

    //http://localhost:8095/user/mypage
    @PostMapping ("/user/mypage") //사용자 마이페이지
    public List<Map<String,Object>> mypage(@RequestBody User user){ //id 받아오면 된다...
        List<Reservation> info = userService.mypage(user); //예약 정보를 기준으로 사용자의 정보를 가져옴
        List<Map<String,Object>> list = new ArrayList<>(); //예약 정보의 옵션도 바꿔서 반환해야하기 때문에 리스트 선언
        for (int i=0; i<info.size(); i++) { //옵션코드 파싱
            String code = info.get(i).getOption_code(); //사용자의 예약 정보의 옵션코드를 받아온다.
            Map<String,Object>  map = new HashMap<>();
            char[] str2 = code.toCharArray(); //옵션코드를 리스트형태로 파싱
            for (int j=0; j<str2.length; j++) {
                Optional<Option> option = optionRepository.findById(String.valueOf(str2[j]));
                //파싱한 옵션코드의 알파벳 (ex : A C D 등)을 기준으로 옵션 정보를 가져옴
                if(option.isPresent()) {
                    Option option1 = option.get();
                    map.put("imt" + j, option1); //가져온 정보를 map에 put
                }
            }
            map.put("res_num",info.get(i));
            list.add(map); //리스트에 map
        }
        return list; //리스트 반환 (마이페이지 구성에 필요한 정보들)
    }

    //http://localhost:8095/user/gradecheck
    @PostMapping("/user/gradecheck") //사용자 -> 블랙 여부
    public int gradecheck(@RequestBody User user){
        Optional<User> user1=userService.gradeCheck(user);
        if(user1.isPresent()) {
            return user1.get().getGrade(); //0 -> 일반 사용자 | 1 -> 관리자 | -1 -> 블랙 사용자
        }else {
            return 0;
        }
        }

    //http://localhost:8095/user/info
    @PostMapping("/user/info") //사용자 -> 사용자 정보 조회
    public User userinfo(@RequestBody User user){
        User user1=userService.userinfo(user);
        return user1;
    }

    //http://localhost:8095/user/chepwd
    @PostMapping("/user/chepwd")
    public int usechepwd(@RequestBody User user){
        return userService.countbyidandpwd(user.getId(), user.getPwd());
    }


    //http://localhost:8095/user/modify
    @PutMapping("/user/modify") //사용자 -> 사용자 정보 수정
    public int usermodify(@RequestBody User user){
        Optional<User> user1=userService.findById(user.getId());
        //받아온 아이디를 기준으로 유저의 전체정보조회
        user.setPwd(user1.get().getPwd());
        //제일먼저 가져온user에다가 비밀번호를 대입해줌.
        userService.save(user);
        //그걸 넣어줘
        return 1;
    }

}
