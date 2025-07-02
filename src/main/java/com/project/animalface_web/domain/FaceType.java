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
public class FaceType extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalNo;

    @ManyToOne
    @JoinColumn(name = "memberNo")
    private Member member;

    private String animalType;
    private Long animalAccuracy;
}

