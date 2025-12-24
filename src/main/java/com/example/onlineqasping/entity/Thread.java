package com.example.onlineqasping.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thread implements Serializable {
    private String id;
    private String title;
    private String content;
    private String author;
    private Date createTime;
    private List<Reply> replies;

    public Thread(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createTime = new Date();
        this.replies = new ArrayList<>();
    }

    public void addReply(Reply reply) {
        if (this.replies == null) {
            this.replies = new ArrayList<>();
        }
        this.replies.add(reply);
    }
}