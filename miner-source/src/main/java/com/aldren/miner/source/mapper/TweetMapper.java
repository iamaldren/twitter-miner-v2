package com.aldren.miner.source.mapper;

import com.aldren.miner.model.ParsedTweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.social.twitter.api.Tweet;

@Mapper(componentModel = "spring")
public interface TweetMapper {

    @Mappings({
            @Mapping(target="tweet", source="tweet.text"),
            @Mapping(target="user", source="tweet.fromUser"),
            @Mapping(target="retweetCount", source="tweet.retweetCount"),
            @Mapping(target="favoriteCount", source="tweet.favoriteCount")
    })
    ParsedTweet tweetToParsedTweet(Tweet tweet);

}
