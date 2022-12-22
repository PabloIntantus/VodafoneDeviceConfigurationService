package com.vodafone.deviceconfserv.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The type Ware house exception.
 */
@Getter
public class DeviceException extends Exception {

    private int code;
    private HttpStatus status;

    /**
     * The Level.
     */
    String level = "error";

    /**
     * The Description.
     */
    String description = "something was wrong";

    /**
     * The Err.
     */
    Throwable err;

    /**
     * Instantiates a new Ware house exception.
     *
     * @param errorMenssage the error menssage
     * @param err           the err
     */
    public DeviceException(String errorMenssage, Throwable err) {
        super(errorMenssage, err);
        if (err instanceof DeviceException deviceException) {
            this.err = deviceException.getErr();
        } else {
            this.err = err;
        }
    }

    /**
     * Instantiates a new Ware house exception.
     *
     * @param message     the message
     * @param status      the status
     * @param description the description
     */
    public DeviceException(String message, HttpStatus status, String description) {
        super(message);
        this.description = description;
        this.status = status;
    }
}
