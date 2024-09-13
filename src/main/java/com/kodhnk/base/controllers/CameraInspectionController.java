package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.cameraInspection.CreateCameraInspectionRequest;
import com.kodhnk.base.dto.cameraInspection.UpdateCameraInspectionRequest;
import com.kodhnk.base.entities.CameraInspection;
import com.kodhnk.base.services.interfaces.ICameraInspectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cameraInspections")
public class CameraInspectionController {

    private final ICameraInspectionService cameraInspectionService;

    public CameraInspectionController(ICameraInspectionService cameraInspectionService) {
        this.cameraInspectionService = cameraInspectionService;
    }

    @GetMapping("getAllCameraInspections")
    public ResponseEntity<?> getAllCameraInspections(@RequestParam Long networkId) {
        DataResult<List<CameraInspection>> result = cameraInspectionService.getAllCameraInspections(networkId);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getCameraInspectionById")
    public ResponseEntity<DataResult<CameraInspection>> getCameraInspectionById(@RequestParam Long id) {
        DataResult<CameraInspection> result = cameraInspectionService.getCameraInspectionById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createCameraInspection")
    public ResponseEntity<DataResult<CameraInspection>> createCameraInspection(@RequestBody CreateCameraInspectionRequest request) {
        DataResult<CameraInspection> result = cameraInspectionService.createCameraInspection(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateCameraInspection")
    public ResponseEntity<DataResult<CameraInspection>> updateCameraInspection(@RequestBody UpdateCameraInspectionRequest request) {
        DataResult<CameraInspection> result = cameraInspectionService.updateCameraInspection(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }


    @DeleteMapping("/deleteCameraInspection")
    public ResponseEntity<Result> deleteCameraInspection(@RequestParam Long id) {
        Result result = cameraInspectionService.deletedCameraInspection(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
