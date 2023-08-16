package com.GaYaHole.Pro;

import com.GaYaHole.Pro.entity.Option;
import com.GaYaHole.Pro.entity.Room;
import com.GaYaHole.Pro.repository.OptionRepository;
import com.GaYaHole.Pro.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class ProApplication implements CommandLineRunner {

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private OptionRepository optionRepository;

	@Override
	public void run(String... args) throws Exception {

		//16 개의 방 생성
		Room room1 = new Room(101, 2, "디럭스 룸", 160000);
		Room room2 = new Room(102, 3, "스탠다드 룸", 180000);
		roomRepository.save(room1);
		roomRepository.save(room2);
		roomRepository.save(new Room(103, 4, "패밀리 룸", 240000));
		roomRepository.save(new Room(104, 2, "스위트 룸", 300000));
		roomRepository.save(new Room(105, 2, "디럭스 룸", 160000));
		roomRepository.save(new Room(106, 3, "스탠다드 룸", 180000));
		roomRepository.save(new Room(107, 4, "패밀리 룸", 240000));
		roomRepository.save(new Room(108, 2, "스위트 룸", 300000));
		roomRepository.save(new Room(109, 2, "디럭스 룸", 160000));
		roomRepository.save(new Room(110, 3, "스탠다드 룸", 180000));
		roomRepository.save(new Room(111, 4, "패밀리 룸", 240000));
		roomRepository.save(new Room(112, 2, "스위트 룸", 300000));
		roomRepository.save(new Room(113, 2, "디럭스 룸", 160000));
		roomRepository.save(new Room(114, 3, "스탠다드 룸", 180000));
		roomRepository.save(new Room(115, 4, "패밀리 룸", 240000));
		roomRepository.save(new Room(116, 2, "스위트 룸", 300000));

		//8 개의 옵션 생성
		optionRepository.save(new Option("A", "수영장", 20000, 0)); // 인당
		optionRepository.save(new Option("B", "바베큐", 40000, 0));
		optionRepository.save(new Option("C", "조식", 30000, 0)); // 인당
		optionRepository.save(new Option("D", "사우나", 20000, 1)); // 인당
		optionRepository.save(new Option("E", "추가침대", 40000, 1)); //인당
		optionRepository.save(new Option("F", "VR", 100000, 2));
		optionRepository.save(new Option("G", "키즈카페", 40000, 2));
		optionRepository.save(new Option("H", "기념행사", 300000, 3));

	}

	public static void main(String[] args) {
		SpringApplication.run(ProApplication.class, args);
	}

}
