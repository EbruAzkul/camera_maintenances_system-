package com.kodhnk.base.dto.cameranetworks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCameraNetworkRequest {
    private String name;
    private Long addressId;
}
