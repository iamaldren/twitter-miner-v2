package com.aldren.miner.processor.service.impl;

import com.aldren.miner.processor.model.ParsedTweet;
import com.aldren.miner.processor.model.TweetSentiment;
import com.aldren.miner.processor.service.ProcessorService;
import com.aldren.miner.processor.service.SentimentService;
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
        String str = tweet.getTweet();
        int sentiment = sentimentService.analyzeSentiment(str);

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Tweet by " + tweet.getUser() + " with sentiment of " + sentiment);
        if(sentiment != -2) {
            TweetSentiment tweetSentiment = new TweetSentiment();
            tweetSentiment.setTweet(tweet.getTweet());
            tweetSentiment.setUser(tweet.getUser());
            tweetSentiment.setRetweetCount(tweet.getRetweetCount());
            tweetSentiment.setFavoriteCount(tweet.getFavoriteCount());
            tweetSentiment.setSentiment(Double.valueOf(sentiment));

            tweetSentimentQueue.add(tweetSentiment);
        }
    }

}
