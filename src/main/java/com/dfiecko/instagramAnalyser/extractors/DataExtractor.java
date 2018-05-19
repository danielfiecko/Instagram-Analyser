package com.dfiecko.instagramAnalyser.extractors;

import com.dfiecko.instagramAnalyser.dto.InstagramProfile;
import com.dfiecko.instagramAnalyser.exceptions.EmptyProfileException;
import com.dfiecko.instagramAnalyser.exceptions.InternalException;
import com.dfiecko.instagramAnalyser.dto.ExtrudedProfileData;
import com.dfiecko.instagramAnalyser.model.media.WebInstagramMedia;
import com.dfiecko.instagramAnalyser.model.profile.WebInstagramProfile;
import com.dfiecko.instagramAnalyser.model.profile.nested.data.page.graph.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.javatuples.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j
@Component
@PropertySource("classpath:application.properties")
public class DataExtractor {

    @Value("${instagram-media-url.prefix}")
    private String instagramMediaUrlPrefix;
    @Value("${instagram-media-url.suffix}")
    private String instagramMediaUrlSuffix;
    @Value("${offcial-instagram-url}")
    private String officialInstagramUrl;
    @Value("${cssQuery}")
    private String cssQuery;
    @Value("${scriptNumber}")
    private int scriptNumber;

    public String extractJsonFromHtml(String htmlFormat) {
        Document doc = Jsoup.parse(htmlFormat);
        Elements elements = doc.select(cssQuery);
        String jsonFormat = elements.get(scriptNumber).toString();
        jsonFormat = jsonFormat.substring(jsonFormat.indexOf("{") - 1, jsonFormat.lastIndexOf("}") + 1);
        return jsonFormat;
    }

    public Object deserialize(String input, Object Pojo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Optional<Object> output = Optional.empty();
        try {
            output = Optional.ofNullable(new UrlValidator().isValid(input) ? objectMapper.readValue(new URL(input), Pojo.getClass()) : objectMapper.readValue(input, Pojo.getClass()));
        } catch (IOException e) {
            log.error("Instagram Analyser is unable to deserialize input stream to objects");
        }

        return output.orElseThrow(() -> new InternalException(input));
    }

    public InstagramProfile getFullFilledInstagramProfile(WebInstagramProfile webInstagramProfile, String url) {

        User webUserData = webInstagramProfile.getEntryData().getProfilePageList().stream()
                .filter(profile -> Optional.ofNullable(profile).isPresent())
                .map(profile -> profile.getGraphQL().getUser())
                .findFirst().orElseThrow(() -> new EmptyProfileException(url));

        return new InstagramProfile.InstagramProfileBuilder()
                .with(profile -> {
                    profile.setId(webUserData.getId());
                    profile.setProfileUrl(officialInstagramUrl + webUserData.getUsername());
                    profile.setUsername(webUserData.getUsername());
                    profile.setFullName(webUserData.getFullName());
                    profile.setBiography(webUserData.getBiography());
                    profile.setProfileImageUrl(webUserData.getProfileImageUrl());
                    profile.setFollowing(webUserData.getEdgeFollow().getCount());
                    profile.setFollowedBy(webUserData.getEdgeFollowedBy().getCount());
                    profile.setAmountOfPosts(webUserData.getMediaTimeline().getCount());
                    profile.setAmountOfAvailableMediaInSet(webUserData.getMediaTimeline().getEdgeList().size());
                    profile.setExtrudedProfileData(getExtrudedData(webUserData));
                }).createProfile();
    }


    private ExtrudedProfileData getExtrudedData(User webUserData) {

        Map<String, String> shortCodesWithImages = webUserData.getMediaTimeline().getEdgeList().stream()
                .map(media -> Pair.with(media.getNode().getShortCode(), media.getNode().getDisplayUrl()))
                .collect(Collectors.toMap(Pair::getValue0, Pair::getValue1));

        Map<String, Set<String>> commentsOfMedia = shortCodesWithImages.entrySet().stream()
                .map(media -> {
                    WebInstagramMedia webInstagramMedia = (WebInstagramMedia) deserialize(instagramMediaUrlPrefix
                            + media.getKey() + instagramMediaUrlSuffix, new WebInstagramMedia());
                    Set<String> imageComments = webInstagramMedia.getGraphQL().getShortCodeMedia().getEdgeMediaToComment().getEdges().stream()
                            .filter(edge -> Optional.ofNullable(edge).isPresent())
                            .map(edge -> edge.getNode().getImageDescription())
                            .collect(Collectors.toSet());
                    return Pair.with(media.getKey(), imageComments);
                }).collect(Collectors.toMap(Pair::getValue0, Pair::getValue1));


        Map<String, String> tagsOfMedia = shortCodesWithImages.entrySet().stream()
                .map(media -> {
                    WebInstagramMedia webInstagramMedia = (WebInstagramMedia) deserialize(instagramMediaUrlPrefix
                            + media.getKey() + instagramMediaUrlSuffix, new WebInstagramMedia());
                    String imageTags = webInstagramMedia.getGraphQL().getShortCodeMedia().getEdgeMediaToCaption().getEdges().stream()
                            .filter(edge -> Optional.ofNullable(edge).isPresent())
                            .map(edge -> edge.getNode().getImageDescription())
                            .findFirst().orElse("");
                    return Pair.with(media.getKey(), imageTags);
                }).collect(Collectors.toMap(Pair::getValue0, Pair::getValue1));

        Integer totalAmountOfComments = webUserData.getMediaTimeline().getEdgeList().stream()
                .map(media -> media.getNode().getEdgeMediaToComment().getCount())
                .mapToInt(specifiedMediaComments -> specifiedMediaComments.intValue())
                .sum();

        Integer totalAmountOfTags = tagsOfMedia.entrySet().stream()
                .map(media -> Arrays.asList(media.getValue().split("\\s")))
                .mapToInt(mediaTagList -> mediaTagList.size())
                .sum();

        Integer totalAmountOfLikes = webUserData.getMediaTimeline().getEdgeList().stream()
                .map(media -> media.getNode().getEdgeLikedBy().getCount())
                .mapToInt(specifiedMediaLikes -> specifiedMediaLikes.intValue())
                .sum();

        return new ExtrudedProfileData.ExtrudedProfileDataBuilder()
                .with(profile -> {
                    profile.setShortCodesWithImages(shortCodesWithImages);
                    profile.setCommentsOfMedia(commentsOfMedia);
                    profile.setTagsOfMedia(tagsOfMedia);
                    profile.setTotalComments(totalAmountOfComments);
                    profile.setTotalTags(totalAmountOfTags);
                    profile.setTotalLikes(totalAmountOfLikes);
                }).createProfile();
    }
}
