package com.vodafone.deviceconfserv.exception.handler;

import com.mongodb.DuplicateKeyException;
import com.vodafone.deviceconfserv.exception.DeviceApiError;
import com.vodafone.deviceconfserv.exception.DeviceApiErrorList;
import com.vodafone.deviceconfserv.exception.DeviceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Rest service exception handler.
 */
@ControllerAdvice
public class RestServiceExceptionHandler {

    /**
     * Exception handler response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DeviceApiErrorList> exceptionHandler(Exception exception) {
        int code = 500;
        String message = "Something was wrong";
        String level = "Error";
        String description = "Something was wrong description";

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (exception instanceof DeviceException deviceException) {
            code = deviceException.getCode();
            message = exception.getMessage();
            description = deviceException.getDescription();
            if (deviceException.getStatus() != null) {
                status = deviceException.getStatus();
                if (status.equals(HttpStatus.BAD_REQUEST)) {
                    code = 400;
                } else if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                    code = 500;
                } else if (status.equals(HttpStatus.NOT_FOUND)) {
                    code = 404;
                }
            }
        }

        DeviceApiError error = new DeviceApiError(code, message, level, description);
        DeviceApiErrorList errors = new DeviceApiErrorList();
        List<DeviceApiError> errorList = new ArrayList<>();
        errorList.add(error);
        errors.setErrors(errorList);
        return new ResponseEntity<>(errors, status);
    }
}
