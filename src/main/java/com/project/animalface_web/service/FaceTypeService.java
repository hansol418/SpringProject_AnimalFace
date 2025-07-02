package com.project.animalface_web.service;

import com.project.animalface_web.domain.FaceType;
import com.project.animalface_web.dto.FaceTypeDTO;

public interface FaceTypeService {

    Long registerFaceType(FaceTypeDTO faceTypeDTO);


    default FaceTypeDTO entityToDTO(FaceType faceType) {
        FaceTypeDTO faceTypeDTO =  FaceTypeDTO.builder()
                .animalType(faceType.getAnimalType())
                .animalAccuracy(faceType.getAnimalAccuracy())
                .build();
        return faceTypeDTO;
    }

    default FaceType dtoToEntity(FaceTypeDTO faceTypeDTO) {
        FaceType faceType = FaceType.builder()
                .animalType(faceTypeDTO.getAnimalType())
                .animalAccuracy(faceTypeDTO.getAnimalAccuracy())
                .build();
        return faceType;
    }








}