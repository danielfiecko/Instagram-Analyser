package com.dfiecko.instagramAnalyser.exceptions;

public class HtmlResponseException extends RuntimeException {

    public HtmlResponseException(String url) {
        super(String.format("Unavailable to get http response for this profile %s", url));
    }

}
