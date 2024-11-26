package webproject.easydent.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import webproject.easydent.entities.Dentistry;
import webproject.easydent.service.DentistryService;

import java.util.List;
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

    //json 형태로 반환
//    @GetMapping("/search")
//    @ResponseBody
//    public ResponseEntity<List<Dentistry>> searchDentistry(
//            @RequestParam("keyword") String keyword,
//            @RequestParam(value = "address", required = false, defaultValue = "") String address) {
//        List<Dentistry> results = dentistryService.searchDentistry(keyword, address);
//        System.out.println("검색 결과: " + results); // 디버깅 로그 추가
//        return ResponseEntity.ok(results);
//    }

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

}
