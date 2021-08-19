package com.aldren.miner.processor.service;

import com.aldren.miner.processor.model.ParsedTweet;

public interface ProcessorService {

    void processTweets(ParsedTweet tweet);

}
