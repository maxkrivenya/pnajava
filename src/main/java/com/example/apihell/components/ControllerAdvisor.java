package com.example.apihell.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@Validated
@ControllerAdvice
public class ControllerAdvisor {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ObjectNode> handleExceptionServer() throws JsonProcessingException {
        log.error("error 500");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObject = mapper.createObjectNode();
        jsonObject.put("value", "check your request body bro");
        return new ResponseEntity<>(jsonObject, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            HttpClientErrorException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            ConstraintViolationException.class,
            NoHandlerFoundException.class,
            ResourceNotFoundException.class,
            NoResourceFoundException.class,
    })
    public ResponseEntity<ObjectNode> handleIllegalArgumentException() {
        log.error("error 400");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObject = mapper.createObjectNode();
        jsonObject.put("value", "check your url bro");
        return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
    }
}