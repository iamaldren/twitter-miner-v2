package com.aldren.miner.processor.config.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Tweet;

import java.util.function.Consumer;

@Configuration
public class KafkaConsumer {

    @Bean
    public Consumer<Tweet> minedTweets() {
        return tweet -> {
            tweet.getExtraData()
                    .keySet()
                    .forEach(key -> System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> key: " + key));
        };
    }

}
