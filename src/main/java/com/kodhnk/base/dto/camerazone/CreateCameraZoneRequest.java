package com.kodhnk.base.dto.camerazone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCameraZoneRequest {
    private String name;
    private Long cameraNetworkId;
}
