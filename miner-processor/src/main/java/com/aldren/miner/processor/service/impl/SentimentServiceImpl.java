package com.aldren.miner.processor.service.impl;

import com.aldren.miner.processor.service.SentimentService;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@AllArgsConstructor
public class SentimentServiceImpl implements SentimentService {

    private StanfordCoreNLP pipeline;

    @Override
    public int analyzeSentiment(String text) {
        log.info("Starting analyzing tweet sentiment");

        AtomicInteger sentiment = new AtomicInteger(0);

        if(text != null && !text.isEmpty()) {
            AtomicInteger longest = new AtomicInteger(0);
            pipeline.process(text).get(CoreAnnotations.SentencesAnnotation.class)
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
        if (mainSentiment < 0) {
            log.info("Sentiment is less than 0, skipping the tweet.");
            return -1;
        }

        return mainSentiment;
    }
}
