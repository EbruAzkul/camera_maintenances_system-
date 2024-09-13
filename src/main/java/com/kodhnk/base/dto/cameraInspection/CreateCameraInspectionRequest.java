package com.kodhnk.base.dto.cameraInspection;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateCameraInspectionRequest {
    private Long cameraId;
    private Long technicianId;
    private Date inspectionDate;
    private String issuesFound;
    private String actionsTaken;
    private String notes;
    List<Long> sparePartIds;
}
