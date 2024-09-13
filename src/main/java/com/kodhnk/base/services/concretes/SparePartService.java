package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.SparePartRepository;
import com.kodhnk.base.dto.sparepart.CreateSparePartRequest;
import com.kodhnk.base.dto.sparepart.UpdateSparePartRequest;
import com.kodhnk.base.entities.SparePart;
import com.kodhnk.base.services.interfaces.ISparePartService;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@Service
public class SparePartService implements ISparePartService {
    private final SparePartRepository sparePartRepository;

    public SparePartService(SparePartRepository sparePartRepository) {
        this.sparePartRepository = sparePartRepository;
    }

    @Override
    public DataResult<List<SparePart>> getAllSpareParts() {
        List<SparePart> spareParts=sparePartRepository.findAll();
        return new SuccessDataResult<>(Response.SPAREPART_LISTED.getMessage(), spareParts,200);
    }

    @Override
    public DataResult<SparePart> getSparePartById(Long id) {
        Optional<SparePart> sparePart=sparePartRepository.findById(id);
        if(sparePart.isPresent()){
            return new SuccessDataResult<>(Response.SPAREPART_FOUND.getMessage(),sparePart.get(),200);
        }
        return new ErrorDataResult<>(Response.SPAREPART_NOT_FOUND.getMessage(),null,400);
    }


    @Override
    public DataResult<SparePart> createSparePart(CreateSparePartRequest request) {
        SparePart sparePart=new SparePart();
        BeanUtils.copyProperties(request, sparePart);
        sparePartRepository.save(sparePart);
        return new SuccessDataResult<>(Response.SPAREPART_CREATED.getMessage(), sparePart, 201);

    }


    @Override
    public DataResult<SparePart> updateSparePart(UpdateSparePartRequest request) {
       DataResult<SparePart> result=getSparePartById(request.getId());
       if(result.isSuccess()){
           SparePart sparePart=result.getData();
           BeanUtils.copyProperties(request,sparePart);
           sparePartRepository.save(sparePart);
           return new SuccessDataResult<>(Response.SPAREPART_UPDATED.getMessage(), sparePart,200);
       }
       return new ErrorDataResult<>(Response.SPAREPART_NOT_FOUND.getMessage(), null,400);
    }


    @Override
    public Result deleteSparePart(Long id) {
        DataResult<SparePart> sparePartDataResult=getSparePartById(id);
        if(sparePartDataResult.isSuccess()){
            SparePart sparePart=sparePartDataResult.getData();
            sparePartRepository.delete(sparePart);
            return new SuccessResult(Response.SPAREPART_DELETED.getMessage(), 200);
        }
        return new ErrorResult(Response.SPAREPART_NOT_FOUND.getMessage(), 400);
    }





}
