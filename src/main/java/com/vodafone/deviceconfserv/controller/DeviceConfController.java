package com.vodafone.deviceconfserv.controller;

import com.vodafone.deviceconfserv.entity.DeviceEntity;
import com.vodafone.deviceconfserv.exception.DeviceApiErrorList;
import com.vodafone.deviceconfserv.exception.DeviceException;
import com.vodafone.deviceconfserv.service.DeviceService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The type Ware house controller.
 */
@RestController
@RequestMapping(value = "/device-conf-service")
public class DeviceConfController {
    @Autowired
    private DeviceService deviceService;


    /**
     * Update device response entity.
     *
     * @param id      the id
     * @param pinCode the pin code
     * @return the response entity
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DeviceEntity.class),
            @ApiResponse(code = 400, message = "Bad request", response = DeviceApiErrorList.class, responseContainer = "List")})
    @PutMapping(value = "/device/{id}")
    public ResponseEntity<DeviceEntity> updateDevice(@ApiParam(value = "Id of device", example = "63a2e81c2f3d95676fb0ec66", required = true)
                                                     @PathVariable(required = true) String id) throws DeviceException {

            Optional<DeviceEntity> optinalDevice = deviceService.findById(id);
            if (optinalDevice.isPresent()) {
                DeviceEntity deviceEntity = optinalDevice.get();
                DeviceEntity updatedDevice = deviceService.updateDevice(deviceEntity);
                return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
            } else {
                throw new DeviceException("Device not found", HttpStatus.NOT_FOUND, "Cant update device because cant find it in DB");
            }


    }
}
