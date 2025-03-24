package com.twix.tweetapi.controller;

import com.google.gson.Gson;
import com.twix.tweetapi.controller.modals.CreateTweetRequest;
import com.twix.tweetapi.controller.modals.UserSharable;
import com.twix.tweetapi.service.TweetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class Receiver {
    private final TweetService tweetService;


    @RabbitListener(queues = "user_queue")
    public void receiveMessage(String user) {
        UserSharable userSharable = stringToUserSharable(user);
        tweetService.newTweet(CreateTweetRequest.builder()
                .userName(userSharable.getUserName())
                .content("Hi everyone, I am new on Twix")
                .build());

        log.info("User with this name {} is created at {}", user, LocalDateTime.now());
    }


    private UserSharable stringToUserSharable(String userString) {
        Gson gson = new Gson();

        // Convert JSON string to UserEntity object
        return gson.fromJson(userString, UserSharable.class);
    }
}