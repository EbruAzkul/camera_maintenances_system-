package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.camerazone.CreateCameraZoneRequest;
import com.kodhnk.base.dto.camerazone.UpdateCameraZoneRequest;
import com.kodhnk.base.entities.CameraZone;
import java.util.List;

public interface ICameraZoneService {

    DataResult<List<CameraZone>> getAllCameraZones();

    DataResult<CameraZone> getCameraZoneById(Long id);

    DataResult<CameraZone> createCameraZone(CreateCameraZoneRequest request);

    DataResult<CameraZone> updateCameraZone(UpdateCameraZoneRequest request);

    Result deleteCameraZone(Long id);
}
