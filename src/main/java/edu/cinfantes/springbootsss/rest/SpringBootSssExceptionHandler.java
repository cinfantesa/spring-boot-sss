package edu.cinfantes.springbootsss.rest;

import edu.cinfantes.springbootsss.domain.exception.ZoneNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SpringBootSssExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ZoneNotFoundException.class})
  protected ResponseEntity handleNotFoundException(Exception e, WebRequest request) {
    return handleExceptionInternal(e, "error", new HttpHeaders(), HttpStatus.FORBIDDEN, request);
  }
}
