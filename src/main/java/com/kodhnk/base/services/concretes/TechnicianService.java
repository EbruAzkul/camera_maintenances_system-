package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.TechnicianRepository;
import com.kodhnk.base.dataAccess.UserRepository;
import com.kodhnk.base.dto.technician.CreateTechnicianRequest;
import com.kodhnk.base.dto.technician.UpdateTechnicianRequest;
import com.kodhnk.base.entities.*;
import com.kodhnk.base.services.interfaces.ICameraNetworkService;
import com.kodhnk.base.services.interfaces.ICameraZoneService;
import com.kodhnk.base.services.interfaces.IRoleService;
import com.kodhnk.base.services.interfaces.ITechnicianService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TechnicianService implements ITechnicianService {

    private final TechnicianRepository technicianRepository;
    private final UserRepository userRepository;
    private final ICameraNetworkService cameraNetworkService;
    private final IRoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ICameraZoneService cameraZoneService;

    public TechnicianService(TechnicianRepository technicianRepository, UserRepository userRepository, ICameraNetworkService cameraNetworkService, IRoleService roleService, BCryptPasswordEncoder passwordEncoder, ICameraZoneService cameraZoneService) {
        this.technicianRepository = technicianRepository;
        this.userRepository = userRepository;
        this.cameraNetworkService = cameraNetworkService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.cameraZoneService = cameraZoneService;
    }


    @Override
    public DataResult<Set<Technician>> getTechniciansByNetwork(Long networkId) {
        DataResult<CameraNetwork> cameraNetworkDataResult=cameraNetworkService.getById(networkId);
        if(!cameraNetworkDataResult.isSuccess()){
            return new ErrorDataResult<>(Response.CAMERANETWORK_NOT_FOUND.getMessage(), null,400);

        }
        Set<Technician> technicians=technicianRepository.findAllByCameraNetwork(cameraNetworkDataResult.getData());
        return new SuccessDataResult<>(Response.GET_TECHNICIANS.getMessage(), technicians,200);
    }

    @Override
    public DataResult<Technician> getTechnicianById(Long id) {
        Optional<Technician> technician=technicianRepository.findById(id);
        if(technician.isPresent()){
            return new SuccessDataResult<>(Response.GET_TECHNICIAN.getMessage(), technician.get(),200);
        }else{
            return new ErrorDataResult<>(Response.TECHNICIAN_NOT_FOUND.getMessage(), null,400);
        }
    }


    @Override
    public Result updateTechnician(UpdateTechnicianRequest request) {
       DataResult<Technician> technicianDataResult=getTechnicianById(request.getId());
       if(!technicianDataResult.isSuccess()){
           return  new ErrorDataResult<>(Response.TECHNICIAN_NOT_FOUND.getMessage(), null,400);

       }
       Technician technician=technicianDataResult.getData();
       technicianRepository.save(technician);
       return new SuccessDataResult<>(Response.UPDATE_TECHNICIAN.getMessage(), technician,200);
    }


    @Override
    public Result deleteTechnician(Long id) {
       DataResult<Technician> technicianDataResult=getTechnicianById(id);
       if(!technicianDataResult.isSuccess()){
           return new ErrorResult(Response.TECHNICIAN_NOT_FOUND.getMessage(), 400);

       }
       Technician technician=technicianDataResult.getData();
       technicianRepository.delete(technician);
       return new SuccessResult(Response.DELETE_TECHNICIAN.getMessage(), 200);
    }


    @Override
    public Result createTechnician(CreateTechnicianRequest request) {
        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new ErrorDataResult<>(Response.EMAIL_ALREADY_EXISTS.getMessage(), null, 409);
        }

        // Create the User entity
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        // Fetch and set roles for the User
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

        // Save the User entity
        User savedUser = userRepository.save(user);

        Technician technician=new Technician();
        technician.setSpecialty(request.getSpeciality());
        technician.setUser(savedUser);

        DataResult<CameraNetwork> cameraNetworkDataResult=cameraNetworkService.getById(request.getCameraNetworkId());
        if(!cameraNetworkDataResult.isSuccess()){
            return new ErrorDataResult<>(Response.TECHNICIAN_NOT_FOUND.getMessage(), null,400);

        }
        technician.setCameraNetwork(cameraNetworkDataResult.getData());

        DataResult<CameraZone> cameraZoneDataResult=cameraZoneService.getCameraZoneById(request.getCameraZoneId());
        if(!cameraZoneDataResult.isSuccess()){
            return new ErrorDataResult<>(Response.CAMERAZONE_NOT_FOUND.getMessage(), null,400);

        }
        technician.setCameraZone(cameraZoneDataResult.getData());
        technicianRepository.save(technician);
        return new SuccessDataResult<>(Response.CREATE_TECHNICIAN.getMessage(), technician,201);

    }


}
