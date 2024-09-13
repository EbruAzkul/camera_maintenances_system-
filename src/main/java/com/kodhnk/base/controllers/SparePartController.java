package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.dto.sparepart.CreateSparePartRequest;
import com.kodhnk.base.dto.sparepart.UpdateSparePartRequest;
import com.kodhnk.base.entities.SparePart;
import com.kodhnk.base.services.interfaces.ISparePartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kodhnk.base.core.utilities.Result;
import java.util.List;

@RestController
@RequestMapping("/api/v1/spareParts")
public class SparePartController {

    private final ISparePartService sparePartService;

    public SparePartController(ISparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }

    @GetMapping("/getAllSpareParts")
    ResponseEntity<DataResult<List<SparePart>>> getAllSpareParts() {
        DataResult<List<SparePart>> result = sparePartService.getAllSpareParts();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }


    @GetMapping("/getSparePartById")
    ResponseEntity<DataResult<SparePart>> getSparePartById(@RequestParam Long id) {
        DataResult<SparePart> result = sparePartService.getSparePartById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createSparePart")
    ResponseEntity<DataResult<SparePart>> createSparePart(@RequestBody CreateSparePartRequest request) {
        DataResult<SparePart> result = sparePartService.createSparePart(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateSparePart")
    ResponseEntity<DataResult<SparePart>> updateSparePart(@RequestBody UpdateSparePartRequest request) {
        DataResult<SparePart> result = sparePartService.updateSparePart(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/deleteSparePart")
    ResponseEntity<Result> deleteSparePart(@RequestParam Long id) {
        Result result = sparePartService.deleteSparePart(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
