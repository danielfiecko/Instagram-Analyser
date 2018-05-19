package com.dfiecko.instagramAnalyser.algorithms.naivebayes;

import com.dfiecko.instagramAnalyser.algorithms.MiningStrategy;
import com.dfiecko.instagramAnalyser.algorithms.naivebayes.machinelearning.classifier.Classification;
import com.dfiecko.instagramAnalyser.algorithms.naivebayes.machinelearning.classifier.Classifier;
import com.dfiecko.instagramAnalyser.algorithms.naivebayes.machinelearning.classifier.bayes.BayesClassifier;
import com.dfiecko.instagramAnalyser.dto.AnalysedInstagramProfile;
import com.dfiecko.instagramAnalyser.dto.ImageTagsStats;
import com.dfiecko.instagramAnalyser.dto.InstagramProfile;
import com.dfiecko.instagramAnalyser.algorithms.AlgorithmType;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Component
public class NaiveBayes implements MiningStrategy {

    private final Classifier<String, String> bayes =
            new BayesClassifier<String, String>();

    @Override
    public AlgorithmType supports() {
        return AlgorithmType.NaiveBayes;
    }

    @Override
    public AnalysedInstagramProfile analyse(InstagramProfile instagramProfile) {

        learnClassifierFromFile("negative", "negative-words.txt");
        learnClassifierFromFile("positive", "positive-words.txt");

        AnalysedInstagramProfile analysedInstagramProfile = new AnalysedInstagramProfile();

        Map<String, ImageTagsStats> imageTagsStatsMap = classify(instagramProfile);

        analysedInstagramProfile.setImageTagStats(imageTagsStatsMap);

        bayes.setMemoryCapacity(2000); // remember the last <VALUE> learned classifications

        return analysedInstagramProfile;
    }

    private Map<String, ImageTagsStats> classify(InstagramProfile instagramProfile) {
        Map<String, ImageTagsStats> imageTagsStatsMap = new HashMap<>();

        instagramProfile.getExtrudedProfileData().getTagsOfMedia().entrySet().stream().forEach(selectedMediaTags -> {
            List<String> tags = Arrays.asList(selectedMediaTags.getValue().replace("#", "").split("\\s"));
            Classification<String, String> tagClassification = bayes.classify(tags);
            String imageUrl = instagramProfile.getExtrudedProfileData().getShortCodesWithImages().get(selectedMediaTags.getKey());
            ImageTagsStats analysedTagsForMedia = new ImageTagsStats(imageUrl,
                    new ArrayList<>(tagClassification.getFeatureset()), tagClassification.getProbability() * 100, tagClassification.getCategory());
            imageTagsStatsMap.put(selectedMediaTags.getKey(), analysedTagsForMedia);
        });

        return imageTagsStatsMap;
    }

    private void learnClassifierFromFile(String textCategory, String fileName) {

        InputStream resourceAsStream = NaiveBayes.class.getResourceAsStream("/" + fileName);
        List<String> wordList = new BufferedReader(new InputStreamReader(resourceAsStream)).lines().collect(toList());

        wordList.stream().forEach(line -> {
            final String[] text = line.split("\\s");
            bayes.learn(textCategory, Arrays.asList(text));
        });
    }
}
