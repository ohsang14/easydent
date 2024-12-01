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


}


