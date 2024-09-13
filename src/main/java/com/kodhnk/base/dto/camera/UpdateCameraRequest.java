package com.kodhnk.base.dto.camera;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UpdateCameraRequest {
    private Long id;
    private String model;
    private LocalDate installationDate;
    private String status;
    private String username;
    private Set<Long> cameraNetworkIds;
}
