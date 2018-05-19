package com.dfiecko.instagramAnalyser.algorithms;


import com.dfiecko.instagramAnalyser.dto.AnalysedInstagramProfile;
import com.dfiecko.instagramAnalyser.dto.InstagramProfile;

public interface MiningStrategy {

    AlgorithmType supports();

    AnalysedInstagramProfile analyse(InstagramProfile instagramProfile);
}
