package com.kodhnk.base.dto.cameramaintenance;

import com.kodhnk.base.entities.MaintenanceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateCameraMaintenanceRequest {
    private Long id;
    private Long cameraId;
    private Long technicianId;
    private Date reviewDate;
    private MaintenanceStatus status;
}
