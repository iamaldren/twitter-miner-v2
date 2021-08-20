package com.aldren.miner.processor.util;

import java.util.HashMap;
import java.util.Map;

public enum Sentiment {

    VERY_POSITIVE("Very Positive", 4),
    POSITIVE("Positive", 3),
    NEUTRAL("Neutral", 2),
    NEGATIVE("Negative", 1),
    VERY_NEGATIVE("Very Negative", 0);

    private static final Map<String, Sentiment> BY_TEXT = new HashMap<>();
    private static final Map<String, Sentiment> BY_POINT = new HashMap<>();

    public final String sentimentText;
    public final int sentimentPoint;

    Sentiment(String sentimentText, int sentimentPoint) {
        this.sentimentText = sentimentText;
        this.sentimentPoint = sentimentPoint;
    }

    public static Sentiment getByPoint(int point) {
        return BY_POINT.get(point);
    }

    public static Sentiment getByText(String text) {
        return BY_TEXT.get(text);
    }

}
