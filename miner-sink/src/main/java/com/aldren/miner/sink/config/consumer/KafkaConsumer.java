package com.aldren.miner.sink.config.consumer;

import com.aldren.miner.model.TweetSentiment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaConsumer {

    @Bean
    public Consumer<TweetSentiment> tweetSentiment() {
        log.info("Consuming from processed_tweets topic.");
        return tweet -> log.info(String.format("Tweet sentiment is %s.", tweet.getSentiment()));
    }

}
