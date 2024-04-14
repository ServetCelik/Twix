package com.twix.tweetapi.service.modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tweet")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String userName;
    public String content;
}
