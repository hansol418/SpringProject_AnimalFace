package com.project.animalface_web.service;

import com.project.animalface_web.domain.Notice;
import com.project.animalface_web.dto.NoticeDTO;
import com.project.animalface_web.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor

public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<NoticeDTO> getNotices() {
        List<Notice> notices = noticeRepository.findAll();
        return notices.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NoticeDTO read(Long noticeNo) {
        Optional<Notice> result = noticeRepository.findById(noticeNo);
        Notice notice = result.orElseThrow();
        return modelMapper.map(notice, NoticeDTO.class);
    }

    @Override
    public NoticeDTO update(Long noticeNo, NoticeDTO noticeDTO) {
        Optional<Notice> optionalNotice = noticeRepository.findById(noticeNo);
        if (optionalNotice.isPresent()) {
            Notice notice = optionalNotice.get();
            notice.setNoticeName(noticeDTO.getNoticeName());
            notice.setNoticeContents(noticeDTO.getNoticeContents());
            notice.setDate(noticeDTO.getDate());

            Notice updatedNotice = noticeRepository.save(notice);
            return entityToDto(updatedNotice);
        }
        return null;
    }

    @Override
    public boolean delete(Long noticeNo) {
        noticeRepository.deleteById(noticeNo);
        return false;
    }

    @Override
    public NoticeDTO save(NoticeDTO noticeDTO) {
        Notice notice = new Notice();
        notice.setNoticeName(noticeDTO.getNoticeName());
        notice.setNoticeContents(noticeDTO.getNoticeContents());
        notice.setDate(noticeDTO.getDate());

        Notice savedNotice = noticeRepository.save(notice);
        return entityToDto(savedNotice);
    }
}
