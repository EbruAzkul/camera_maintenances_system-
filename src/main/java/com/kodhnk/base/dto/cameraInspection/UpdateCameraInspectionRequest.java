package com.kodhnk.base.dto.cameraInspection;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class UpdateCameraInspectionRequest {

    private Long id;
    private Long technicianId;
    private Date inspectionDate;
    private String issuesFound;
    private String actionsTaken;
    private String notes;
}
