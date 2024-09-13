package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.ErrorDataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.core.utilities.SuccessDataResult;
import com.kodhnk.base.dataAccess.CameraInspectionRepository;
import com.kodhnk.base.dataAccess.CameraRepository;
import com.kodhnk.base.dataAccess.SparePartRepository;
import com.kodhnk.base.dataAccess.TechnicianRepository;
import com.kodhnk.base.dto.cameraInspection.CreateCameraInspectionRequest;
import com.kodhnk.base.dto.cameraInspection.UpdateCameraInspectionRequest;
import com.kodhnk.base.entities.Camera;
import com.kodhnk.base.entities.CameraInspection;
import com.kodhnk.base.entities.SparePart;
import com.kodhnk.base.entities.Technician;
import com.kodhnk.base.services.interfaces.ICameraInspectionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraInspectionService implements ICameraInspectionService {

    private final CameraInspectionRepository cameraInspectionRepository;
    private final CameraRepository cameraRepository;
    private final TechnicianRepository technicianRepository;
    private final SparePartRepository sparePartRepository;

    public CameraInspectionService(CameraInspectionRepository cameraInspectionRepository, CameraRepository cameraRepository, TechnicianRepository technicianRepository, SparePartRepository sparePartRepository) {
        this.cameraInspectionRepository = cameraInspectionRepository;
        this.cameraRepository = cameraRepository;
        this.technicianRepository = technicianRepository;
        this.sparePartRepository = sparePartRepository;
    }


    @Override
    public DataResult<List<CameraInspection>> getAllCameraInspections(Long cameraNetworkId) {
        return null;
    }

    @Override
    public DataResult<CameraInspection> getCameraInspectionById(Long id) {
        Optional<CameraInspection> cameraInspection=cameraInspectionRepository.findById(id);
        if(cameraInspection.isPresent()){
            return  new SuccessDataResult<>(Response.GET_CAMERAINSPECTION.getMessage(), cameraInspection.get(),200);
        }else{
            return new ErrorDataResult<>(Response.CAMERAINSPECTION_NOT_FOUND.getMessage(), null,200);
        }
    }


    @Override
    public DataResult<CameraInspection> createCameraInspection(CreateCameraInspectionRequest request) {
        Optional<Camera> camera = cameraRepository.findById(request.getCameraId());
        Optional<Technician> technician = technicianRepository.findById(request.getTechnicianId());

        if (!camera.isPresent() || !technician.isPresent()) {
     return new ErrorDataResult<>("Camera or technician not found",null,404);
        }

        List<SparePart> spareParts = sparePartRepository.findAllById(request.getSparePartIds());
        if (spareParts.size() != request.getSparePartIds().size()) {
            return new ErrorDataResult<>("One or more spareParts not found", null, 400);
        }

        CameraInspection newCameraInspection=new CameraInspection();
        newCameraInspection.setCamera(camera.get());
        newCameraInspection.setTechnician(technician.get());
        newCameraInspection.setNotes(request.getNotes());
        newCameraInspection.setInspectionDate(request.getInspectionDate());
        newCameraInspection.setIssuesFound(request.getIssuesFound());
        newCameraInspection.setActionsTaken(request.getActionsTaken());
        newCameraInspection.setUsedParts(spareParts);

        CameraInspection savedCameraInspection=cameraInspectionRepository.save(newCameraInspection);
        return new SuccessDataResult<>(Response.CREATE_CAMERAINSPECTION.getMessage(), savedCameraInspection, 201);

    }

    @Override
    public DataResult<CameraInspection> updateCameraInspection(UpdateCameraInspectionRequest request) {
        return null;
    }

    @Override
    public Result deletedCameraInspection(Long id) {
        return null;
    }
}
