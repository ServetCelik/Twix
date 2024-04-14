package com.twix.tweetapi.repository;

import com.twix.tweetapi.service.modals.TweetModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<TweetModal, Long> {
    List<TweetModal> findByUserName(String userName);
}
