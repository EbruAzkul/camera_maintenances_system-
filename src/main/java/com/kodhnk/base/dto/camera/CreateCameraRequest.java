package com.kodhnk.base.dto.camera;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class CreateCameraRequest {
    private String model;
    private String email;
    private String password;
    private LocalDate installationDate;
    private String status;
    private String username;
    private Set<Long> cameraNetworkIds;
    private Set<Long> roleIds;
    private Set<Long> sparePartIds;
}