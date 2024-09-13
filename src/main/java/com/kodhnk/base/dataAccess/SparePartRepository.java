package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SparePartRepository extends JpaRepository<SparePart,Long> {
}
