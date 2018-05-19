package com.dfiecko.instagramAnalyser.model.media.nested;

import com.dfiecko.instagramAnalyser.model.media.nested.graphql.ShortCodeMedia;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GraphQL {
    @JsonProperty("shortcode_media")
    private ShortCodeMedia shortCodeMedia;
}
