package com.twix.tweetapi.service;

import com.twix.tweetapi.controller.modals.CreateTweetRequest;
import com.twix.tweetapi.repository.TweetRepository;
import com.twix.tweetapi.service.modals.TweetModal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@AllArgsConstructor
public class TweetServiceImp implements TweetService {
    private final TweetRepository tweetRepository;

    @Override
    public List<TweetModal> getAllTweets() {
        List<TweetModal> tweets = tweetRepository.findAll();
        Collections.reverse(tweets);
        return tweets;
    }

    @Override
    public List<TweetModal> getTweetsByUserName(String userName) {

        List<TweetModal> tweets = tweetRepository.findByUserName(userName);
        Collections.reverse(tweets);
        return tweets;
    }

    @Override
    public List<TweetModal> getTimeline(List<String> userNames) {
        List<TweetModal> tweets = tweetRepository.findByUserNameIn(userNames);
        Collections.reverse(tweets);
        return tweets;
    }

    @Override
    public Long newTweet(CreateTweetRequest tweet) {
        TweetModal savedTweet = TweetModal.builder()
                .userName(tweet.getUserName())
                .content(tweet.getContent())
                .build();

        return tweetRepository.save(savedTweet).getId();
    }


    @Override
    public Long deleteTweet(Long tweetId) {
        tweetRepository.deleteById(tweetId);
        return tweetId;
    }

    @Override
    public Long updateTweet(Long id, TweetModal tweet) {
        return tweetRepository.save(tweet).getId();
    }
}