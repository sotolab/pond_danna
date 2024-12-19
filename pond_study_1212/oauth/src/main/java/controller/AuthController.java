package controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

//    @GetMapping("/user-info")
//    public String userInfo(Model model, @AuthenticationPrincipal OAuth2User oauth2User) {
//        if (oauth2User != null) {
//            model.addAttribute("userName", oauth2User.getAttribute("name"));
//            model.addAttribute("userEmail", oauth2User.getAttribute("email"));
//        }
//        return "user-info";
//    }

//    @GetMapping("/")
//    public String index(Model model, @AuthenticationPrincipal(errorOnInvalidType = false) OAuth2User oauth2User) {
//        if (oauth2User != null) {
//            model.addAttribute("userName", oauth2User.getAttribute("name"));
//            model.addAttribute("userEmail", oauth2User.getAttribute("email"));
//        }
//        return "index";
//    }

}
