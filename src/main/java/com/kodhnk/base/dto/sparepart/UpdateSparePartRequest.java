package com.kodhnk.base.dto.sparepart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSparePartRequest {

    private Long id;
    private String partName;
    private String specification;
    private String instructions;

}
