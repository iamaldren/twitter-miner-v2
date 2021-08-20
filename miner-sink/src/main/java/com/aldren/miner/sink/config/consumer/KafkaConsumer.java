package com.aldren.miner.sink.config.consumer;

import com.aldren.miner.model.TweetSentiment;
import com.aldren.miner.sink.service.ElasticSearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaConsumer {

    private ElasticSearchService elasticSearchService;

    @Bean
    public Consumer<TweetSentiment> tweetSentiment() {
        log.info("Consuming from processed_tweets topic.");
        return tweet -> elasticSearchService.saveTweetSentimentToES(tweet);
    }

}
