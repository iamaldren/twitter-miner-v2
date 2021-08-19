package com.aldren.miner.processor.config.consumer;

import com.aldren.miner.processor.service.ProcessorService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Tweet;

import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class KafkaConsumer {

    private ProcessorService processorService;

    @Bean
    public Consumer<Tweet> minedTweets() {
        return tweet -> processorService.processTweets(tweet);
    }

}
