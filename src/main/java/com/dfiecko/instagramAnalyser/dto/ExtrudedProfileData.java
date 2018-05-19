package com.dfiecko.instagramAnalyser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@Getter
@ToString
@AllArgsConstructor
public class ExtrudedProfileData {
    private Map<String, String> shortCodesWithImages;
    private Map<String, Set<String>> commentsOfMedia;
    private Map<String, String> tagsOfMedia;
    private Integer totalComments;
    private Integer totalTags;
    private Integer totalLikes;

    @Setter
    public static class ExtrudedProfileDataBuilder {
        private Map<String, String> shortCodesWithImages;
        private Map<String, Set<String>> commentsOfMedia;
        private Map<String, String> tagsOfMedia;
        private Integer totalComments;
        private Integer totalTags;
        private Integer totalLikes;

        public ExtrudedProfileDataBuilder with(
                Consumer<ExtrudedProfileDataBuilder> builderFunction) {
            builderFunction.accept(this);
            return this;
        }

        public ExtrudedProfileData createProfile() {
            return new ExtrudedProfileData(shortCodesWithImages, commentsOfMedia, tagsOfMedia, totalComments, totalTags, totalLikes);
        }
    }
}
