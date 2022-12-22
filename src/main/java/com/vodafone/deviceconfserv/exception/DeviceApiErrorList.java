package com.vodafone.deviceconfserv.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * The type Ware house api error list.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceApiErrorList {
    /**
     * The Errors.
     */
    @ApiModelProperty(value = "List of errors")
    List<DeviceApiError> errors;
}
