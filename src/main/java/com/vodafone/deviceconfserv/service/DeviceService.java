package com.vodafone.deviceconfserv.service;

import com.vodafone.deviceconfserv.entity.DeviceEntity;
import com.vodafone.deviceconfserv.exception.DeviceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Ware house service.
 */
public interface DeviceService {

    /**
     * Update device device entity.
     *
     * @param device the device
     * @return the device entity
     */
    DeviceEntity updateDevice(DeviceEntity device);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<DeviceEntity> findById(String id);

}
