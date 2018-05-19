package com.dfiecko.instagramAnalyser.model.profile;

import com.dfiecko.instagramAnalyser.model.profile.nested.EntryData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class WebInstagramProfile {
    @JsonProperty("entry_data")
    private EntryData entryData;
}
