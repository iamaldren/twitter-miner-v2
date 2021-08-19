package com.aldren.miner.processor.service.impl;

import com.aldren.miner.processor.service.SentimentService;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SentimentServiceImpl implements SentimentService {

    private StanfordCoreNLP pipeline;

    public SentimentServiceImpl(StanfordCoreNLP pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public int analyzeSentiment(String tweet) {
        AtomicInteger sentiment = new AtomicInteger(0);

        if(tweet != null && !tweet.isEmpty()) {
            AtomicInteger longest = new AtomicInteger(0);
            pipeline.process(tweet).get(CoreAnnotations.SentencesAnnotation.class)
                    .stream()
                    .forEach(sentence -> {
                        Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                        int predictionSentiment = RNNCoreAnnotations.getPredictedClass(tree);
                        String partText = sentence.toString();
                        if (partText.length() > longest.get()) {
                            sentiment.set(predictionSentiment);
                            longest.set(partText.length());
                        }
                    });
        }

        int mainSentiment = sentiment.get();
        if (mainSentiment == 2 || mainSentiment > 4 || mainSentiment < 0) {
            return -2;
        }

        return mainSentiment;
    }
}
