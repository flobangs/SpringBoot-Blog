package shop.mtcoding.metablog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.metablog.dto.user.UserRequest;
import shop.mtcoding.metablog.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(UserRequest.JoinInDTO joinInDTO) {
        System.out.println(joinInDTO.getEmail());
        System.out.println(joinInDTO.getPassword());

        userService.joinUser(joinInDTO);

        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
}