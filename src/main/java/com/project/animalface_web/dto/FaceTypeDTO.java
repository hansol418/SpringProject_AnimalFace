package com.project.animalface_web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FaceTypeDTO {
    private Long animalNo;

    private String animalType;

    private Long animalAccuracy;


}
