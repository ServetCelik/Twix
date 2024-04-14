package com.twix.tweetapi.controller;

import com.twix.tweetapi.controller.modals.CreateTweetRequest;
import com.twix.tweetapi.service.TweetService;
import com.twix.tweetapi.service.modals.TweetModal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class TweetController {
    private final TweetService tweetService;


    @GetMapping("/")
    public ResponseEntity<List<TweetModal>> getAllTweets() {

        return ResponseEntity.ok(tweetService.getAllTweets());
    }

    @GetMapping("/{userName}")
    public ResponseEntity<List<TweetModal>> getTweetsByUserName(@PathVariable String userName) {

        return ResponseEntity.ok(tweetService.getTweetsByUserName(userName));
    }

    @PostMapping("/")
    public ResponseEntity<Long> newTweet(@RequestBody CreateTweetRequest tweetRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(tweetService.newTweet(tweetRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTweet(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tweetService.deleteTweet(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTweet(@PathVariable("id") long id
            , @RequestBody TweetModal userEntity) {
        //asdasdajdas
        return ResponseEntity.status(HttpStatus.OK).
                body(tweetService.updateTweet(id, userEntity));
    }

}


//        rabbitTemplate.convertAndSend("answers", "#", null, m -> {
//            m.getMessageProperties().getHeaders().put("MessageType", "CreateAnswerEvent");
//            return m;
//        });
