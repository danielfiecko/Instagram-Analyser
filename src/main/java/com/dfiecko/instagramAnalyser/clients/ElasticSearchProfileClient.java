package com.dfiecko.instagramAnalyser.clients;

import com.dfiecko.instagramAnalyser.dto.InstagramProfile;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchProfileClient implements ProfileClient<String, InstagramProfile> {

    @Override
    public InstagramProfile getProfileByUrl(String url) {
        //todo: implement client
        return null;
    }
}
