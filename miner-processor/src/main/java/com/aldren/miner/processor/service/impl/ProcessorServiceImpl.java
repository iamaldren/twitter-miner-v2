package com.aldren.miner.processor.service.impl;

import com.aldren.miner.processor.model.TweetSentiment;
import com.aldren.miner.processor.service.ProcessorService;
import com.aldren.miner.processor.service.SentimentService;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

@Service
public class ProcessorServiceImpl implements ProcessorService {

    private SentimentService sentimentService;

    public ProcessorServiceImpl(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @Override
    public void processTweets(Tweet tweet) {
        int sentiment = sentimentService.analyzeSentiment(tweet.getText());

        TweetSentiment tweetSentiment = TweetSentiment.builder()
                .tweet(tweet.getText())
                .user(tweet.getFromUser())
                .retweetCount(tweet.getRetweetCount())
                .favoriteCount(tweet.getFavoriteCount())
                .sentiment(sentiment).build();
    }

}
