package com.twix.tweetapi.controller;

import com.twix.tweetapi.controller.modals.CreateTweetRequest;
import com.twix.tweetapi.controller.modals.UserSharable;
import com.twix.tweetapi.service.TweetService;
import com.twix.tweetapi.service.modals.TweetModal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
//@CrossOrigin("http://localhost:3000")
public class TweetController {
    private final TweetService tweetService;
    @Autowired
    private RestTemplate restTemplate;


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
        Optional<UserSharable> user = Optional.ofNullable(restTemplate.getForObject("http://user-api:8082/user/username/" + tweetRequest.getUserName(), UserSharable.class));

        if (user.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(tweetService.newTweet(tweetRequest));
        }
        return ResponseEntity.badRequest().body(-1L);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTweet(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tweetService.deleteTweet(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTweet(@PathVariable("id") long id
            , @RequestBody TweetModal userEntity) {
        return ResponseEntity.status(HttpStatus.OK).
                body(tweetService.updateTweet(id, userEntity));
    }

}


//        rabbitTemplate.convertAndSend("answers", "#", null, m -> {
//            m.getMessageProperties().getHeaders().put("MessageType", "CreateAnswerEvent");
//            return m;
//        });
