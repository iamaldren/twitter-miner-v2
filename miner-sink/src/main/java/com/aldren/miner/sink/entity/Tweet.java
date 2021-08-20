package com.aldren.miner.sink.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "twitter")
public class Tweet {

    @Id
    private String id;

    private String tweet;

    private String user;

    private Integer retweetCount;

    private Integer favoriteCount;

    private Integer polarity;

    private String sentiment;

}
