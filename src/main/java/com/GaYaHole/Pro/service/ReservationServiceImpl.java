package com.GaYaHole.Pro.service;


import com.GaYaHole.Pro.entity.Accounting;
import com.GaYaHole.Pro.entity.Reservation;
import com.GaYaHole.Pro.entity.Room;
import com.GaYaHole.Pro.entity.User;
import com.GaYaHole.Pro.repository.AccountRepository;
import com.GaYaHole.Pro.repository.ReservationRepository;
import com.GaYaHole.Pro.repository.RoomRepository;
import com.GaYaHole.Pro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Room> dateCheck(Reservation reservation) {
        List<Room> ableRoomList = roomRepository.dateCal(reservation.getCheck_in(), reservation.getCheck_out());
        //체크인과 체크아웃 날짜를 받아와 가능한 방 리스트를 반환한다
        return ableRoomList;
    }

    @Override
    public void Reservation(Reservation reservation) {
        User user; Room room;
        user = reservation.getId();
        Optional<User> user1=userRepository.findById(user.getId());
        room = roomRepository.roominfo(reservation.getR_num().getR_num());
        if(user1.isPresent()) {
            user = user1.get();
        }
        // id와 r_num의 형식이 각각 USER, ROOM이기 때문에 그에 맞춰 데이터를 가져온다

        reservation = Reservation.builder()
                .option_code(reservation.getOption_code())
                .check_in(reservation.getCheck_in())
                .check_out(reservation.getCheck_out())
                .total_price(reservation.getTotal_price())
                .order_id(reservation.getOrder_id())
                .r_num(room)
                .id(user)
                .build();
        //입력한 정보를 예약테이블에 삽입함

        reservationRepository.save(reservation);

        Accounting accounting = Accounting.builder()
                .pay_number(reservation.getReservation_num())
                .price(reservation.getTotal_price())
                .id(user)
                .reservation_num(reservation)
                .build();
        //예약이 실행됨과 동시에 매출 테이블에도 정보를 추가해준다
        accountRepository.save(accounting);

    }
}
