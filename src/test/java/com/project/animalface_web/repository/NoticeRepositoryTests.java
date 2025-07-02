package com.project.animalface_web.repository;

import com.project.animalface_web.domain.Notice;
import com.project.animalface_web.service.NoticeService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class NoticeRepositoryTests {
    @Autowired
    NoticeService noticeService;
    @Autowired
    NoticeRepository noticeRepository;


    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,10).forEach(i->
                {
                    Notice notice = Notice.builder()
                            .noticeNo((long) i)
                            .noticeName("공지사항 제목"+i)
                            .noticeContents("공지사항 내용"+i)
                            .date(LocalDate.now())
                            .build();


                    Notice result = noticeRepository.save(notice);
                    log.info("추가한 BNO : " + result.getNoticeNo());
                }
        );
    }


}
