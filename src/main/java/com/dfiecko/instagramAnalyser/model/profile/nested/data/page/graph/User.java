package com.dfiecko.instagramAnalyser.model.profile.nested.data.page.graph;

import com.dfiecko.instagramAnalyser.model.profile.nested.data.page.graph.user.EdgeFollow;
import com.dfiecko.instagramAnalyser.model.profile.nested.data.page.graph.user.EdgeFollowedBy;
import com.dfiecko.instagramAnalyser.model.profile.nested.data.page.graph.user.MediaTimeline;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class User {
    private String id;
    private String username;
    private String biography;
    @JsonProperty("profile_pic_url_hd")
    private String profileImageUrl;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("edge_followed_by")
    private EdgeFollowedBy edgeFollowedBy;
    @JsonProperty("edge_follow")
    private EdgeFollow edgeFollow;
    @JsonProperty("edge_owner_to_timeline_media")
    private MediaTimeline mediaTimeline;
}
