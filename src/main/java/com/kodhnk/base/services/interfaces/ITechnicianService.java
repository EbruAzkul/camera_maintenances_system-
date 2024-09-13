package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.technician.UpdateTechnicianRequest;
import com.kodhnk.base.dto.technician.CreateTechnicianRequest;
import com.kodhnk.base.entities.Technician;

import java.util.Set;

public interface ITechnicianService {

    DataResult<Set<Technician>> getTechniciansByNetwork(Long networkId);

    DataResult<Technician> getTechnicianById(Long id);

    Result createTechnician(CreateTechnicianRequest request);

    Result updateTechnician(UpdateTechnicianRequest request);

    Result deleteTechnician(Long id);
}
