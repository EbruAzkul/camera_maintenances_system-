package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.cameramaintenance.CreateCameraMaintenanceRequest;
import com.kodhnk.base.dto.cameramaintenance.UpdateCameraMaintenanceRequest;
import com.kodhnk.base.entities.CameraMaintenance;
import com.kodhnk.base.services.interfaces.ICameraMaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cameraMaintenances")
public class CameraMaintenanceController {

    private final ICameraMaintenanceService cameraMaintenanceService;

    public CameraMaintenanceController(ICameraMaintenanceService cameraMaintenanceService) {
        this.cameraMaintenanceService = cameraMaintenanceService;
    }

    @GetMapping("/getAllCameraMaintenance")
    public ResponseEntity<DataResult<List<CameraMaintenance>>> getAllCameraMaintenance() {
        DataResult<List<CameraMaintenance>> result = cameraMaintenanceService.getAllCameraMaintenance();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }


    @PostMapping("/createCameraMaintenance")
    public ResponseEntity<Result> createCameraMaintenance(@RequestBody CreateCameraMaintenanceRequest request) {
        Result result = cameraMaintenanceService.createCameraMaintenance(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateCameraMaintenance")
    public ResponseEntity<Result> updateCameraMaintenance(@RequestBody UpdateCameraMaintenanceRequest request) {
        Result result = cameraMaintenanceService.updateCameraMaintenance(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }


    @DeleteMapping("/cancelCameraMaintenance/{id}")
    public ResponseEntity<Result> cancelCameraMaintenance(@PathVariable Long id) {
        Result result = cameraMaintenanceService.cancelCameraMaintenance(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
