package com.kodhnk.base.dto.cameramaintenance;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateCameraMaintenanceRequest {

    private Long cameraNetworkId;
    private Long cameraZoneId;
    private Long cameraId;
    private Long technicianId;
    private Date reviewDate;
}
