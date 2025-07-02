package com.project.animalface_web.service;

import com.project.animalface_web.domain.Notice;
import com.project.animalface_web.dto.NoticeDTO;

import java.util.List;


public interface NoticeService {
    List<NoticeDTO> getNotices();
    NoticeDTO read(Long noticeNo);
    NoticeDTO update(Long noticeNo, NoticeDTO noticeDTO);
    boolean delete(Long noticeNo);
    NoticeDTO save(NoticeDTO noticeDTO);

    default Notice dtoToEntity(NoticeDTO noticeDTO) {
        Notice notice = Notice.builder()
                .noticeNo(noticeDTO.getNoticeNo())
                .noticeName(noticeDTO.getNoticeName())
                .noticeContents(noticeDTO.getNoticeContents())
                .date(noticeDTO.getDate())
                .build();
        return notice;
    }

    default NoticeDTO entityToDto(Notice notice) {
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .noticeNo(notice.getNoticeNo())
                .noticeName(notice.getNoticeName())
                .noticeContents(notice.getNoticeContents())
                .date(notice.getDate())
                .build();
        return noticeDTO;
    }
}
