package com.excelra.glpg.exception;

/**
 * Tabular Service Exception
 *
 * @author Nagajyothi Rayabarapu
 */
public class GlpgServiceException extends RuntimeException {

    private static final long serialVersionUID = 25369827205008315L;

    public GlpgServiceException(String message, Throwable tx) {
        super(message, tx);
    }

    public GlpgServiceException(String message) {
        super(message);
    }

    public GlpgServiceException(Throwable tx) {
        super(tx);
    }

}