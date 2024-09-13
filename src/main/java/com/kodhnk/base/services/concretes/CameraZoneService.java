package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.CameraZoneRepository;
import com.kodhnk.base.dto.camerazone.CreateCameraZoneRequest;
import com.kodhnk.base.dto.camerazone.UpdateCameraZoneRequest;
import com.kodhnk.base.entities.CameraNetwork;
import com.kodhnk.base.entities.CameraZone;
import com.kodhnk.base.services.interfaces.ICameraNetworkService;
import com.kodhnk.base.services.interfaces.ICameraZoneService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraZoneService implements ICameraZoneService {

    private final CameraZoneRepository cameraZoneRepository;
    private final ICameraNetworkService cameraNetworkService;

    public CameraZoneService(CameraZoneRepository cameraZoneRepository, ICameraNetworkService cameraNetworkService) {
        this.cameraZoneRepository = cameraZoneRepository;
        this.cameraNetworkService = cameraNetworkService;
    }
    @Override
    public DataResult<List<CameraZone>> getAllCameraZones() {
       List<CameraZone> cameraZones=cameraZoneRepository.findAll();
       return new SuccessDataResult<>(Response.CAMERAZONE_LISTED.getMessage(),cameraZones,200);
    }

    @Override
    public DataResult<CameraZone> getCameraZoneById(Long id) {
        Optional<CameraZone> cameraZone=cameraZoneRepository.findById(id);
        if(cameraZone.isPresent()){
            return new SuccessDataResult<>(Response.CAMERAZONE_FOUND.getMessage(),cameraZone.get(),200 );
        }
        return new ErrorDataResult<>(Response.CAMERAZONE_NOT_FOUND.getMessage(), cameraZone.get(),400);
    }

    @Override
    public DataResult<CameraZone> createCameraZone(CreateCameraZoneRequest request) {
       DataResult<CameraNetwork> cameraNetworkDataResult=cameraNetworkService.getById(request.getCameraNetworkId());
       if(!cameraNetworkDataResult.isSuccess()){
           return new ErrorDataResult<>(Response.CAMERANETWORK_NOT_FOUND.getMessage(), null,400);
       }
       CameraZone cameraZone=new CameraZone();
       cameraZone.setCameranetwork(cameraNetworkDataResult.getData());
       cameraZone.setName(request.getName());
       cameraZoneRepository.save(cameraZone);
       return new SuccessDataResult<>(Response.CREATE_CAMERAZONE.getMessage(), cameraZone,201);
    }


    @Override
    public DataResult<CameraZone> updateCameraZone(UpdateCameraZoneRequest request) {
        DataResult<CameraZone> result=getCameraZoneById(request.getId());
        if(result.isSuccess()){
            CameraZone cameraZone=result.getData();
            BeanUtils.copyProperties(request,cameraZone);
            cameraZoneRepository.save(cameraZone);
            return new SuccessDataResult<>(Response.UPDATE_CAMERAZONE.getMessage(), cameraZone,200);

        }
        return new ErrorDataResult<>(Response.CAMERAZONE_NOT_FOUND.getMessage(), null,400);
    }


    @Override
    public Result deleteCameraZone(Long id) {
        DataResult<CameraZone> cameraZoneDataResult=getCameraZoneById(id);
        if(cameraZoneDataResult.isSuccess()){
            CameraZone camerazone=cameraZoneDataResult.getData();
            cameraZoneRepository.delete(camerazone);
            return new SuccessResult(Response.DELETE_CAMERAZONE.getMessage(),200);
        }
        return new ErrorResult(Response.CAMERAZONE_NOT_FOUND.getMessage(), 400);
    }

}
