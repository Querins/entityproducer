package com.example.kafkaproducer.service;

import com.example.kafkaproducer.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class OnContextStartedListener {

    @Value("${app.topic.users}")
    private String topic;
    private KafkaTemplate<Integer, User> template;
    private static final List<User> users = Collections.unmodifiableList(Arrays
                    .asList(new User(0,"test1", 1),
                            new User(1,"test2", 2),
                            new User(2,"test3", 3),
                            new User(3,"test4", 4),
                            new User(4,"test5", 5)));

    public OnContextStartedListener(KafkaTemplate<Integer, User> template) {
        this.template = template;
    }

    @EventListener
    public void onContextStartup(ApplicationStartedEvent event) {
        new Thread(() -> {
           for(int i = 0; i < 5; i++) {
               template.send(topic, users.get(i));
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) { }
           }
        }).start();
    }
}
