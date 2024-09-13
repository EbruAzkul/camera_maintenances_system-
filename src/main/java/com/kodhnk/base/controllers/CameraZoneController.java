package com.kodhnk.base.controllers;


import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.dto.camerazone.CreateCameraZoneRequest;
import com.kodhnk.base.dto.camerazone.UpdateCameraZoneRequest;
import com.kodhnk.base.entities.CameraZone;
import com.kodhnk.base.services.interfaces.ICameraZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kodhnk.base.core.utilities.Result;

import java.util.List;

@RestController
@RequestMapping("/api/v1/camerazones")
public class CameraZoneController {

    private final ICameraZoneService cameraZoneService;

    public CameraZoneController(ICameraZoneService cameraZoneService) {
        this.cameraZoneService = cameraZoneService;
    }

    @GetMapping("/getAllCamerazones")
    ResponseEntity<DataResult<List<CameraZone>>> getAllCameraZones() {
        DataResult<List<CameraZone>> result = cameraZoneService.getAllCameraZones();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getCameraZoneById")
    ResponseEntity<DataResult<CameraZone>> getCameraZoneById(@RequestParam Long id) {
        DataResult<CameraZone> result = cameraZoneService.getCameraZoneById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
    @PostMapping("/createCameraZone")
    ResponseEntity<DataResult<CameraZone>> createCameraZone(@RequestBody CreateCameraZoneRequest request) {
        DataResult<CameraZone> result = cameraZoneService.createCameraZone(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateCameraZone")
    ResponseEntity<DataResult<CameraZone>> updateCameraZone(@RequestBody UpdateCameraZoneRequest request) {
        DataResult<CameraZone> result = cameraZoneService.updateCameraZone(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/deleteCameraZone")
    ResponseEntity<Result> deleteCameraZone(@RequestParam Long id) {
        Result result = cameraZoneService.deleteCameraZone(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
