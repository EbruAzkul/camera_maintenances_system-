package com.kodhnk.base.dto.cameranetworks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTechnicianToNetworkRequest {

    private Long cameranetworkId;
    private Long technicianId;
}
