package com.example.onlineqasping.controller;

import com.example.onlineqasping.entity.Thread;
import com.example.onlineqasping.entity.User;
import com.example.onlineqasping.service.DiscussionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/discussions")
public class DiscussionController {
    private final DiscussionService discussionService;

    @GetMapping
    public String listDiscussions(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("threads", discussionService.getAllThreads());
        return "discussion-list";
    }

    @GetMapping("/create")
    public String showCreateForm(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "discussion-create";
    }

    @PostMapping("/create")
    public String createThread(@RequestParam String title,
                               @RequestParam String content,
                               HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        discussionService.createThread(title, content, user.getUsername());
        return "redirect:/discussions";
    }

    @GetMapping("/{id}")
    public String viewThread(@PathVariable String id,
                             HttpSession session,
                             Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Thread thread = discussionService.getThreadById(id);
        if (thread == null) {
            return "redirect:/discussions";
        }

        model.addAttribute("thread", thread);
        return "discussion-detail";
    }

    @PostMapping("/{id}/reply")
    public String addReply(@PathVariable String id,
                           @RequestParam String content,
                           HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        discussionService.addReply(id, content, user.getUsername());
        return "redirect:/discussions/" + id;
    }
}