package com.dfiecko.instagramAnalyser.model.profile.nested;


import com.dfiecko.instagramAnalyser.model.profile.nested.data.ProfilePage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class EntryData {
    @JsonProperty("ProfilePage")
    private List<ProfilePage> profilePageList;
}
