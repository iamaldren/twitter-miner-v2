package com.aldren.miner.source.config.producer;

import com.aldren.miner.model.ParsedTweet;
import com.aldren.miner.source.mapper.TweetMapper;
import com.aldren.miner.source.properties.MinerSourceProperties;
import com.aldren.miner.source.service.TwitterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.twitter.api.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaProducer {

    private TwitterService twitterService;
    private MinerSourceProperties minerSourceProperties;
    private TweetMapper tweetMapper;

    @Bean
    public Supplier<List<ParsedTweet>> tweets() {
        AtomicBoolean isFirstRun = new AtomicBoolean();
        isFirstRun.set(true);

        return () -> {
            if(!isFirstRun.get()) {
                try {
                    Thread.sleep(30000L);
                } catch (InterruptedException e) {
                    log.error(e.getLocalizedMessage(), e);
                }
            }

            List<Tweet> tweets = new ArrayList<>();
            try {
                tweets = twitterService.mineTweets(minerSourceProperties.getKeyword());
            } catch (NotAuthorizedException e) {
                log.warn(e.getLocalizedMessage(), e);
            }

            if(isFirstRun.get()) {
                isFirstRun.set(false);
            }

            return tweets.stream()
                    .filter(tweet -> tweet.getText() != null && !tweet.getText().isEmpty())
                    .map(tweet -> {
                        ParsedTweet pt = tweetMapper.tweetToParsedTweet(tweet);
                        pt.setKeyword(minerSourceProperties.getKeyword());
                        return pt;
                    })
                    .collect(Collectors.toList());
        };
    }

}
