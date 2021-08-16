package com.aldren.miner.source.scheduler;

import com.aldren.miner.source.properties.MinerSourceProperties;
import com.aldren.miner.source.service.TwitterService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@AllArgsConstructor
public class MinerScheduler {

    private TwitterService twitterService;
    private MinerSourceProperties minerSourceProperties;

    @Async
    @Scheduled(fixedRate = 60000)
    public void twitterMinerScheduler() {
        String keyword = minerSourceProperties.getKeyword();
        twitterService.mineTweets(keyword);
    }

}
