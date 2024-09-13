package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.*;
import com.kodhnk.base.dto.cameramaintenance.CreateCameraMaintenanceRequest;
import com.kodhnk.base.dto.cameramaintenance.UpdateCameraMaintenanceRequest;
import com.kodhnk.base.entities.*;
import com.kodhnk.base.services.interfaces.ICameraMaintenanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraMaintenanceService implements ICameraMaintenanceService {

    private final CameraMaintenanceRepository cameraMaintenanceRepository;
    private final TechnicianRepository technicianRepository;
    private final CameraNetworkRepository cameraNetworkRepository;
    private final CameraRepository cameraRepository;
    private final CameraZoneRepository cameraZoneRepository;

    public CameraMaintenanceService(CameraMaintenanceRepository cameraMaintenanceRepository, TechnicianRepository technicianRepository, CameraNetworkRepository cameraNetworkRepository, CameraRepository cameraRepository, CameraZoneRepository cameraZoneRepository) {
        this.cameraMaintenanceRepository = cameraMaintenanceRepository;
        this.technicianRepository = technicianRepository;
        this.cameraNetworkRepository = cameraNetworkRepository;
        this.cameraRepository = cameraRepository;
        this.cameraZoneRepository = cameraZoneRepository;
    }


    @Override
    public DataResult<List<CameraMaintenance>> getAllCameraMaintenance() {
        List<CameraMaintenance> cameraMaintenances = cameraMaintenanceRepository.findAll();
        return new SuccessDataResult<>(Response.GET_CAMERAMAINTENANCE.getMessage(), cameraMaintenances, 200);
    }

    @Override
    public DataResult<CameraMaintenance> getCameraMaintenanceById(Long id) {
        Optional<CameraMaintenance> cameraMaintenance=cameraMaintenanceRepository.findById(id);
        if(cameraMaintenance.isPresent()){
            return new SuccessDataResult<>(Response.GET_CAMERAMAINTENANCE.getMessage(), cameraMaintenance.get(),200);

        }else{
            return  new ErrorDataResult<>(Response.CAMERAMAINTENANCE_NOT_FOUND.getMessage(), null,400);
        }
    }

    @Override
    public DataResult<CameraMaintenance> createCameraMaintenance(CreateCameraMaintenanceRequest request) {
      Optional<Camera> camera = cameraRepository.findById(request.getCameraId());
      if(!camera.isPresent()){
          return new ErrorDataResult<>(Response.CAMERA_NOT_FOUND.getMessage(), null,400);
      }
      Optional<Technician> technician =technicianRepository.findById(request.getTechnicianId());
      if(!technician.isPresent()){
          return new ErrorDataResult<>(Response.TECHNICIAN_NOT_FOUND.getMessage(), null,400);
      }
      Optional<CameraZone> cameraZone=cameraZoneRepository.findById(request.getCameraZoneId());
      if(!cameraZone.isPresent()){
          return new ErrorDataResult<>(Response.CAMERAZONE_NOT_FOUND.getMessage(), null,400);
      }

      CameraMaintenance cameraMaintenance=new CameraMaintenance();
      cameraMaintenance.setCamera(camera.get());
      cameraMaintenance.setTechnician(technician.get());
      cameraMaintenance.setCameraZone(cameraZone.get());
      cameraMaintenance.setReviewDate(request.getReviewDate());
      cameraMaintenance.setStatus(MaintenanceStatus.PENDING);
      cameraMaintenanceRepository.save(cameraMaintenance);
      return new SuccessDataResult<>(Response.CREATE_CAMERAMAINTENANCE.getMessage(), cameraMaintenance,200);
    }


    @Override
    public Result updateCameraMaintenance(UpdateCameraMaintenanceRequest request) {
        Optional<CameraMaintenance> existingMaintenance=cameraMaintenanceRepository.findById(request.getId());
        if(!existingMaintenance.isPresent()){
            return new ErrorResult(Response.CAMERAMAINTENANCE_NOT_FOUND.getMessage(), 400);

        }
        CameraMaintenance cameraMaintenance=existingMaintenance.get();
        Optional<Camera> camera=cameraRepository.findById(request.getCameraId());
        if(!camera.isPresent()){
            return new ErrorResult("Camera Not Found",400);
        }
        Optional<Technician> technician=technicianRepository.findById(request.getTechnicianId());
        if(!technician.isPresent()){
            return new ErrorResult("Technician not found",400);
        }
        cameraMaintenance.setCamera(camera.get());
        cameraMaintenance.setTechnician(technician.get());
        cameraMaintenance.setStatus(MaintenanceStatus.COMPLETED);
        cameraMaintenanceRepository.save(cameraMaintenance);
        return new SuccessResult(Response.UPDATE_CAMERAMAINTENANCE.getMessage(), 200);
    }


    @Override
    public Result cancelCameraMaintenance(Long id) {
       Optional<CameraMaintenance> cameraMaintenance=cameraMaintenanceRepository.findById(id);
       if(cameraMaintenance.isPresent()){
           CameraMaintenance currentCameraMaintenance=cameraMaintenance.get();
           currentCameraMaintenance.setStatus(MaintenanceStatus.CANCELLED);
           cameraMaintenanceRepository.save(currentCameraMaintenance);
           return new SuccessResult(Response.DELETE_CAMERAMAINTENANCE.getMessage(), 200);
       }
       return new ErrorResult(Response.CAMERAMAINTENANCE_NOT_FOUND.getMessage(), 400);
    }


}
