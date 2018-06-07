package com.sajeelbong.reddit.service;

import com.sajeelbong.reddit.fetcher.TokenFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TokenFetcherService {

    @Autowired
    private TokenFetcher tokenFetcher;

    public void generateAccessToken() {
        HttpHeaders headers = tokenFetcher.createHeaders("1HvmbvDJOGAodw", "xy2z-cqOoJeKna4o0ifs5HVeqgU");
        HttpEntity<MultiValueMap<String, String>> request = tokenFetcher.getBody(headers);
        String accessToken = tokenFetcher.getToken(tokenFetcher.tokenUrl, request);
        tokenFetcher.getSelfDetails(accessToken);
    }
}
