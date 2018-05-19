package com.dfiecko.instagramAnalyser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
public class ImageTagsStats {
    private String imageUrl;
    private List<String> tags;
    private Float probability;
    private String tagsContext; //todo enumeration
}