package com.twix.tweetapi.service;

import com.twix.tweetapi.controller.modals.CreateTweetRequest;
import com.twix.tweetapi.service.modals.TweetModal;

import java.util.List;

public interface TweetService {
    List<TweetModal> getAllTweets();

    Long newTweet(CreateTweetRequest tweet);

    List<TweetModal> getTweetsByUserName(String userName);
    List<TweetModal> getTimeline(List<String> userNames);

    Long deleteTweet(Long tweetId);

    Long updateTweet(Long id, TweetModal tweet);
}
