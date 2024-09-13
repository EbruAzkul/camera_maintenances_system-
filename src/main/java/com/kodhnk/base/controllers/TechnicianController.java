package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.technician.CreateTechnicianRequest;
import com.kodhnk.base.dto.technician.UpdateTechnicianRequest;
import com.kodhnk.base.entities.Technician;
import com.kodhnk.base.services.interfaces.ITechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/technicians")
public class TechnicianController {

    private final ITechnicianService technicianService;

    public TechnicianController(ITechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping("/getTechniciansByNetwork")
    public ResponseEntity<DataResult<Set<Technician>>> getTechniciansByNetwork(@RequestParam Long networkId) {
        DataResult<Set<Technician>> result = technicianService.getTechniciansByNetwork(networkId);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createTechnician")
    public ResponseEntity<Result> createTechnician(@RequestBody CreateTechnicianRequest request) {
        Result result = technicianService.createTechnician(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateTechnician")
    public ResponseEntity<Result> updateTechnician(@RequestBody UpdateTechnicianRequest request) {
        Result result = technicianService.updateTechnician(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }


    @DeleteMapping("/deleteTechnician")
    public ResponseEntity<Result> deleteTechnician(@RequestParam Long id) {
        Result result = technicianService.deleteTechnician(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

}
