package com.dfiecko.instagramAnalyser.api;

import com.dfiecko.instagramAnalyser.exceptions.EmptyProfileException;
import com.dfiecko.instagramAnalyser.exceptions.HtmlResponseException;
import com.dfiecko.instagramAnalyser.exceptions.InternalException;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Log4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EmptyProfileException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public String handleEmptyProfileException(EmptyProfileException e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ExceptionHandler(InternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleInternalException(InternalException e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ExceptionHandler(HtmlResponseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleIOException(IOException e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }
}
