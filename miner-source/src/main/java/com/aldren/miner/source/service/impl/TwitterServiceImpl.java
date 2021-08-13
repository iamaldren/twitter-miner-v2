package com.aldren.miner.source.service.impl;

import com.aldren.miner.source.properties.MinerSourceProperties;
import com.aldren.miner.source.service.TwitterService;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service
public class TwitterServiceImpl implements TwitterService {

    private MinerSourceProperties sourceProps;

    private Twitter twitter;

    public TwitterServiceImpl(MinerSourceProperties sourceProps) {
        this.sourceProps = sourceProps;
        initConnection();
    }

    private void initConnection() {
        twitter = new TwitterTemplate(sourceProps.getConsumer().getKey(),
                sourceProps.getConsumer().getSecret(),
                sourceProps.getAccess().getToken(),
                sourceProps.getAccess().getSecret());
    }

    @Override
    public void mineTweets(String keyword) {
        SearchResults results = twitter.searchOperations().search(String.format("#%s", keyword));
        results.getTweets();
    }

}
