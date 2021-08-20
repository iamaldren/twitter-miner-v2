package com.aldren.miner.sink.repository;

import com.aldren.miner.sink.entity.Tweet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TweetRepository extends ElasticsearchRepository<Tweet, String> {
}
