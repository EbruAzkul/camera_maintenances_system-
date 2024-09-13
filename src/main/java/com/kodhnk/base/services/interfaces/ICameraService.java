package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.camera.CreateCameraRequest;
import com.kodhnk.base.dto.camera.UpdateCameraRequest;
import com.kodhnk.base.entities.Camera;

import java.util.Set;

public interface ICameraService {

    DataResult<Set<Camera>> getAllCameras(Long cameraNetworkId);

    DataResult<Camera> getByCameraId(Long cameraNetworkId);

    DataResult<Camera> createCamera(CreateCameraRequest request);

    DataResult<Camera> updateCamera(UpdateCameraRequest request);

    Result deleteCamera(Long id);
}
