package com.vodafone.deviceconfserv.service.impl;

import com.vodafone.deviceconfserv.entity.DeviceEntity;
import com.vodafone.deviceconfserv.exception.DeviceException;
import com.vodafone.deviceconfserv.repository.DeviceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class DeviceServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;
    @InjectMocks
    private DeviceServiceImpl deviceService;

    DeviceEntity deviceTest = new DeviceEntity("1","READY",-1,"1234567");



    @Test
    @DisplayName("Should return a device updated with temp >= 0")
    void shouldReturnUpdatedDevice() throws DeviceException {
        DeviceEntity device = new DeviceEntity("1", "ACTIVE", -1, "0000001");
        when(deviceRepository.save(any())).thenReturn(device);
        DeviceEntity deviceUpdated = deviceService.updateDevice(device);
        assertEquals("0000001", deviceUpdated.getPinCode());
        assertTrue(deviceUpdated.getTemperature()>=0);
    }

    @Test
    @DisplayName("Should return true when find a device with ID")
    void shouldReturnDeviceWithID() throws DeviceException {
        DeviceEntity device = new DeviceEntity("1", "ACTIVE", 2, "0000001");
        Optional<DeviceEntity> deviceOptional = Optional.of(device);
        when(deviceService.findById(any())).thenReturn(deviceOptional);
        Optional<DeviceEntity> findDevice = deviceService.findById("1");
        assertTrue(findDevice.isPresent());
    }

}