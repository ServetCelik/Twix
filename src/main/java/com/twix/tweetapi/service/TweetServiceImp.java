package com.twix.tweetapi.service;

import com.twix.tweetapi.controller.modals.CreateTweetRequest;
import com.twix.tweetapi.repository.TweetRepository;
import com.twix.tweetapi.service.modals.TweetModal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TweetServiceImp implements TweetService {
    private final TweetRepository tweetRepository;

    @Override
    public List<TweetModal> getAllTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public List<TweetModal> getTweetsByUserName(String userName) {

        return tweetRepository.findByUserName(userName);
    }

    @Override
    public List<TweetModal> getTimeline(List<String> userNames) {
        return tweetRepository.findByUserNameIn(userNames);
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


//@Override
//    public List<TweetModal> getTweets() {
//        return List.of(
//                new TweetModal(1L, 101L, List.of(
//                        new TweetVersionModal(1L, 1L, "First tweet content", LocalDateTime.now()),
//                        new TweetVersionModal(2L, 1L, "Updated content of first tweet", LocalDateTime.now().plusMinutes(5))
//                )),
//                new TweetModal(2L, 102L, List.of(
//                        new TweetVersionModal(3L, 2L, "Second tweet content", LocalDateTime.now()),
//                        new TweetVersionModal(4L, 2L, "Updated content of second tweet", LocalDateTime.now().plusMinutes(10))
//                )),
//                new TweetModal(3L, 103L, List.of(
//                        new TweetVersionModal(5L, 3L, "Third tweet content", LocalDateTime.now()),
//                        new TweetVersionModal(6L, 3L, "Updated content of third tweet", LocalDateTime.now().plusMinutes(15))
//                )));
//    }