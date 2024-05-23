package com.vtx.reportgenerator;

public class ReportException extends RuntimeException {
    private final int statusCode;

    public ReportException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ReportException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public ReportException(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public ReportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int statusCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
