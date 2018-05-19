package com.dfiecko.instagramAnalyser.service;

import com.dfiecko.instagramAnalyser.extractors.DataExtractor;
import com.dfiecko.instagramAnalyser.dto.UserAlgorithmSettings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnalysesServiceTest {

    @Mock
    private DataExtractor dataExtractor;

    @InjectMocks
    private AnalysesService analysesService;

    @Test
    public void getAnalyses() throws Exception {
//        UserAlgorithmSettings userAlgorithmSettings = new UserAlgorithmSettings();
//        analysesService.getAnalyses(userAlgorithmSettings);
    }
}