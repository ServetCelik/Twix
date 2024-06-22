package com.twix.tweetapi.service;

public interface TokenService {
    boolean isAuthorized(String token, Long idFromParams);
    boolean isAuthorized(String token, String idFromParams);
}
