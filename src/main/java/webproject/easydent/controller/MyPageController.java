package webproject.easydent.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webproject.easydent.entities.FamilyAccount;
import webproject.easydent.entities.User;
import webproject.easydent.service.FamilyAccountService;
import webproject.easydent.service.ProductService;
import webproject.easydent.service.UserService;
import webproject.easydent.vos.CustomOAuth2User;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final UserService userService;
    private final FamilyAccountService familyAccountService;

    @GetMapping
    public String myPage(Model model, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
//        if (customOAuth2User != null) {
//            User user = customOAuth2User.getUser();
//            log.info("Authenticated user: {}", user);
//            addUserToModel(model, user);
//        }
//        return "mypage";
        if (customOAuth2User != null) {
            String email = customOAuth2User.getUser().getEmail(); // 현재 로그인한 사용자의 이메일
            User user = userService.findByEmail(email); // DB에서 사용자 정보 조회
            model.addAttribute("userName", user.getName()); // 모델에 사용자 이름 추가
        }
        return "mypage"; // 마이페이지 템플릿 반환
    }

    //정보 수정
    @GetMapping("/edit-info")
    public String userInfo(Model model, @AuthenticationPrincipal CustomOAuth2User customOAuth2User){

        if(customOAuth2User!=null){
            User user = customOAuth2User.getUser();
            log.info("Authenticated user: {}", user);
            model.addAttribute("user",user);
        }
        else{
            log.info("user-notFound");
        }
        return "edit-info";
    }

    //정보 수정
    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute User user,
                             @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        if (customOAuth2User != null) {
            user.setEmail(customOAuth2User.getUser().getEmail());  // 현재 로그인한 사용자의 ID 설정
            userService.updateUser(user);

            log.info(user.getName());
        }

        return "redirect:/mypage";
    }

    @PostMapping("/family-group/create")
    public String createFamilyGroup(
            @AuthenticationPrincipal CustomOAuth2User user,
            @RequestParam(name = "memberEmail") String memberEmail,
            @RequestParam(name = "relationship") String relationship,
            HttpSession session,
            Model model) {
        try {
            // 가족 그룹 생성
            FamilyAccount familyAccount = familyAccountService.createFamilyGroup(user.getUser(), memberEmail, relationship);

            // 세션에 데이터 저장
            // TODO : DB에 저장하기
            session.setAttribute("currentUser", user.getUser());
            session.setAttribute("memberEmail", memberEmail);
            session.setAttribute("relationship", relationship);
            session.setAttribute("familyAccount", familyAccount);

            return "family-management";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "family-management";
        }
    }

    @GetMapping("/family-management")
    public String familyManagement(Model model, HttpSession session) {
        // 세션에서 데이터 복원
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        model.addAttribute("memberEmail", session.getAttribute("memberEmail"));
        model.addAttribute("relationship", session.getAttribute("relationship"));
        model.addAttribute("familyAccount", session.getAttribute("familyAccount"));

        return "family-management";
    }

    //고객 센터
    @GetMapping("/customer-center")
    public String customerCenter(){
        return "customer-center";
    }

    //공지 사항
    @GetMapping("/notice")
    public String notice(){
        return "notice";
    }

    //진료 내역
    @GetMapping("/medical-history")
    public String medicalHistory(){
        return "medical-history";
    }

    //치과 가입 문의
    @GetMapping("/register")
    public String register(){
        return "register";
    }

}
