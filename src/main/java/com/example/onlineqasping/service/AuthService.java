package com.example.onlineqasping.service;

import com.example.onlineqasping.entity.User;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AuthService {
    private Map<String, User> users = new ConcurrentHashMap<>();
    private AtomicInteger userCounter = new AtomicInteger(1);

    public AuthService() {
        // 初始化示例用户
        User admin = User.builder()
                .id("U1")
                .username("admin")
                .password("123456")
                .age(25)
                .build();
        users.put("admin", admin);

        User user1 = User.builder()
                .id("U2")
                .username("user1")
                .password("123456")
                .age(30)
                .build();
        users.put("user1", user1);
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean register(String username, String password, int age) {
        if (users.containsKey(username)) {
            return false;
        }
        String userId = "U" + userCounter.incrementAndGet();
        User newUser = User.builder()
                .id(userId)
                .username(username)
                .password(password)
                .age(age)
                .build();
        users.put(username, newUser);
        return true;
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }
}