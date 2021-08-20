package com.aldren.miner.sink.repository;

import com.aldren.miner.sink.data.Tweet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TweetRepository extends ElasticsearchRepository<Tweet, String> {
}
