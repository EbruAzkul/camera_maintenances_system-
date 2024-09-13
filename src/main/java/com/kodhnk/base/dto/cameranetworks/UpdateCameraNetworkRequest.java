package com.kodhnk.base.dto.cameranetworks;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateCameraNetworkRequest {

    private Long id;
    private String name;
    private List<Long> technicianId;
}
