package com.excelra.glpg.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.excelra.glpg.model.ApiError;





/**
 * Controller Advice
 */
@ControllerAdvice(basePackages = "com.excelra.glpg.controller")
public class GlpgControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlpgControllerAdvice.class);

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ ServiceException.class, ConstraintViolationException.class,
            DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleExceptionInternal(Exception ex) {

        LOGGER.error("Exception occurred in glpg service ", ex);

        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(),
                "Exception occurred in target synonyms service ",getStackTraceString(ex));

        return new ResponseEntity<Object>(apiError, apiError.getStatus());
    }

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> genericExceptionHandle(Exception ex) {

        LOGGER.error("Exception occurred in glpg service ", ex);

        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
                "Exception occurred in target synonyms service",getStackTraceString(ex));

        return new ResponseEntity<Object>(apiError, apiError.getStatus());
    }

    /**
     * <p>
     * To convert exception object to String.
     * <p>
     * @param ex
     * @return
     */
    public String getStackTraceString(Exception ex){

        StringWriter stringWriter=new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));

        String error = stringWriter.toString().replaceAll("\r\n\tat ", StringUtils.EMPTY);

        return error;
    }
}
