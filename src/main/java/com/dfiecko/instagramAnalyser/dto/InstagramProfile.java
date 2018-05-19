package com.dfiecko.instagramAnalyser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
@AllArgsConstructor
public class InstagramProfile {
    private String id;
    private String profileUrl;
    private String username;
    private String fullName;
    private String biography;
    private String profileImageUrl;
    private Integer following;
    private Integer followedBy;
    private Integer amountOfPosts;
    private Integer amountOfAvailableMediaInSet;
    private ExtrudedProfileData extrudedProfileData;

    @Setter
    public static class InstagramProfileBuilder {
        private String id;
        private String profileUrl;
        private String username;
        private String fullName;
        private String biography;
        private String profileImageUrl;
        private Integer following;
        private Integer followedBy;
        private Integer amountOfPosts;
        private Integer amountOfAvailableMediaInSet;
        private ExtrudedProfileData extrudedProfileData;


        public InstagramProfileBuilder with(
                Consumer<InstagramProfileBuilder> builderFunction) {
            builderFunction.accept(this);
            return this;
        }

        public InstagramProfile createProfile() {
            return new InstagramProfile(id, profileUrl, username, fullName, biography, profileImageUrl, following,
                    followedBy, amountOfPosts, amountOfAvailableMediaInSet, extrudedProfileData);
        }
    }
}
