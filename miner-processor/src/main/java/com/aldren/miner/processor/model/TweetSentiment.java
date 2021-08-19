package com.aldren.miner.processor.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TweetSentiment {

    private String tweet;
    private String user;
    private Integer retweetCount;
    private Integer favoriteCount;
    private int sentiment;

}
