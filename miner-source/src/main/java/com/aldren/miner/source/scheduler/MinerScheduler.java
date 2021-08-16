package com.aldren.miner.source.scheduler;

import com.aldren.miner.source.properties.MinerSourceProperties;
import com.aldren.miner.source.service.TwitterService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
@EnableAsync
@AllArgsConstructor
public class MinerScheduler {

    private Supplier<List<Tweet>> tweets;

    @Async
    @Scheduled(fixedRate = 60000)
    public void twitterMinerScheduler() {
        tweets.get();
    }

}
