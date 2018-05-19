package com.dfiecko.instagramAnalyser.model.media.nested.graphql.shortcodemedia.edgemedia.edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Node {
    @JsonProperty("text")
    private String imageDescription;
}
