package com.aldren.miner.sink.service;

import com.aldren.miner.model.TweetSentiment;

public interface ElasticSearchService {

    void saveTweetSentimentToES(TweetSentiment tweetSentiment);

}
