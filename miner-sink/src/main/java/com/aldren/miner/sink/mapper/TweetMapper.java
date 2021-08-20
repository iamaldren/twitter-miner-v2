package com.aldren.miner.sink.mapper;

import com.aldren.miner.model.TweetSentiment;
import com.aldren.miner.sink.data.Tweet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TweetMapper {

    Tweet modelToEntity(TweetSentiment tweetSentiment);

}
