package com.dfiecko.instagramAnalyser.service;

import com.dfiecko.instagramAnalyser.algorithms.AlgorithmFactory;
import com.dfiecko.instagramAnalyser.algorithms.AlgorithmType;
import com.dfiecko.instagramAnalyser.algorithms.MiningStrategy;
import com.dfiecko.instagramAnalyser.dto.AnalysedInstagramProfile;
import com.dfiecko.instagramAnalyser.dto.InstagramProfile;
import com.dfiecko.instagramAnalyser.dto.UserAlgorithmSettings;
import com.dfiecko.instagramAnalyser.repository.ESInstagramProfileRepository;
import com.dfiecko.instagramAnalyser.repository.WebInstagramProfileRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j
@Service
public class AnalysesService {

    @Autowired
    private WebInstagramProfileRepository webInstagramProfileRepository;

    @Autowired
    private ESInstagramProfileRepository esInstagramProfileRepository;

    @Autowired
    private AlgorithmFactory algorithmFactory;

    public AnalysedInstagramProfile getAnalyses(UserAlgorithmSettings userAlgorithmSettings) {
        InstagramProfile instagramProfile = getUserData(userAlgorithmSettings.getUrl());
        AnalysedInstagramProfile analysedInstagramProfile = analyse(instagramProfile, userAlgorithmSettings);
        return analysedInstagramProfile;
    }

    private InstagramProfile getUserData(String url) {
        return Optional.ofNullable(esInstagramProfileRepository.findInstagramProfileByUrl(url)).isPresent() ? esInstagramProfileRepository.findInstagramProfileByUrl(url) : webInstagramProfileRepository.findInstagramProfileByUrl(url);
    }

    private AnalysedInstagramProfile analyse(InstagramProfile instagramProfile, UserAlgorithmSettings userAlgorithmSettings) {
        MiningStrategy miningStrategy = algorithmFactory.getMiningStrategy(AlgorithmType.toAlgorithmType(userAlgorithmSettings.getAlgorithmType()));
        AnalysedInstagramProfile analysedInstagramProfile = miningStrategy.analyse(instagramProfile);
        analysedInstagramProfile = fillUpDetails(instagramProfile, analysedInstagramProfile);
        return analysedInstagramProfile;
    }

    private AnalysedInstagramProfile fillUpDetails(InstagramProfile instagramProfile, AnalysedInstagramProfile analysedInstagramProfile) {
        analysedInstagramProfile.setFullName(instagramProfile.getFullName());
        analysedInstagramProfile.setBiography(instagramProfile.getBiography());
        analysedInstagramProfile.setProfileImageUrl(instagramProfile.getProfileImageUrl());
        return analysedInstagramProfile;
    }
}
