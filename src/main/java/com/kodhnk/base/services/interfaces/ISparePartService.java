package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.sparepart.CreateSparePartRequest;
import com.kodhnk.base.dto.sparepart.UpdateSparePartRequest;
import com.kodhnk.base.entities.SparePart;

import java.util.List;

public interface ISparePartService {

    DataResult<List<SparePart>> getAllSpareParts();

    DataResult<SparePart> getSparePartById(Long id);

    DataResult<SparePart> createSparePart(CreateSparePartRequest request);

    DataResult<SparePart> updateSparePart(UpdateSparePartRequest request);

    Result deleteSparePart(Long id);
}
