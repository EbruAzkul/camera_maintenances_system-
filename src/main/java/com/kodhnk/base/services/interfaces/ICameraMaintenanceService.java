package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.cameramaintenance.CreateCameraMaintenanceRequest;
import com.kodhnk.base.dto.cameramaintenance.UpdateCameraMaintenanceRequest;
import com.kodhnk.base.entities.CameraMaintenance;

import java.util.List;

public interface ICameraMaintenanceService {
    DataResult<List<CameraMaintenance>> getAllCameraMaintenance();

    DataResult<CameraMaintenance> getCameraMaintenanceById(Long id);

    Result createCameraMaintenance(CreateCameraMaintenanceRequest request);

    Result updateCameraMaintenance(UpdateCameraMaintenanceRequest request);

    Result cancelCameraMaintenance(Long id);

}
