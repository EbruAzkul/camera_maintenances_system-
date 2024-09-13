package com.kodhnk.base.dto.technician;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateTechnicianRequest {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String speciality;
    private Long cameraNetworkId;
    private Long cameraZoneId;
    private Set<Long> roleIds;
}
