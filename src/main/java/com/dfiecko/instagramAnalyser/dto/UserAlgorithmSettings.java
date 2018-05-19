package com.dfiecko.instagramAnalyser.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAlgorithmSettings {
    private String url;
    private String algorithmType;
    private Double confidence;
    private Double threshold;
}
