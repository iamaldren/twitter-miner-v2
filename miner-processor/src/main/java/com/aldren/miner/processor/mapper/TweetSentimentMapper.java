package com.aldren.miner.processor.mapper;

import com.aldren.miner.model.ParsedTweet;
import com.aldren.miner.model.TweetSentiment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TweetSentimentMapper {

    TweetSentiment parsedTweetToSentiment(ParsedTweet parsedTweet);

}
