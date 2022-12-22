package com.vodafone.deviceconfserv.service.impl;


import com.vodafone.deviceconfserv.entity.DeviceEntity;
import com.vodafone.deviceconfserv.repository.DeviceRepository;
import com.vodafone.deviceconfserv.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * The type Ware house service.
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    Random rand = new Random();

    public DeviceEntity updateDevice(DeviceEntity device) {
        int randomTemp = rand.nextInt(11);
        device.setStatus("ACTIVE");
        device.setTemperature(randomTemp);
        return deviceRepository.save(device);
    }

    public Optional<DeviceEntity> findById(String id) {
        return deviceRepository.findById(id);
    }

}
