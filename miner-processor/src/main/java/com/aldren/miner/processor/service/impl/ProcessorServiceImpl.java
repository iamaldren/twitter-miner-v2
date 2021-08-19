package com.aldren.miner.processor.service.impl;

import com.aldren.miner.processor.model.TweetSentiment;
import com.aldren.miner.processor.service.ProcessorService;
import com.aldren.miner.processor.service.SentimentService;
import lombok.AllArgsConstructor;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
@AllArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {

    private SentimentService sentimentService;
    private Queue<TweetSentiment> tweetSentimentQueue;

    @Override
    public void processTweets(Tweet tweet) {
        int sentiment = sentimentService.analyzeSentiment(tweet.getText());

        tweetSentimentQueue.add(TweetSentiment.builder()
                .tweet(tweet.getText())
                .user(tweet.getFromUser())
                .retweetCount(tweet.getRetweetCount())
                .favoriteCount(tweet.getFavoriteCount())
                .sentiment(sentiment).build());
    }

}
