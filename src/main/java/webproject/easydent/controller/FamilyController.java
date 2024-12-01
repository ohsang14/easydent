package webproject.easydent.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webproject.easydent.entities.FamilyAccount;
import webproject.easydent.entities.User;
import webproject.easydent.repositories.FamilyRepository;
import webproject.easydent.repositories.UserRepository;
import webproject.easydent.service.FamilyAccountService;
import webproject.easydent.vos.CustomOAuth2User;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyAccountService familyAccountService;
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

//    @GetMapping("/family-management")
//    public String familManagement(Model model, @AuthenticationPrincipal CustomOAuth2User user){
//        log.info("user: {}", user);
//        return "family-management";
//    }
//
//    @PostMapping("/family-group/create")
//    public String createFamilyGroup(
//            Model model,
//            @AuthenticationPrincipal CustomOAuth2User user,
//            @RequestParam(name = "memberEmail") String memberEmail,
//            @RequestParam(name = "relationship") String relationship,
//            RedirectAttributes redirectAttributes) {
//        try {
//            familyAccountService.createFamilyGroup(user.getUser(), memberEmail, relationship);
//            redirectAttributes.addFlashAttribute("message", "가족 그룹이 생성되었습니다.");
//            log.info("Leader: {}, Member Email: {}", user.getUser().getEmail(), memberEmail);
//            return "redirect:/family-management";
//        } catch (IllegalArgumentException e) {
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
//            return "redirect:/family-management";
//        }
//    }

//    @GetMapping("/family-management")
//    public String familManagement(Model model, @AuthenticationPrincipal CustomOAuth2User user){
//        User currentUser = userRepository.findById(user.getUser().getEmail())
//                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
//
//        // 현재 사용자와 관련된 모든 가족 계정 조회
//        List<FamilyAccount> familyAccounts = familyRepository.findAll();
//        model.addAttribute("familyAccounts", familyAccounts);
//        model.addAttribute("currentUser", currentUser);
//
//        log.info("user: {}", user);
//        return "family-management";
//    }

//    @PostMapping("/family-group/create")
//    public String createFamilyGroup(
//            @AuthenticationPrincipal CustomOAuth2User user,
//            @RequestParam(name = "memberEmail") String memberEmail,
//            @RequestParam(name = "relationship") String relationship,
//            Model model) {
//
//        model.addAttribute("currentUser", user.getUser());
//        model.addAttribute("memberEmail", memberEmail);
//        model.addAttribute("relationship", relationship);
//
//        return "family-management";
//    }

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
}


