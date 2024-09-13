package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.CameraRepository;
import com.kodhnk.base.dataAccess.UserRepository;
import com.kodhnk.base.dto.camera.CreateCameraRequest;
import com.kodhnk.base.dto.camera.UpdateCameraRequest;
import com.kodhnk.base.entities.Camera;
import com.kodhnk.base.entities.CameraNetwork;
import com.kodhnk.base.entities.Role;
import com.kodhnk.base.entities.User;
import com.kodhnk.base.services.interfaces.ICameraNetworkService;
import com.kodhnk.base.services.interfaces.ICameraService;
import com.kodhnk.base.services.interfaces.IRoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class CameraService implements ICameraService {

    private final CameraRepository cameraRepository;
    private final UserRepository userRepository;
    private final ICameraNetworkService cameraNetworkService;
    private final IRoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    public CameraService(CameraRepository cameraRepository, UserRepository userRepository, ICameraNetworkService cameraNetworkService, IRoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.cameraRepository = cameraRepository;
        this.userRepository = userRepository;
        this.cameraNetworkService = cameraNetworkService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public DataResult<Set<Camera>> getAllCameras(Long cameraNetworkId) {
        List<Camera> cameras=cameraRepository.findAllByNetworks_Id(cameraNetworkId);
        Set<Camera> cameraSet=new HashSet<>(cameras);
        return new SuccessDataResult<>(Response.GET_CAMERA.getMessage(), cameraSet,200);
    }

    @Override
    public DataResult<Camera> getByCameraId(Long cameraNetworkId) {
        Optional<Camera> camera=cameraRepository.findById(cameraNetworkId);
        if(camera.isPresent()){
            return new SuccessDataResult<>(Response.GET_CAMERA.getMessage(),camera.get(),200);
        }else{
            return new ErrorDataResult<>(Response.CAMERA_NOT_FOUND.getMessage(),null,400);
        }
    }

    @Override
    public DataResult<Camera> createCamera(CreateCameraRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new ErrorDataResult<>(Response.EMAIL_ALREADY_EXISTS.getMessage(), null, 409);
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Set<Role> roles = new HashSet<>();
        for (Long roleId : request.getRoleIds()) {
            DataResult<Role> roleDataResult = roleService.getRoleById(roleId);
            if (!roleDataResult.isSuccess()) {
                return new ErrorDataResult<>(Response.ROLE_NOT_FOUND.getMessage(), null, 400);
            }
            Role role = roleDataResult.getData();
            roles.add(role);
        }
        user.setRoles(roles);

        // Kullanıcıyı kaydet
        User savedUser = userRepository.save(user);

        //yeni cameralar için
        Camera camera = new Camera();
        camera.setModel(request.getModel());
        camera.setInstallationDate(request.getInstallationDate());
        camera.setStatus(request.getStatus());
        camera.setUser(savedUser);

        //Ağları ekle
        Set<CameraNetwork> cameraNetworks = new HashSet<>();
        for (Long cameraNetworkId : request.getCameraNetworkIds()) {
            DataResult<CameraNetwork> cameraNetworkOptional = cameraNetworkService.getById(cameraNetworkId);
            if (!cameraNetworkOptional.isSuccess()) {
                return new ErrorDataResult<>(Response.CAMERANETWORK_NOT_FOUND.getMessage(), null, 400);
            }
            cameraNetworks.add(cameraNetworkOptional.getData());
        }

        camera.setNetworks(cameraNetworks);

        cameraRepository.save(camera);
        return new SuccessDataResult<>(Response.CREATE_CAMERA.getMessage(), camera,200);
    }

    @Override
    public DataResult<Camera> updateCamera(UpdateCameraRequest request) {
        DataResult<Camera> cameraDataResult=getByCameraId(request.getId());
        if(!cameraDataResult.isSuccess()){
            return new ErrorDataResult<>(Response.CAMERA_NOT_FOUND.getMessage(), null,400);
        }
        Camera camera=cameraDataResult.getData();
        Set<CameraNetwork> networks=new HashSet<>();
        for(Long networkId:request.getCameraNetworkIds()){
            DataResult<CameraNetwork> networkOptional=cameraNetworkService.getById(networkId);
            if(!networkOptional.isSuccess()){
                return new ErrorDataResult<>(Response.CAMERANETWORK_NOT_FOUND.getMessage(), null,400);

            }
            if(!networks.contains(networkOptional.getData())){
                networks.add(networkOptional.getData());
            }
        }
        camera.setNetworks(networks);
        camera.setInstallationDate(request.getInstallationDate());
        camera.setStatus(request.getStatus());
        camera.setModel(request.getModel());
        cameraRepository.save(camera);
        return new SuccessDataResult<>(Response.UPDATE_CAMERA.getMessage(), camera,200);
    }
    @Override
    public Result deleteCamera(Long id) {
        DataResult<Camera> cameraDataResult=getByCameraId(id);
        if(!cameraDataResult.isSuccess()){
            return new ErrorDataResult<>(Response.CAMERA_NOT_FOUND.getMessage(), null,400);
        }
        Camera camera=cameraDataResult.getData();
        cameraRepository.delete(camera);

        return new SuccessDataResult<>(Response.DELETE_CAMERA.getMessage(), camera,200);
    }



}
