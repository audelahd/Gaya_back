package com.GaYaHole.Pro.service;

import com.GaYaHole.Pro.entity.Reservation;
import com.GaYaHole.Pro.entity.Room;

import java.util.List;

public interface ReservationService {

    public List<Room> dateCheck(Reservation reservation); //예약 가능한 방 리스트 조회
    public void Reservation (Reservation reservation); //예약 실행
}
