package com.aldren.miner.processor.service;

import org.springframework.social.twitter.api.Tweet;

public interface ProcessorService {

    void processTweets(Tweet tweet);

}
