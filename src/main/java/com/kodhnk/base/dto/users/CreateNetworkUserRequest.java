package com.kodhnk.base.dto.users;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateNetworkUserRequest {
    private Long networkId;
    private String speciality;
    private Long departmentId;
}