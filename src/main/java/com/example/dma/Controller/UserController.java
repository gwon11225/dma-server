package com.example.dma.Controller;

import com.example.dma.Auth.SessionManger;
import com.example.dma.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SessionManger sessionManger;

    @PostMapping("/user/signup")
    public void signup(@RequestBody HashMap<String, Object> userSignupData) {
        userService.insertUserData(userSignupData);
    }

    @PostMapping("/user/login")
    public void login(@RequestBody HashMap<String, Object> loginData, HttpServletResponse response) {
        userService.login(loginData, response);
    }

    @GetMapping("/user/getUserInfo")
    public Object getSession(HttpServletRequest request) {
        return userService.getUserInfo(request);
    }

    @PostMapping("/user/logout")
    public void logout(HttpServletRequest httpServletRequest){
        userService.logout(httpServletRequest);
    }

}
