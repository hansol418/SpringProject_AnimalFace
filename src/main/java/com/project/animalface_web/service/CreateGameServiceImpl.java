package com.project.animalface_web.service;

import com.project.animalface_web.domain.CreateGame;
import com.project.animalface_web.dto.CreateGameDTO;
import com.project.animalface_web.repository.CreateGameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor

@Transactional
public class CreateGameServiceImpl implements CreateGameService {

    private final CreateGameRepository createGameRepository;

    @Override
    public Long registerCreateGame(CreateGameDTO createGameDTO) {
        CreateGame createGame = dtoToEntity(createGameDTO);
        Long createGameNo = createGameRepository.save(createGame).getCreateGameNo();
        return createGameNo;
    }

    @Override
    public CreateGameDTO readCreateGame(Long createGameNo) {
        Optional<CreateGame> result = createGameRepository.findByCreateGameNo(createGameNo);
        CreateGame createGame = result.orElseThrow();
        CreateGameDTO createGameDTO = entityToDTO(createGame);

        return createGameDTO;
    }


}
