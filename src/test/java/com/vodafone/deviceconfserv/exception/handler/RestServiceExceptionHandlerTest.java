package com.vodafone.deviceconfserv.exception.handler;

import com.vodafone.deviceconfserv.exception.DeviceApiErrorList;
import com.vodafone.deviceconfserv.exception.DeviceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RestServiceExceptionHandlerTest {

    @InjectMocks
    private RestServiceExceptionHandler restServiceExceptionHandler;

    @Test
    @DisplayName(
            "Should return 500 when the exception is device and status is internal_server_error")
    void
    exceptionHandlerWhenExceptionIsDeviceExceptionAndStatusIsInternalServerErrorThenReturn500() {
        DeviceException exception =
                new DeviceException("message", HttpStatus.INTERNAL_SERVER_ERROR, "description");
        ResponseEntity<DeviceApiErrorList> responseEntity =
                restServiceExceptionHandler.exceptionHandler(exception);
        assertEquals(500, responseEntity.getBody().getErrors().get(0).getCode());
    }

    @Test
    @DisplayName(
            "Should return 400 when the exception is device and status is bad_request")
    void exceptionHandlerWhenExceptionIsDeviceExceptionAndStatusIsBadRequestThenReturn400() {
        DeviceException exception =
                new DeviceException("message", HttpStatus.BAD_REQUEST, "description");
        ResponseEntity<DeviceApiErrorList> responseEntity =
                restServiceExceptionHandler.exceptionHandler(exception);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName(
            "Should return 404 when the exception is deviceexception and status is not_found")
    void exceptionHandlerWhenExceptionIsDeviceExceptionAndStatusIsNotFoundThenReturn404() {
        DeviceException exception =
                new DeviceException("message", HttpStatus.NOT_FOUND, "description");
        ResponseEntity<DeviceApiErrorList> responseEntity =
                restServiceExceptionHandler.exceptionHandler(exception);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName(
            "Should return 500 when the exception is deviceexception and status is not_found")
    void exceptionHandlerWhenExceptionIsdeviceExceptionAndStatus500() {
        DeviceException exception =
                new DeviceException("message", HttpStatus.INTERNAL_SERVER_ERROR, "description");
        Throwable err = new Throwable();
        Throwable err2 = new DeviceException("message",HttpStatus.INTERNAL_SERVER_ERROR,"description");
        DeviceException exception2 = new DeviceException("message",err);
        DeviceException exception3 = new DeviceException("message",err2);
        ResponseEntity<DeviceApiErrorList> responseEntity =
                restServiceExceptionHandler.exceptionHandler(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should return 500 when the exception is not deviceexception")
    void exceptionHandlerWhenExceptionIsNotDeviceExceptionThenReturn500() {
        Exception exception = new Exception("something was wrong");
        ResponseEntity<DeviceApiErrorList> responseEntity =
                restServiceExceptionHandler.exceptionHandler(exception);
        assertEquals(500, responseEntity.getBody().getErrors().get(0).getCode());
        assertEquals(
                "Something was wrong", responseEntity.getBody().getErrors().get(0).getMessage());
        assertEquals("Error", responseEntity.getBody().getErrors().get(0).getLevel());
        assertEquals(
                "Something was wrong description",
                responseEntity.getBody().getErrors().get(0).getDescription());
    }
}