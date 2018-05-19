package com.dfiecko.instagramAnalyser.algorithms.decisiontree;


import com.dfiecko.instagramAnalyser.algorithms.MiningStrategy;
import com.dfiecko.instagramAnalyser.dto.AnalysedInstagramProfile;
import com.dfiecko.instagramAnalyser.dto.InstagramProfile;
import com.dfiecko.instagramAnalyser.algorithms.AlgorithmType;
import org.springframework.stereotype.Repository;

@Repository
public class DecisionTree implements MiningStrategy {

    @Override
    public AlgorithmType supports() {
        return AlgorithmType.DecisionTree;
    }

    @Override
    public AnalysedInstagramProfile analyse(InstagramProfile instagramProfile) {
        //todo: implement algorithm
        return null;
    }
}
