package com.aldren.miner.processor.config.consumer;

import com.aldren.miner.processor.model.ParsedTweet;
import com.aldren.miner.processor.service.ProcessorService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class KafkaConsumer {

    private ProcessorService processorService;

    @Bean
    public Consumer<List<ParsedTweet>> minedTweets() {
        return tweets -> {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> tweets.size()::" + tweets.size());
            tweets.stream().forEach(tweet -> {
                if(tweet == null) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> tweet is null!!!!!!!!!!!!");
                } else {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> IAMHERE!!!!!!!!!!!!");
                    processorService.processTweets(tweet);
                }
            });
        };
    }

}
