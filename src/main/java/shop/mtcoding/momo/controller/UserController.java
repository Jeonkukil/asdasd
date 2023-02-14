package shop.mtcoding.momo.controller;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import shop.mtcoding.momo.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.momo.dto.user.UserReq.LoginReqDto;
import shop.mtcoding.momo.handler.ex.CustomException;
import shop.mtcoding.momo.model.User;
import shop.mtcoding.momo.model.UserRepository;
import shop.mtcoding.momo.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/profileUpdateForm")
    public String profileUpdateForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }
        User userPS = userRepository.findById(principal.getId());
        model.addAttribute("user", userPS);
        return "user/profileUpdateForm";
    }

    @PostMapping("/user/profileUpdate")
    public @ResponseBody String profileUpdate(MultipartFile profile) {
        System.out.println(profile.getContentType());
        System.out.println(profile.getSize());
        System.out.println(profile.getOriginalFilename());

        if (profile.isEmpty()) {
            throw new CustomException("사진이 전송되지 않았습니다");
        }

        // 1번 파일은 하드디스크에 저장
        String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\";
        System.out.println(savePath);
        Path imageFilePath = Paths.get(savePath + "\\" + profile.getOriginalFilename());

        System.out.println(imageFilePath);

        try {
            Files.write(imageFilePath, profile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2번 저장된 파일의 경로를 DB에 저장

        return "ok";
    }

    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto) {
        if (joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 작성해주세요");
        }
        if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해주세요");
        }
        if (joinReqDto.getEmail() == null || joinReqDto.getEmail().isEmpty()) {
            throw new CustomException("email을 작성해주세요");
        }

        userService.회원가입(joinReqDto);

        return "redirect:/loginForm"; // 302
    }

    @PostMapping("/login")
    public String login(LoginReqDto loginReqDto) {
        if (loginReqDto.getUsername() == null || loginReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 작성해주세요");
        }
        if (loginReqDto.getPassword() == null || loginReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해주세요");
        }
        User principal = userService.로그인(loginReqDto);
        session.setAttribute("principal", principal);
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
        session.invalidate();
        return "redirect:/";
    }
}