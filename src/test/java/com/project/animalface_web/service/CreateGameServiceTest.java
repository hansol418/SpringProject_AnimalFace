package com.project.animalface_web.service;

import com.project.animalface_web.dto.CreateGameDTO;
import com.project.animalface_web.repository.CreateGameRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class CreateGameServiceTest {
    @Autowired
    CreateGameService createGameService;
    @Autowired
    CreateGameRepository createGameRepository;

    @Test
    public void createGameInsert() {
        CreateGameDTO createGameDTO = CreateGameDTO.builder()
                .createGameName("생성 게임 이름")
                .createResult("생성 게임 결과")
                .createQuestion("생성 게임 질문")
                .createAnswer("생성 게임 답변")
                .build();

        Long createGameNo = createGameService.registerCreateGame(createGameDTO);
        log.info("추가 후에 반환되는 번호 : "+ createGameNo);
    }

}//Class
