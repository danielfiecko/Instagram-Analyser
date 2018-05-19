package com.dfiecko.instagramAnalyser.algorithms;

public enum AlgorithmType {
    NaiveBayes,
    DecisionTree,
    Apriori;

    public static AlgorithmType toAlgorithmType(String algorithmType) {
        return valueOf(algorithmType.toString());
    }
}
