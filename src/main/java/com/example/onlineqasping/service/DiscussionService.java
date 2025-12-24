package com.example.onlineqasping.service;

import com.example.onlineqasping.entity.Thread;
import com.example.onlineqasping.entity.Reply;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DiscussionService {
    private Map<String, Thread> threads = new ConcurrentHashMap<>();
    private Map<String, Reply> replies = new ConcurrentHashMap<>();
    private AtomicInteger threadCounter = new AtomicInteger(1);
    private AtomicInteger replyCounter = new AtomicInteger(1);

    public DiscussionService() {
        // 初始化示例话题
        Thread thread1 = new Thread("欢迎来到问答平台", "这是一个示例话题，欢迎大家讨论！", "admin");
        thread1.setId("T1");
        threads.put("T1", thread1);

        // 初始化示例回复
        Reply reply1 = Reply.builder()
                .id("R1")
                .content("感谢创建这个平台！")
                .author("user1")
                .threadId("T1")
                .replyTime(new Date())
                .build();
        replies.put("R1", reply1);
        thread1.addReply(reply1);
    }

    public List<Thread> getAllThreads() {
        List<Thread> threadList = new ArrayList<>(threads.values());
        threadList.sort((t1, t2) -> t2.getCreateTime().compareTo(t1.getCreateTime()));
        return threadList;
    }

    public Thread getThreadById(String id) {
        return threads.get(id);
    }

    public void createThread(String title, String content, String author) {
        String threadId = "T" + threadCounter.incrementAndGet();
        Thread newThread = new Thread(title, content, author);
        newThread.setId(threadId);
        threads.put(threadId, newThread);
    }

    public void addReply(String threadId, String content, String author) {
        Thread thread = threads.get(threadId);
        if (thread != null) {
            String replyId = "R" + replyCounter.incrementAndGet();
            Reply reply = new Reply(content, author, threadId);
            reply.setId(replyId);
            replies.put(replyId, reply);
            thread.addReply(reply);
        }
    }
}