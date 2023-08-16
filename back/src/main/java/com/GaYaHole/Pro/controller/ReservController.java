package com.GaYaHole.Pro.controller;

import com.GaYaHole.Pro.entity.Option;
import com.GaYaHole.Pro.entity.Review;
import com.GaYaHole.Pro.entity.Room;
import com.GaYaHole.Pro.repository.OptionRepository;
import com.GaYaHole.Pro.repository.ReviewRepository;
import com.GaYaHole.Pro.repository.RoomRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reserv")
@Log4j2
public class ReservController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OptionRepository optionRepository;


    @PostMapping("/detail") // 방 정보 출력 - r_num
    public Optional<Room> detail(@RequestBody Room room) {
        log.info("r_num: " + room);
        Optional<Room> rom = roomRepository.findById(room.getR_num());

        return rom;
    }

    @PostMapping("/review") // 리뷰 출력 - r_num (service에서 쿼리 입력)
    public List<Review> rreview(@RequestBody Room room) {
        log.info("review: " + room.getR_num());
        List<Review> rev = reviewRepository.test111(room.getR_num());
        return rev;
    }

    @PostMapping("/option") // 옵션 출력 - type에 입력값 이하의 값을 갖고있는 옵션 출력
    public List<Option> selectop(@RequestBody Room room) {
        if (room.getR_type().equals("디럭스 룸")) {
            List<Option> deluxe = optionRepository.optype(0);           // 0옵션 출력
            return deluxe;
        } else if (room.getR_type().equals("스탠다드 룸")) {
            List<Option> standard = optionRepository.optype(1);         // 0,1옵션 출력
            return standard;
        } else if (room.getR_type().equals("패밀리 룸")) {
            List<Option> family = optionRepository.optype(2);           // 0, 1, 2옵션 출력
            return family;
        } else {
            List<Option> sweet = optionRepository.optype(3);            // 0, 1, 2, 3옵션 출력
            return sweet;
        }
    }
}
