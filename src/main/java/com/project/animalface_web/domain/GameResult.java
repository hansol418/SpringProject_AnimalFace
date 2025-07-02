package com.project.animalface_web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameResult extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultNo;

    private String resultText; // 결과에 대한 설명


    private int minScore; // 결과 적용 최소 점수
    private int maxScore; // 결과 적용 최대 점수
}

