package com.dfiecko.instagramAnalyser.model.profile.nested.data;

import com.dfiecko.instagramAnalyser.model.profile.nested.data.page.GraphQL;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProfilePage {
    @JsonProperty("graphql")
    private GraphQL graphQL;
}
