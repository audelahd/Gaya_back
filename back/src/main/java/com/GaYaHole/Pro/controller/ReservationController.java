package com.GaYaHole.Pro.controller;


import com.GaYaHole.Pro.entity.Accounting;
import com.GaYaHole.Pro.entity.Reservation;
import com.GaYaHole.Pro.entity.Room;
import com.GaYaHole.Pro.entity.User;
import com.GaYaHole.Pro.repository.AccountRepository;
import com.GaYaHole.Pro.repository.ReservationRepository;
import com.GaYaHole.Pro.repository.RoomRepository;
import com.GaYaHole.Pro.repository.UserRepository;
import com.GaYaHole.Pro.service.ReservationService;
import com.GaYaHole.Pro.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {

    @Autowired
    ReservationServiceImpl reservationService;

    //http://localhost:8095/reser/datecheck
    @PostMapping("/reser/datecheck") //체크인, 체크아웃 날짜에 따른 방 정보 출력
    public List<Room> datecheck(@RequestBody Reservation reservation) throws ParseException {
        List<Room> ableRoomList = reservationService.dateCheck(reservation);
        //예약 가능한 방 리스트를 가져온다
        return ableRoomList; //방 리스트 반환
    }

    //http://localhost:8095/reser/reservation
    @PostMapping("/reser/reservation") //예약하기
    public String reserv (@RequestBody Reservation reservation )
            throws ParseException{
        //Reservation 형태의 데이터를 그대로 서비스로 넘긴다.
        reservationService.Reservation(reservation);
        return "예약 성공";
    }




}
