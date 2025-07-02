package com.project.animalface_web.repository;

import com.project.animalface_web.domain.FaceType;
import com.project.animalface_web.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class FaceTypeRepositoryTest {
    @Autowired
    private FaceTypeRepository faceTypeRepository;

//    @Test
//    public void testInsert() {
//
//        Long memberNo = 1L;
//
//        Member member = Member.builder()
//                .memberNo(memberNo)
//                .build();
//
//        FaceType faceType=FaceType.builder()
//                .member(member)
//                .animalType("고양이상")
//                .animalImage("https://ibb.co/6grghdk")
//                .build();
//        FaceType result=faceTypeRepository.save(faceType);
//        log.info(result);
//    }












}//Class
