package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.camera.CreateCameraRequest;
import com.kodhnk.base.dto.camera.UpdateCameraRequest;
import com.kodhnk.base.entities.Camera;
import com.kodhnk.base.services.interfaces.ICameraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/cameras")
public class CameraController {
    private final ICameraService cameraService;

    public CameraController(ICameraService cameraService) {
        this.cameraService = cameraService;
    }


    @GetMapping("/getAllCameras")
    public ResponseEntity<DataResult<Set<Camera>>> getAllCameras(@RequestParam Long cameraNetworkId) {
        DataResult<Set<Camera>> result = cameraService.getAllCameras(cameraNetworkId);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getByCameraId")
    public ResponseEntity<DataResult<Camera>> getByCameraId(@RequestParam Long cameraNetworkId) {
        DataResult<Camera> result = cameraService.getByCameraId(cameraNetworkId);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createCamera")
    public ResponseEntity<DataResult<Camera>> createCamera(@RequestBody CreateCameraRequest request) {
        DataResult<Camera> result = cameraService.createCamera(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateCamera")
    public ResponseEntity<DataResult<Camera>> updateCamera(@RequestBody UpdateCameraRequest request) {
        DataResult<Camera> result = cameraService.updateCamera(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/deleteCamera")
    public ResponseEntity<Result> deleteCamera(@RequestParam Long id) {
        Result result = cameraService.deleteCamera(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
