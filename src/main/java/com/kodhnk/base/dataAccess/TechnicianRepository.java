package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.CameraNetwork;
import com.kodhnk.base.entities.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    Set<Technician> findAllByCameraNetwork(CameraNetwork data);
}
