package com.aldren.miner.processor.config.producer;

import com.aldren.miner.model.TweetSentiment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;
import java.util.function.Supplier;

@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaProducer {

    private Queue<TweetSentiment> tweetSentimentQueue;

    @Bean
    public Supplier<TweetSentiment> processedTweets() {
        log.info("Producing to processed_tweets topic.");
        if(tweetSentimentQueue.isEmpty()) {
            try {
                log.warn("Queue is empty, thread will sleep.");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                log.warn(e.getLocalizedMessage(), e);
                Thread.currentThread().interrupt();
            }
        }

        return () -> tweetSentimentQueue.poll();
    }

}
