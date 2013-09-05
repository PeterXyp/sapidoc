package com.xyp.sapidoc.idoc.exception;

/**
 *
 * @author Yunpeng_Xu
 */
public class IDocException extends RuntimeException {

    public IDocException() {
    }

    public IDocException(String message) {
        super(message);
    }

    public IDocException(String message, Throwable cause) {
        super(message, cause);
    }

    public IDocException(Throwable cause) {
        super(cause);
    }

    public IDocException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
