package com.project.animalface_web.service;

import com.project.animalface_web.domain.FaceType;
import com.project.animalface_web.dto.FaceTypeDTO;
import com.project.animalface_web.repository.FaceTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor

@Transactional
public class FaceTypeServiceImpl implements FaceTypeService{

    private final FaceTypeRepository faceTypeRepository;

    @Override
    public Long registerFaceType(FaceTypeDTO faceTypeDTO) {
        FaceType faceType = dtoToEntity(faceTypeDTO);
        Long faceTypeNo = faceTypeRepository.save(faceType).getAnimalNo();
        return faceTypeNo;
    }







}//Class
