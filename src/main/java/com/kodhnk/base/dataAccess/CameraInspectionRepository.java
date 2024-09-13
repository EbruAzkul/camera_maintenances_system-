package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.CameraInspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraInspectionRepository extends JpaRepository<CameraInspection, Long> {
}
