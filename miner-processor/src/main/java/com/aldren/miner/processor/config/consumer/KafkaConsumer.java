package com.aldren.miner.processor.config.consumer;

import com.aldren.miner.model.ParsedTweet;
import com.aldren.miner.processor.service.ProcessorService;
import com.aldren.miner.processor.service.UserCacheService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaConsumer {

    private ProcessorService processorService;
    private UserCacheService userCacheService;

    @Bean
    public Consumer<List<ParsedTweet>> minedTweets() {
        log.info("Consuming from mined_tweets topic.");
        return tweets -> tweets.stream()
                .filter(tweet -> tweet != null)
                .filter(tweet -> !userCacheService.isUserExceedThreshold(tweet.getUser()))
                .forEach(tweet -> processorService.processTweets(tweet));
    }

}
