package com.twix.tweetapi.controller;

import com.twix.tweetapi.controller.modals.CreateTweetRequest;
import com.twix.tweetapi.controller.modals.GetTimelineRequest;
import com.twix.tweetapi.service.TokenService;
import com.twix.tweetapi.service.TweetService;
import com.twix.tweetapi.service.modals.TweetModal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TweetController {
    private final TweetService tweetService;
    private final TokenService tokenService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public ResponseEntity<String> getTestString() {

        return ResponseEntity.ok("9");
    }


    @GetMapping("/")
    public ResponseEntity<List<TweetModal>> getAllTweets() {

        return ResponseEntity.ok(tweetService.getAllTweets());
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<List<TweetModal>> getTweetsByUserName(@PathVariable String userName, @RequestHeader("Authorization") String token) {
        if (tokenService.isAuthorized(token,userName)){
            return ResponseEntity.ok(tweetService.getTweetsByUserName(userName));
        }else{
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/timeline")
    public ResponseEntity<List<TweetModal>> getTimeline(@RequestBody GetTimelineRequest timelineRequest) {

        return ResponseEntity.ok(tweetService.getTimeline(timelineRequest.userNames));
    }

    @PostMapping("/")
    public ResponseEntity<Long> newTweet(@RequestBody CreateTweetRequest tweetRequest, @RequestHeader("Authorization") String token) {

        if (tokenService.isAuthorized(token,tweetRequest.getUserName())){
                return ResponseEntity.status(HttpStatus.CREATED).body(tweetService.newTweet(tweetRequest));
        }else{
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTweet(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (tokenService.isAuthorized(token,id)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(tweetService.deleteTweet(id));
        }else{
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTweet(@PathVariable("id") long id
            , @RequestBody TweetModal userEntity, @RequestHeader("Authorization") String token) {
        if (tokenService.isAuthorized(token,id)){
            return ResponseEntity.status(HttpStatus.OK).
                    body(tweetService.updateTweet(id, userEntity));
        }else{
            return ResponseEntity.status(403).build();
        }
    }

}