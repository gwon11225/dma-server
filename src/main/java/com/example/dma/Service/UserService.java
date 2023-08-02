package com.example.dma.Service;

import com.example.dma.Auth.SessionManger;
import com.example.dma.Domain.User;
import com.example.dma.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SessionManger sessionManger;

    @Transactional
    public void insertUserData(HashMap<String, Object> user_info) {
        userRepository.insertUserData((String) user_info.get("name"), (String) user_info.get("email"), (String) user_info.get("password"));
    }

    public void login(HashMap<String, Object> loginData, HttpServletResponse response) {
        if(isLogin((String) loginData.get("email"), (String) loginData.get("password"))) {
            User user = userRepository.findUserByEmail((String) loginData.get("email"));
            sessionManger.createSession(user, response);
        }
    }

    private boolean isLogin(String email, String password) {
        if(userRepository.isLogin(email, password) == 1){
            return true;
        } else {
            return false;
        }
    }

    public void logout(HttpServletRequest request) {
        sessionManger.expire(request);
    }

    public Object getUserInfo(HttpServletRequest request) {
        return sessionManger.getSession(request);
    }


}
