package com.dfiecko.instagramAnalyser.repository;

import com.dfiecko.instagramAnalyser.clients.ElasticSearchProfileClient;
import com.dfiecko.instagramAnalyser.dto.InstagramProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ESInstagramProfileRepository implements InstagramProfileRepository {

    @Autowired
    private ElasticSearchProfileClient elasticSearchProfileClient;

    @Override
    public InstagramProfile findInstagramProfileByUrl(String url) {
        return elasticSearchProfileClient.getProfileByUrl(url);
    }
}
