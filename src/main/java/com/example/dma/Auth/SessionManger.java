package com.example.dma.Auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManger {
    private String SESSION_COOKIE_NAME = "dmaSessionId";

    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    public void createSession(Object value, HttpServletResponse response) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        mySessionCookie.setHttpOnly(true);
        mySessionCookie.setPath("/");
        response.addCookie(mySessionCookie);
    }

    private Cookie findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie == null) {
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
    }

    public void expire(HttpServletRequest request, HttpServletResponse response) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
            sessionCookie.setMaxAge(0);
            sessionCookie.setPath("/");
            response.addCookie(sessionCookie);
        }
    }

    public boolean isLogin(HttpServletRequest request) {
        if(getSession(request) != null){
            return true;
        } else {
            return false;
        }
    }
}