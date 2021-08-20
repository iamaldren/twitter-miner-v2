package com.aldren.miner.sink.service.impl;

import com.aldren.miner.model.TweetSentiment;
import com.aldren.miner.sink.mapper.TweetMapper;
import com.aldren.miner.sink.repository.TweetRepository;
import com.aldren.miner.sink.service.ElasticSearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private TweetRepository tweetRepository;
    private TweetMapper tweetMapper;

    @Override
    public void saveTweetSentimentToES(TweetSentiment tweetSentiment) {
        log.info("Saving tweet to ES.");
        tweetRepository.save(tweetMapper.modelToEntity(tweetSentiment));
    }

}
