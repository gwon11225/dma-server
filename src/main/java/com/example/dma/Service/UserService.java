package com.example.dma.Service;

import com.example.dma.Auth.SessionManger;
import com.example.dma.DTO.User;
import com.example.dma.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SessionManger sessionManger;

    @Transactional
    public ResponseEntity<String> signup(HashMap<String, Object> user_info) {
        userRepository.insertUserData((String) user_info.get("name"), (String) user_info.get("email"), (String) user_info.get("password"));
        return ResponseEntity.ok("회원가입 성공");
    }

    public ResponseEntity<String> login(HashMap<String, Object> loginData, HttpServletResponse response) {
        if(isLogin((String) loginData.get("email"), (String) loginData.get("password"))) {
            User user = userRepository.findUserByEmail((String) loginData.get("email"));
            sessionManger.createSession(user, response);
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }

    private boolean isLogin(String email, String password) {
        if(userRepository.isLogin(email, password) == 1){
            return true;
        } else {
            return false;
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        sessionManger.expire(request, response);
    }

    public Object getUserInfo(HttpServletRequest request) {
        return sessionManger.getSession(request);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        User userInfo = (User) sessionManger.getSession(request);
        userRepository.deleteById(userInfo.getId());
        sessionManger.expire(request, response);
    }

    public boolean loginCheck(HttpServletRequest request) {
        return sessionManger.isLogin(request);
    }
}