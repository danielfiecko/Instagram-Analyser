package com.dfiecko.instagramAnalyser.model.profile.nested.data.page.graph.user.timeline.edge;

import com.dfiecko.instagramAnalyser.model.profile.nested.data.page.graph.user.timeline.edge.node.EdgeLikedBy;
import com.dfiecko.instagramAnalyser.model.profile.nested.data.page.graph.user.timeline.edge.node.EdgeMediaToComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Node {
    @JsonProperty("shortcode")
    private String shortCode;
    @JsonProperty("display_url")
    private String displayUrl;
    @JsonProperty("edge_liked_by")
    private EdgeLikedBy edgeLikedBy;
    @JsonProperty("edge_media_to_comment")
    private EdgeMediaToComment edgeMediaToComment;
}
