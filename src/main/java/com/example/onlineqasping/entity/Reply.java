package com.example.onlineqasping.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply implements Serializable {
    private String id;
    private String content;
    private String author;
    private Date replyTime;
    private String threadId;

    public Reply(String content, String author, String threadId) {
        this.content = content;
        this.author = author;
        this.threadId = threadId;
        this.replyTime = new Date();
    }
}