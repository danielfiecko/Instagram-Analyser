package com.dfiecko.instagramAnalyser.clients;

import com.dfiecko.instagramAnalyser.exceptions.HtmlResponseException;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.Scanner;

@Log4j
@Component
public class HttpProfileClient implements ProfileClient<String, String> {

    @Override
    public String getProfileByUrl(String url) {
        Optional<String> htmlResponse = Optional.empty();
        try {
            URLConnection connection = new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            htmlResponse = Optional.ofNullable(scanner.next());
        } catch (Exception e) {
            log.error("Instagram Analyser is unable to get html response from http client");
        }
        return htmlResponse.orElseThrow(() -> new HtmlResponseException(url));
    }
}
