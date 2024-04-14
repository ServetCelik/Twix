package com.twix.tweetapi.service.modals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetVersionModal {
    public Long id;
    public Long tweetId;
    public String content;
    public LocalDateTime createdAt;
}
