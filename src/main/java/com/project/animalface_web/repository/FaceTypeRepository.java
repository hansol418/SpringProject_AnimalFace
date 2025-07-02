package com.project.animalface_web.repository;

import com.project.animalface_web.domain.CreateGame;
import com.project.animalface_web.domain.FaceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaceTypeRepository extends JpaRepository<FaceType, Long> {

}
