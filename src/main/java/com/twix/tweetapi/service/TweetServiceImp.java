package com.twix.tweetapi.service;

import com.twix.tweetapi.controller.modals.CreateTweetRequest;
import com.twix.tweetapi.repository.TweetRepository;
import com.twix.tweetapi.service.modals.TweetModal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class TweetServiceImp implements TweetService {
    private final TweetRepository tweetRepository;

    @Override
    public List<TweetModal> getAllTweets() {
        List<TweetModal> tweets = tweetRepository.findAll();
        Collections.reverse(tweets);
        return tweets;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TweetModal> getTweetsByUserName(String userName) {
        log.info("Fetching tweets for user: {}", userName);
        List<TweetModal> tweets = tweetRepository.findByUserName(userName);
        if (tweets.isEmpty()) {
            log.warn("No tweets found for user: {}", userName);
        }
        Collections.reverse(tweets);
        return tweets;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TweetModal> getTimeline(List<String> userNames) {
        log.info("Fetching timeline for users: {}", userNames);
        List<TweetModal> tweets = tweetRepository.findByUserNameIn(userNames);
        Collections.reverse(tweets);
        return tweets;
    }

    @Transactional
    @Override
    public Long newTweet(CreateTweetRequest tweetRequest) {
        log.info("Creating new tweet for user: {}", tweetRequest.getUserName());
        TweetModal savedTweet = TweetModal.builder()
                .userName(tweetRequest.getUserName())
                .content(tweetRequest.getContent())
                .build();

        savedTweet = tweetRepository.save(savedTweet);
        log.info("Tweet created with ID: {}", savedTweet.getId());
        return savedTweet.getId();
    }


    @Transactional
    @Override
    public Long deleteTweet(Long tweetId) {
        log.info("Deleting tweet with ID: {}", tweetId);
        if (!tweetRepository.existsById(tweetId)) {
            log.error("Tweet with ID: {} does not exist", tweetId);
            return -1L;
        }
        tweetRepository.deleteById(tweetId);
        log.info("Tweet deleted with ID: {}", tweetId);
        return tweetId;
    }

    @Transactional
    @Override
    public Long updateTweet(Long id, TweetModal tweet) {
        log.info("Updating tweet with ID: {}", id);
        TweetModal updatedTweet = tweetRepository.save(tweet);
        log.info("Tweet updated with ID: {}", updatedTweet.getId());
        return updatedTweet.getId();
    }
}