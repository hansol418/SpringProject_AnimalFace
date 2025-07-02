package com.project.animalface_web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateGameDTO {

    private Long createGameNo;

    @NotEmpty(message = "게임 이름을 입력 해 주세요.")
    private String createGameName;

    @NotEmpty(message = "게임 질문을 입력 해 주세요.")
    private String createQuestion;

    @NotEmpty(message = "게임 답변을 입력 해 주세요.")
    private String createAnswer;

    @NotEmpty(message = "게임 결과를 입력 해 주세요.")
    private String createResult;

}
