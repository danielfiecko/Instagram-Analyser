package com.dfiecko.instagramAnalyser.exceptions;

public class EmptyProfileException extends RuntimeException {

    public EmptyProfileException(String url) {
        super(String.format("Empty profile for %s", url));
    }
}
