package com.example.onlineqasping.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class CaptchaController {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    private static final int CODE_LENGTH = 4;
    private Random random = new Random();

    @GetMapping("/captcha")
    public void generateCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应类型
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

        // 创建图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 绘制干扰线
        drawInterferenceLines(g);

        // 生成验证码
        String captchaCode = generateCaptchaCode();

        // 绘制验证码文字
        drawCaptchaText(g, captchaCode);

        // 存储验证码到session
        session.setAttribute("captcha", captchaCode);

        // 输出图片
        ImageIO.write(image, "JPEG", response.getOutputStream());
        g.dispose();
    }

    private String generateCaptchaCode() {
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }

    private void drawCaptchaText(Graphics2D g, String code) {
        g.setFont(new Font("Arial", Font.BOLD, 24));
        for (int i = 0; i < code.length(); i++) {
            g.setColor(new Color(random.nextInt(128), random.nextInt(128), random.nextInt(128)));
            int x = 20 + i * 25;
            int y = 25 + random.nextInt(10);
            double angle = random.nextDouble() * 0.5 - 0.25;

            g.rotate(angle, x, y);
            g.drawString(String.valueOf(code.charAt(i)), x, y);
            g.rotate(-angle, x, y);
        }
    }

    private void drawInterferenceLines(Graphics2D g) {
        for (int i = 0; i < 10; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }

        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            g.drawLine(x, y, x, y);
        }
    }
}