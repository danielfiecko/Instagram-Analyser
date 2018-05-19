package com.dfiecko.instagramAnalyser.model.media;

import com.dfiecko.instagramAnalyser.model.media.nested.GraphQL;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WebInstagramMedia {

    @JsonProperty("graphql")
    private GraphQL graphQL;
}
