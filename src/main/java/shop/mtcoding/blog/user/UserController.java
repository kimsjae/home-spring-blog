package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        System.out.println(joinDTO);

        userRepository.save(joinDTO);
        return "redirect:/loginForm";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        System.out.println(loginDTO);

        if (loginDTO.getUsername().length() < 2) return "error/400";

        User user = userRepository.findByUsernameAndPassword(loginDTO);

        if (user == null) {
            return "error/400";
        } else {
            session.setAttribute("sessionUser", user);
        }
        return "redirect:/";
    }



    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }


    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
