package com.project.animalface_web.repository;


import com.project.animalface_web.domain.CreateGame;
import com.project.animalface_web.domain.Notice;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class CreateGameRepositoryTest {

    @Autowired
    CreateGameRepository createGameRepository;

    @Test
    public void CreateGameInsertTest() {
        IntStream.rangeClosed(1,30).forEach(i->
                {
                    CreateGame createGame = CreateGame.builder()
                            .createGameName("생성게임 이름 더미" + i)
                            .createGameQuestion("생성게임 질문 더미" + i)
                            .createGameAnswer("생성게임 답변 더미" + i)
                            .createGameResult("생성게임 결과 더미" + i)
                            .build();

                    CreateGame result = createGameRepository.save(createGame);
                    log.info("추가한 getCreateGameNo : " + result.getCreateGameNo());
                }
        );
    }



} //Class
