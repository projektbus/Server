package ProjektBus.Server.utils;

import ProjektBus.Server.Application;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler{

    private final static Logger logger = Logger.getLogger(Application.class.getName());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApplicationError handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException ex, WebRequest request){
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        logger.error(ex.getMessage());
        return new ApplicationError(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ApplicationError handleAllExceptions(Exception ex, WebRequest request){
        logger.error(ex.getMessage());
        return new ApplicationError(HttpStatus.INTERNAL_SERVER_ERROR, "");
    }
}