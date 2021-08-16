package com.aldren.miner.source.config.producer;

import com.aldren.miner.source.properties.MinerSourceProperties;
import com.aldren.miner.source.service.TwitterService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Tweet;

import java.util.List;
import java.util.function.Supplier;

@Configuration
@AllArgsConstructor
public class KafkaProducer {

    private TwitterService twitterService;
    private MinerSourceProperties minerSourceProperties;

    @Bean
    public Supplier<List<Tweet>> tweets() {
        return () -> twitterService.mineTweets(minerSourceProperties.getKeyword());
    }

}
