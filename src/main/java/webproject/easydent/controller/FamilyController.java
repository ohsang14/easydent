package webproject.easydent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webproject.easydent.entities.User;
import webproject.easydent.service.FamilyAccountService;
import webproject.easydent.vos.CustomOAuth2User;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyAccountService familyAccountService;

    @GetMapping("/family-management")
    public String familManagement(Model model, @AuthenticationPrincipal CustomOAuth2User user){
        log.info("user: {}", user);
        return "family-management";
    }

    @PostMapping("/family-group/create")
    public String createFamilyGroup(@AuthenticationPrincipal CustomOAuth2User user,
                                    @RequestParam String familyEmail) {
        familyAccountService.createFamilyGroup(user.getUser(), familyEmail);
        log.info("User: {}", user);  // 사용자 정보 로깅
        log.info("Family Email: {}", familyEmail);  // 요청 파라미터 로깅

        return "redirect:/family-management";
    }

    @PostMapping("/family-group/add-member")
    public String addFamilyMember(@AuthenticationPrincipal CustomOAuth2User leader,
                                  @RequestParam String memberEmail,
                                  @RequestParam String relationship) {  // relationship 파라미터 추가
        familyAccountService.addFamilyMember(
                leader.getUser().getEmail(),
                memberEmail,
                relationship  // relationship 전달
        );
        return "redirect:/family-management";
    }
}
