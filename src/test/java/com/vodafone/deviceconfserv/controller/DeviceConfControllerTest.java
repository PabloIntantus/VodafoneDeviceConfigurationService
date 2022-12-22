package com.vodafone.deviceconfserv.controller;

import com.vodafone.deviceconfserv.entity.DeviceEntity;
import com.vodafone.deviceconfserv.exception.DeviceException;
import com.vodafone.deviceconfserv.service.DeviceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DeviceConfControllerTest {

    @Mock
    private DeviceService deviceService;
    @InjectMocks
    private DeviceConfController deviceConfController;

    @Test
    @DisplayName("Should return an updatd device")
    void shouldReturnAnUpdatedDevice() throws DeviceException {
        DeviceEntity device = new DeviceEntity();
        device.setId("123456789");
        device.setStatus("available");
        device.setTemperature(20);
        device.setPinCode("1234567");
        Optional<DeviceEntity> deviceEntityOptional = Optional.of(device);
        doReturn(deviceEntityOptional).when(deviceService).findById(any());
        doReturn(device).when(deviceService).updateDevice(any());
        ResponseEntity<DeviceEntity> response = deviceConfController.updateDevice("123456789");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
    }

    @Test
    @DisplayName("Should return an Exception")
    void shouldReturnExceptionInUpdatedDevice() throws DeviceException {

        Optional<DeviceEntity> deviceEntityOptional = Optional.ofNullable(null);
        doReturn(deviceEntityOptional).when(deviceService).findById(any());
        assertThrows(DeviceException.class, () -> {
            deviceConfController.updateDevice("123456789");
        });

    }
}