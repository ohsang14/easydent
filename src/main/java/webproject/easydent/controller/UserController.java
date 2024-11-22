package webproject.easydent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webproject.easydent.entities.User;
import webproject.easydent.service.UserService;
import webproject.easydent.vos.CustomOAuth2User;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        if (customOAuth2User != null) {
            User user = customOAuth2User.getUser();
            log.info("Authenticated user: {}", user);
            addUserToModel(model, user);
        }
        else{
            log.info("User is Null");
        }
        return "home";
    }

    @GetMapping("/mypage")
    public String myPage(Model model, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        if (customOAuth2User != null) {
            User user = customOAuth2User.getUser();
            log.info("Authenticated user: {}", user);
            addUserToModel(model, user);
        }
        return "mypage";
    }



    private void addUserToModel(Model model, User user) {
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userName", user.getName());
        model.addAttribute("accountType", user.getAccountType());
        model.addAttribute("phoneNumber", user.getPhoneNumber());
        model.addAttribute("birthDay", user.getBirthDay());
        model.addAttribute("createdAt", user.getCreatedAt());
        model.addAttribute("address", user.getAddress());
    }

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

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute User user,
                             @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        if (customOAuth2User != null) {
            user.setEmail(customOAuth2User.getUser().getEmail());  // 현재 로그인한 사용자의 ID 설정
            log.info("Authenticated user: {}", user);
            userService.updateUser(user);

        }

        return "redirect:/mypage";
    }

    @GetMapping("/notice")
    public String notice(){
        return "notice";
    }

    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }

    @GetMapping("/medical-history")
    public String medicalHistory(){
        return "medical-history";
    }

    @GetMapping("/customer-center")
    public String customerCenter(){
        return "customer-center";
    }



//    @GetMapping("/consultation")
//    public String consultation(){
//        return "consultation";
//    }
//    @GetMapping("/reservation")
//    public String reservation(){
//        return "reservation";
//    }
}