package com.aldren.miner.source.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "miner.source.twitter")
public class MinerSourceProperties {

    private Consumer consumer;

    private Access access;

    private String keyword;

}
