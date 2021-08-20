package com.aldren.miner.processor.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ToString
@RedisHash("User")
public class User implements Serializable {

    @Id
    private String id;
    private int thresholdCount;

    @TimeToLive(unit = TimeUnit.DAYS)
    private long timeout;

}
