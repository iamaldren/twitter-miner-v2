package com.aldren.miner.processor.config.producer;

import com.aldren.miner.processor.model.TweetSentiment;
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
        if(tweetSentimentQueue.isEmpty()) {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage(), e);
                Thread.currentThread().interrupt();
            }
        }

        return () -> tweetSentimentQueue.poll();
    }

}
