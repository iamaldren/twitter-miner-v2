package com.aldren.miner.processor.config;

import com.aldren.miner.model.TweetSentiment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.Queue;

@Configuration
public class QueueConfig {

    @Bean
    public Queue<TweetSentiment> tweetSentimentQueue() {
        return new LinkedList<>();
    }

}
