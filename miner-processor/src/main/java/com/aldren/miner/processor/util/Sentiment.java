package com.aldren.miner.processor.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Sentiment {

    VERY_POSITIVE("Very Positive", 4),
    POSITIVE("Positive", 3),
    NEUTRAL("Neutral", 2),
    NEGATIVE("Negative", 1),
    VERY_NEGATIVE("Very Negative", 0);

    private static final Map<String, Sentiment> BY_TEXT = new HashMap<>();
    private static final Map<Integer, Sentiment> BY_POINT = new HashMap<>();

    static {
        Arrays.stream(values())
                .forEach(value -> {
                    BY_TEXT.put(value.sentimentText, value);
                    BY_POINT.put(value.sentimentPoint, value);
                });
    }

    public final String sentimentText;
    public final Integer sentimentPoint;

    Sentiment(String sentimentText, Integer sentimentPoint) {
        this.sentimentText = sentimentText;
        this.sentimentPoint = sentimentPoint;
    }

    public static Sentiment getByPoint(int point) {
        return BY_POINT.get(point);
    }

}
