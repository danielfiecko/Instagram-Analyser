package com.dfiecko.instagramAnalyser.repository;

import com.dfiecko.instagramAnalyser.clients.HttpProfileClient;
import com.dfiecko.instagramAnalyser.dto.InstagramProfile;
import com.dfiecko.instagramAnalyser.extractors.DataExtractor;
import com.dfiecko.instagramAnalyser.model.profile.WebInstagramProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WebInstagramProfileRepository implements InstagramProfileRepository {

    @Autowired
    private HttpProfileClient httpProfileClient;

    @Autowired
    private DataExtractor dataExtractor;

    @Override
    public InstagramProfile findInstagramProfileByUrl(String url) {
        String htmlResponse = httpProfileClient.getProfileByUrl(url);
        String jsonResponse = dataExtractor.extractJsonFromHtml(htmlResponse);
        WebInstagramProfile webInstagramProfile = (WebInstagramProfile) dataExtractor.deserialize(jsonResponse, new WebInstagramProfile());
        InstagramProfile instagramProfile = dataExtractor.getFullFilledInstagramProfile(webInstagramProfile, url);
        return instagramProfile;
    }
}
