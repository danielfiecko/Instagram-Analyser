package com.dfiecko.instagramAnalyser.model.media.nested.graphql.shortcodemedia;

import com.dfiecko.instagramAnalyser.model.media.nested.graphql.shortcodemedia.edgemedia.Edge;
import lombok.Getter;

import java.util.List;

@Getter
public class EdgeMediaToCaption {
    private List<Edge> edges;
}
