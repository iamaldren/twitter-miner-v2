package com.aldren.miner.processor.service.impl;

import com.aldren.miner.model.ParsedTweet;
import com.aldren.miner.model.TweetSentiment;
import com.aldren.miner.processor.service.ProcessorService;
import com.aldren.miner.processor.service.SentimentService;
import com.aldren.miner.processor.util.Sentiment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Slf4j
@Service
@AllArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {

    private SentimentService sentimentService;
    private Queue<TweetSentiment> tweetSentimentQueue;

    @Override
    public void processTweets(ParsedTweet tweet) {
        log.info(String.format("Processing tweet for user::%s", tweet.getUser()));

        String str = tweet.getTweet();
        int sentiment = sentimentService.analyzeSentiment(str);

        if(sentiment != -1) {
            TweetSentiment tweetSentiment = new TweetSentiment();
            tweetSentiment.setTweet(tweet.getTweet());
            tweetSentiment.setUser(tweet.getUser());
            tweetSentiment.setRetweetCount(tweet.getRetweetCount());
            tweetSentiment.setFavoriteCount(tweet.getFavoriteCount());
            tweetSentiment.setPolarity(sentiment);
            tweetSentiment.setSentiment(getSentiment(sentiment));

            tweetSentimentQueue.add(tweetSentiment);
        }
    }

    private String getSentiment(int polarity) {
        Sentiment sentiment = Sentiment.getByPoint(polarity);
        return sentiment.sentimentText;
    }

}
