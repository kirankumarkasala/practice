package com.excelra.glpg.exception;

public class GlpgSqlException extends RuntimeException{

    private static final long serialVersionUID = 1186449842830771229L;

    public GlpgSqlException(String message, Throwable tx) {
        super(message, tx);
    }

    public GlpgSqlException(String message) {
        super(message);
    }

    public GlpgSqlException(Throwable tx) {
        super(tx);
    }

}
