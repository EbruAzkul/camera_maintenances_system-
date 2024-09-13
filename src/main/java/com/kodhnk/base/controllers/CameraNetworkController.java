package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.cameranetworks.AddTechnicianToNetworkRequest;
import com.kodhnk.base.dto.cameranetworks.CreateCameraNetworkRequest;
import com.kodhnk.base.dto.cameranetworks.UpdateCameraNetworkRequest;
import com.kodhnk.base.entities.CameraNetwork;
import com.kodhnk.base.services.interfaces.ICameraNetworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/camera-networks")
public class CameraNetworkController {

    private final ICameraNetworkService cameraNetworkService;

    public CameraNetworkController(ICameraNetworkService cameraNetworkService) {
        this.cameraNetworkService = cameraNetworkService;
    }
    @GetMapping("/getAllCameraNetwork")
   public ResponseEntity<DataResult<List<CameraNetwork>>> getAllCameraNetwork(){
       DataResult<List<CameraNetwork>> result=cameraNetworkService.getAllCameraNetwork();
       return ResponseEntity.status(result.getStatusCode()).body(result);
   }



    @GetMapping("/getCameraNetworkById")
    public ResponseEntity<DataResult<CameraNetwork>> getCameraNetworkById(@RequestParam Long id) {
        DataResult<CameraNetwork> result = cameraNetworkService.getById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createCameraNetwork")
    public ResponseEntity<Result> createHospital(@RequestBody CreateCameraNetworkRequest request) {
        Result result = cameraNetworkService.createCameraNetwork(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateCameraNetwork")
    public ResponseEntity<Result> updateHospital(@RequestBody UpdateCameraNetworkRequest request) {
        Result result = cameraNetworkService.updateCameraNetwork(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("deleteCameraNetwork")
    public ResponseEntity<Result> deleteHospital(@RequestParam Long id) {
        Result result = cameraNetworkService.deleteCameraNetwork(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/addTechnicianToCameraNetwork")
    public ResponseEntity<Result> addTechnicianToNetwork(@RequestBody AddTechnicianToNetworkRequest request) {
        Result result = cameraNetworkService.addTechnicianToNetwork(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }


}
