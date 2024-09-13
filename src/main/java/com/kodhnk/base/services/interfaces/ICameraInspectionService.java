package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.cameraInspection.CreateCameraInspectionRequest;
import com.kodhnk.base.dto.cameraInspection.UpdateCameraInspectionRequest;
import com.kodhnk.base.entities.CameraInspection;

import java.util.List;

public interface ICameraInspectionService {

    DataResult<List<CameraInspection>> getAllCameraInspections(Long cameraNetworkId);

    DataResult<CameraInspection> getCameraInspectionById(Long id);

    DataResult<CameraInspection> createCameraInspection(CreateCameraInspectionRequest request);

    DataResult<CameraInspection> updateCameraInspection(UpdateCameraInspectionRequest request);

    Result deletedCameraInspection(Long id);
}
