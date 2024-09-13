package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.CameraNetworkRepository;
import com.kodhnk.base.dataAccess.TechnicianRepository;
import com.kodhnk.base.dto.cameranetworks.AddTechnicianToNetworkRequest;
import com.kodhnk.base.dto.cameranetworks.CreateCameraNetworkRequest;
import com.kodhnk.base.dto.cameranetworks.UpdateCameraNetworkRequest;
import com.kodhnk.base.entities.Address;
import com.kodhnk.base.entities.CameraNetwork;
import com.kodhnk.base.entities.Technician;
import com.kodhnk.base.services.interfaces.IAddressService;
import com.kodhnk.base.services.interfaces.ICameraNetworkService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraNetworkService implements ICameraNetworkService {

    private final CameraNetworkRepository cameraNetworkRepository;
    private final TechnicianRepository technicianRepository;
    private final IAddressService addressService;

    public CameraNetworkService(CameraNetworkRepository cameraNetworkRepository, TechnicianRepository technicianRepository, IAddressService addressService) {
        this.cameraNetworkRepository = cameraNetworkRepository;
        this.technicianRepository = technicianRepository;
        this.addressService = addressService;
    }


    @Override
    public DataResult<List<CameraNetwork>> getAllCameraNetwork() {
        List<CameraNetwork> cameraNetworks=cameraNetworkRepository.findAll();
        return new SuccessDataResult<>(Response.CAMERANETWORK_GET_ALL.getMessage(), cameraNetworks,200);
    }


    @Override
    public Result createCameraNetwork(CreateCameraNetworkRequest request) {
        CameraNetwork cameraNetwork=new CameraNetwork();
        DataResult<Address> addressResult = addressService.getAddressById(request.getAddressId());
        if (!addressResult.isSuccess()) {
            return new ErrorDataResult<>(Response.ADDRESS_NOT_FOUND.getMessage(), null, 400);
        }
        BeanUtils.copyProperties(request, cameraNetwork);
        cameraNetwork.setAddress(addressResult.getData());
        cameraNetworkRepository.save(cameraNetwork);
        return new SuccessDataResult<>(Response.CREATE_CAMERANETWORK.getMessage(), cameraNetwork, 201);
    }

    @Override
    public Result updateCameraNetwork(UpdateCameraNetworkRequest request) {
        DataResult<CameraNetwork> result=getById(request.getId());
        if (!result.isSuccess()) {
            return new ErrorDataResult<>(Response.CAMERANETWORK_NOT_FOUND.getMessage(),null, 400);
        }
        CameraNetwork cameraNetwork = result.getData();
        BeanUtils.copyProperties(request, cameraNetwork);
        cameraNetworkRepository.save(cameraNetwork);
        return new SuccessDataResult<>(Response.UPDATE_CAMERANETWORK.getMessage(), cameraNetwork, 200);
    }

    @Override
    public Result deleteCameraNetwork(Long id) {
        CameraNetwork cameraNetwork=getById(id).getData();
        if (cameraNetwork == null) {
            return new ErrorResult(Response.CAMERANETWORK_NOT_FOUND.getMessage(), 400);
        }
        cameraNetworkRepository.delete(cameraNetwork);
        return new SuccessResult(Response.DELETE_CAMERANETWORK.getMessage(), 200);
    }

    @Override
    public DataResult<CameraNetwork> getById(Long id) {
        Optional<CameraNetwork> cameraNetwork=cameraNetworkRepository.findById(id);
        if (cameraNetwork.isPresent()) {
            return new SuccessDataResult<>(Response.CAMERANETWORK_BY_ID.getMessage(), cameraNetwork.get(), 200);
        } else {
            return new ErrorDataResult<>(Response.CAMERANETWORK_NOT_FOUND.getMessage(), null, 400);
        }
    }

    @Override
    public Result addTechnicianToNetwork(AddTechnicianToNetworkRequest request) {
        Optional<CameraNetwork> cameraNetworkOpt=cameraNetworkRepository.findById(request.getCameranetworkId());

        Optional<Technician> technicianOpt = technicianRepository.findById(request.getTechnicianId());

        if (cameraNetworkOpt.isPresent() && technicianOpt.isPresent()) {
            CameraNetwork cameranetwork = cameraNetworkOpt.get();
            Technician technician = technicianOpt.get();
            technician.setCameraNetwork(cameranetwork);
            technician.setCameraNetwork(cameranetwork);
            technicianRepository.save(technician);
            cameraNetworkRepository.save(cameranetwork);
            return new SuccessResult(Response.SUCCESS.getMessage(), 200);
        } else {
            return new ErrorResult(Response.ERROR.getMessage(), 400);
        }
    }

}
