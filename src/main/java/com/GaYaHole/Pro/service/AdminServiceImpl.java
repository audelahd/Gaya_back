package com.GaYaHole.Pro.service;

import com.GaYaHole.Pro.entity.*;
import com.GaYaHole.Pro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    OptionRepository optionRepository;


    @Override
    public String totalProfit() throws Exception { //총 매출 확인
        String result = accountRepository.totalAccounting();
        return  result;
    }

    @Override
    public void addNotice(Notice notice) throws Exception { //공지 추가
        noticeRepository.save(notice);
    }

    @Override
    public List<Notice> allNotice() throws Exception { //공지 조회
      List<Notice> ncheck = noticeRepository.NOTICE_LIST();
    //    List<Notice> ncheck =noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "nnum"));
        return ncheck;
    }

    @Override
    public void modNotice(Notice notice) throws Exception { //공지 수정
        noticeRepository.save(notice);
    }

    @Override
    public void delNotice(int n_num) throws Exception { //공지 삭제
        noticeRepository.deleteById(n_num);
    }

    @Override
    public List<User> userinfo() throws Exception {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public List<Map<String, Object>> allreservation() throws Exception {
        List<Reservation> reservations = reservationRepository.findAll(); //전체 예약 정보를 가져온다
        List<Map<String, Object>> allresvlist = new ArrayList<>();
        //옵션코드를 기준으로 옵션의 정보를 받아와야 하기 때문에 List<Map<String, Object>> 형식으로 리스트를 만든다
        for (int i=0; i<reservations.size(); i++) { //옵션코드 파싱
            String code = reservations.get(i).getOption_code(); //예약 정보의 옵션코드를 받아온다
            char[] codech=code.toCharArray(); //옵션코드를 파싱함
            Map<String,Object>  map = new HashMap<>();
            for (int j=0; j<reservations.get(i).getOption_code().length(); j++) {
                String str2 = String.valueOf(codech[j]);
                Optional<Option> option = optionRepository.findById(str2);
                //파싱한 옵션코드의 알파벳 (ex : A C D 등)을 기준으로 옵션 정보를 가져옴
                if(option.isPresent()) {
                    Option option1 = option.get();
                    map.put("imt" + j, option1); //가져온 정보를 map에 put
                }
            }
            map.put("res_num",reservations.get(i));
            allresvlist.add(map);
        }
        return allresvlist; //전체 예약 정보 리스트 반환
    }

    @Override
    public void modUsergrade(User user) throws Exception { //사용자 등급 변경
        userRepository.moduser(user.getId(), user.getGrade());
    }

    @Override
    public List<Room> allrooms() throws Exception { //전체 방 리스트
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }
}
