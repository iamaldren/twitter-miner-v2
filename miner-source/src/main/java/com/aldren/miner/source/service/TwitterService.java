package com.aldren.miner.source.service;

import org.springframework.social.twitter.api.Tweet;

import java.util.List;

public interface TwitterService {

    List<Tweet> mineTweets(String keyword);

}
