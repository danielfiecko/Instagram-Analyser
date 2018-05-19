package com.dfiecko.instagramAnalyser.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class AnalysedInstagramProfile {
    private String fullName;
    private String biography;
    private String totalLikes;
    private String profileImageUrl;
    private Map<String, ImageTagsStats> imageTagStats;
}
