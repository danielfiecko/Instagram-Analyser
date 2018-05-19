package com.dfiecko.instagramAnalyser.api;


import com.dfiecko.instagramAnalyser.clients.HttpProfileClient;
import com.dfiecko.instagramAnalyser.dto.InstagramProfile;
import com.dfiecko.instagramAnalyser.extractors.DataExtractor;
import com.dfiecko.instagramAnalyser.model.media.WebInstagramMedia;
import com.dfiecko.instagramAnalyser.model.profile.WebInstagramProfile;
import com.dfiecko.instagramAnalyser.repository.ESInstagramProfileRepository;
import com.dfiecko.instagramAnalyser.repository.WebInstagramProfileRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j
@PropertySource("classpath:application.properties")
@RestController
@RequestMapping(value = "debug")
public class DebugController {

    @Autowired
    private HttpProfileClient httpProfileClient;

    @Autowired
    private WebInstagramProfileRepository webInstagramProfileRepository;

    @Autowired
    private ESInstagramProfileRepository esInstagramProfileRepository;

    @Value("${instagram-media-url.prefix}")
    private String instagramMediaUrlPrefix;

    @Value("${instagram-media-url.suffix}")
    private String instagramMediaUrlSuffix;

    @Autowired
    private DataExtractor dataExtractor;

    @GetMapping(value = "/media/raw")
    public WebInstagramMedia findRawMediaDataByWeb(@RequestParam final String shortcode) {
        return (WebInstagramMedia) dataExtractor.deserialize(instagramMediaUrlPrefix + shortcode + instagramMediaUrlSuffix, new WebInstagramMedia());
    }

    @GetMapping(value = "/profile/raw")
    public WebInstagramProfile findRawInstagramProfileByWeb(@RequestParam final String url) {
        String htmlResponse = httpProfileClient.getProfileByUrl(url);
        String jsonResponse = dataExtractor.extractJsonFromHtml(htmlResponse);
        WebInstagramProfile webInstagramProfile = (WebInstagramProfile) dataExtractor.deserialize(jsonResponse, new WebInstagramProfile());
        return webInstagramProfile;
    }

    @GetMapping(value = "/profile/web")
    public InstagramProfile findInstagramProfileByWeb(@RequestParam final String url) {
        return webInstagramProfileRepository.findInstagramProfileByUrl(url);
    }

    @GetMapping(value = "/profile/es")
    public InstagramProfile findInstagramProfileByES(@RequestParam final String url) {
        return esInstagramProfileRepository.findInstagramProfileByUrl(url);
    }

    @GetMapping(value = "/healthcheck")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void checkApplicationHealth() {
        log.info("application health check");
    }
}
