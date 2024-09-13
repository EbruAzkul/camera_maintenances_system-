package com.kodhnk.base.dataAccess;
import com.kodhnk.base.entities.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CameraRepository extends JpaRepository<Camera, Long> {

    List<Camera> findAllByNetworks_Id(Long cameraNetworkId);
}
