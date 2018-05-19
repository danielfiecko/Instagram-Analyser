package com.dfiecko.instagramAnalyser.model.media.nested.graphql;

import com.dfiecko.instagramAnalyser.model.media.nested.graphql.shortcodemedia.EdgeMediaToCaption;
import com.dfiecko.instagramAnalyser.model.media.nested.graphql.shortcodemedia.EdgeMediaToComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ShortCodeMedia {
    @JsonProperty("edge_media_to_caption")
    private EdgeMediaToCaption edgeMediaToCaption;
    @JsonProperty("edge_media_to_comment")
    private EdgeMediaToComment edgeMediaToComment;
}
