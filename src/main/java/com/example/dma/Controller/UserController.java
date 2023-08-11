package com.example.dma.Controller;

import com.example.dma.Auth.SessionManger;
import com.example.dma.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(@RequestBody HashMap<String, Object> userSignupData) {
        return userService.signup(userSignupData);
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody HashMap<String, Object> loginData, HttpServletResponse response) {
        return userService.login(loginData, response);
    }

    @GetMapping("/user/getUserInfo")
    public Object getSession(HttpServletRequest request) {
        return userService.getUserInfo(request);
    }

    @GetMapping("/user/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        userService.logout(request, response);
    }

    @GetMapping(value = "/user/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        userService.deleteUser(request, response);
    }

    @GetMapping(value = "/user/check")
    public boolean check(HttpServletRequest request) {
        return userService.loginCheck(request);
    }
}