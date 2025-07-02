package com.project.animalface_web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGame extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long createGameNo;

    @Column(length = 50, nullable = false)
    private String createGameName;

    @Column(length = 2000, nullable = false)
    private String createGameQuestion;

    @Column(length = 500,nullable = false)
    private String createGameAnswer;

    @Column(length = 500,nullable = false)
    private String createGameResult;
}

