package com.twix.tweetapi;

import com.twix.tweetapi.controller.modals.CreateTweetRequest;
import com.twix.tweetapi.repository.TweetRepository;
import com.twix.tweetapi.service.TweetServiceImp;
import com.twix.tweetapi.service.modals.TweetModal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TweetServiceImpTest {

    @Mock
    private TweetRepository tweetRepository;

    @InjectMocks
    private TweetServiceImp tweetService;


    @Test
    public void getAllTweets_ShouldReturnReversedTweets() {
        // Setup
        List<TweetModal> tweets = Arrays.asList(
                new TweetModal(1L, "user1", "Hello"),
                new TweetModal(2L, "user2", "World")
        );
        when(tweetRepository.findAll()).thenReturn(tweets);

        // Action
        List<TweetModal> result = tweetService.getAllTweets();

        // Assert
        assertEquals(2, result.size());
        assertEquals("World", result.get(0).getContent());  // Check if tweets are reversed
        verify(tweetRepository).findAll();
    }

    @Test
    public void getTweetsByUserName_ShouldReturnReversedTweetsForUser() {
        // Setup
        List<TweetModal> tweets = Collections.singletonList(new TweetModal(1L, "user1", "Hello"));
        when(tweetRepository.findByUserName("user1")).thenReturn(tweets);

        // Action
        List<TweetModal> result = tweetService.getTweetsByUserName("user1");

        // Assert
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getUserName());
        verify(tweetRepository).findByUserName("user1");
    }

    @Test
    public void getTimeline_ShouldReturnReversedTweetsForUsers() {
        // Setup
        List<String> userNames = Arrays.asList("user1", "user2");
        List<TweetModal> tweets = Arrays.asList(
                new TweetModal(1L, "user1", "Hi"),
                new TweetModal(2L, "user2", "There")
        );
        when(tweetRepository.findByUserNameIn(userNames)).thenReturn(tweets);

        // Action
        List<TweetModal> result = tweetService.getTimeline(userNames);

        // Assert
        assertEquals(2, result.size());
        assertEquals("There", result.get(0).getContent());
        verify(tweetRepository).findByUserNameIn(userNames);
    }

    @Test
    public void newTweet_ShouldCreateAndReturnNewTweetId() {
        // Setup
        CreateTweetRequest request = new CreateTweetRequest("user1", "Hello");
        TweetModal savedTweet = new TweetModal(1L, "user1", "Hello");
        when(tweetRepository.save(any(TweetModal.class))).thenReturn(savedTweet);

        // Action
        Long result = tweetService.newTweet(request);

        // Assert
        assertEquals(1L, result);
        verify(tweetRepository).save(any(TweetModal.class));
    }

    @Test
    public void deleteTweet_ShouldDeleteTweetIfItExists() {
        // Setup
        when(tweetRepository.existsById(1L)).thenReturn(true);

        // Action
        Long result = tweetService.deleteTweet(1L);

        // Assert
        assertEquals(1L, result);
        verify(tweetRepository).deleteById(1L);
    }

    @Test
    public void updateTweet_ShouldUpdateAndReturnUpdatedTweetId() {
        // Setup
        TweetModal tweet = new TweetModal(1L, "user1", "Updated");
        when(tweetRepository.save(tweet)).thenReturn(tweet);

        // Action
        Long result = tweetService.updateTweet(1L, tweet);

        // Assert
        assertEquals(1L, result);
        verify(tweetRepository).save(tweet);
    }
}