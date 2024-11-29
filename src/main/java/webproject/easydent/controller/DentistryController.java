package webproject.easydent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webproject.easydent.entities.Dentistry;
import webproject.easydent.entities.Reservation;
import webproject.easydent.repositories.ReservationRepository;
import webproject.easydent.service.DentistryService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Set;

@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class DentistryController {

    public final DentistryService dentistryService;

    @GetMapping
    public String reservation(){
        return "reservation";
    }

    //responseEntity -> http 상태코드, 헤더, 본문 데이터를 모두 포함할 수 있는 객체로
    //http 상태코드200(OK)와 함께 results 객체를 json 형태로 반환
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<Set<Dentistry>> searchDentistry(
            @RequestParam("keyword") String keyword
    ) {
        Set<Dentistry> results = dentistryService.searchByKeyword(keyword);
        System.out.println(keyword + " 검색 결과 : "+ results);
        return ResponseEntity.ok(results);
    }
    @Autowired
    private ReservationRepository reservationRepository;



    @CrossOrigin(origins = "http://192.168.0.49:9090") // HTML 파일을 열고 있는 브라우저의 주소와 동일하게 설정
    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(
            @RequestBody Reservation reservation) {
        Reservation savedReservation = reservationRepository.save(reservation);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }





}
