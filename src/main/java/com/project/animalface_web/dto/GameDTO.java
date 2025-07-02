package com.project.animalface_web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private Long gameNo;

    private String gameName;

    private String questions;
    private String answers;
    private String results;


}

