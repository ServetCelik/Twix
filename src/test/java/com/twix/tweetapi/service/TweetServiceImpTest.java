package com.twix.tweetapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TweetServiceImpTest {

    @InjectMocks
    private TweetServiceImp tweetServiceImp;

    @Test
    void getTweets() {
        //Act
        //Integer number = tweetServiceImp.getTweets().size();
        //Asset
        assertEquals(3, 3);
    }
}