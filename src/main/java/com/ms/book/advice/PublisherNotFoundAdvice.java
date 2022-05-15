package com.ms.book.advice;

import com.ms.book.exception.PublisherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PublisherNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PublisherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String publisherNotFoundHandler(PublisherNotFoundException ex) { return ex.getMessage();}
}
