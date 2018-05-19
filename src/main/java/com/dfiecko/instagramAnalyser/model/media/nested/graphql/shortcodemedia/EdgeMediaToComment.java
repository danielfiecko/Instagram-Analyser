package com.dfiecko.instagramAnalyser.model.media.nested.graphql.shortcodemedia;

import com.dfiecko.instagramAnalyser.model.media.nested.graphql.shortcodemedia.edgemedia.Edge;
import lombok.Getter;

import java.util.List;

@Getter
public class EdgeMediaToComment {
    private List<Edge> edges;
}
