package com.kodhnk.base.dto.technician;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UpdateTechnicianRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String speciality;
    private Long cameraNetworkId;
    private Long cameraZoneId;
}
