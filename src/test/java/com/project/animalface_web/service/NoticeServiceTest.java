package com.project.animalface_web.service;


import com.project.animalface_web.dto.NoticeDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2

public class NoticeServiceTest {

    @Autowired
    NoticeService noticeService;

    @Test
    public void testRead() {
        NoticeDTO noticeDTO = noticeService.read(10L);
        log.info("하나 조회 noticeDTO : "+noticeDTO);
    }

    @Test
    public void testUpdate() {
        Long noticeNo = 10L;

        NoticeDTO noticeDTO = NoticeDTO.builder()
                .noticeName("공지사항 제목 수정")
                .noticeContents("공지사항 내용 수정")
                .date(LocalDate.now())
                .build();

        NoticeDTO updatedNotice = noticeService.update(noticeNo, noticeDTO);
    }

    @Test
    public void testDelete() {
        noticeService.delete(5L);
    }
}
