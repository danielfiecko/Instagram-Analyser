package com.dfiecko.instagramAnalyser.model.profile.nested.data.page.graph.user;

import com.dfiecko.instagramAnalyser.model.profile.nested.data.page.graph.user.timeline.Edge;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MediaTimeline {
    @JsonProperty("edges")
    private List<Edge> edgeList;
    private Integer count;
}
