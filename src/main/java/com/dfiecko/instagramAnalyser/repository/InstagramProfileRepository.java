package com.dfiecko.instagramAnalyser.repository;

import com.dfiecko.instagramAnalyser.dto.InstagramProfile;

public interface InstagramProfileRepository {
    InstagramProfile findInstagramProfileByUrl(String url);
}
