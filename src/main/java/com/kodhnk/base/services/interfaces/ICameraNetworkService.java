package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.cameranetworks.AddTechnicianToNetworkRequest;
import com.kodhnk.base.dto.cameranetworks.CreateCameraNetworkRequest;
import com.kodhnk.base.dto.cameranetworks.UpdateCameraNetworkRequest;
import com.kodhnk.base.entities.CameraNetwork;

import java.util.List;

public interface ICameraNetworkService {

    DataResult<List<CameraNetwork>> getAllCameraNetwork();

    Result createCameraNetwork(CreateCameraNetworkRequest request);

    Result updateCameraNetwork(UpdateCameraNetworkRequest request);

    Result deleteCameraNetwork(Long id);

    DataResult<CameraNetwork> getById(Long id);

    Result addTechnicianToNetwork(AddTechnicianToNetworkRequest request);
}
