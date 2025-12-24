package com.example.onlineqasping.controller;

import com.example.onlineqasping.entity.User;
import com.example.onlineqasping.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // ========== 添加这个首页映射 ==========
    @GetMapping("/")
    public String home(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // 已登录，跳转到讨论列表
            return "redirect:/discussions";
        } else {
            // 未登录，跳转到登录页面
            return "redirect:/login";
        }
    }
    // ===================================

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String captcha,
                        HttpSession session,
                        Model model) {
        // 验证验证码
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(captcha)) {
            model.addAttribute("error", "验证码错误！");
            return "login";
        }

        // 验证用户
        User user = authService.authenticate(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.removeAttribute("captcha");
            return "redirect:/discussions";
        } else {
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam int age,
                           Model model) {
        boolean success = authService.register(username, password, age);
        if (success) {
            model.addAttribute("message", "注册成功，请登录！");
        } else {
            model.addAttribute("error", "用户名已存在！");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}