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
public class GameAnswer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerNo;

    private String answerText;

    private int score; // 각 답변에 할당된 점수

    @ManyToOne
    @JoinColumn(name = "question_no")
    private GameQuestion question;

}