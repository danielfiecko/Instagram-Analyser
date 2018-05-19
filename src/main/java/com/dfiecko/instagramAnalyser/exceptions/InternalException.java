package com.dfiecko.instagramAnalyser.exceptions;

public class InternalException extends RuntimeException {

    public InternalException(String url) {
        super(String.format("Internal exception for p profile for %s", url));
    }
}
