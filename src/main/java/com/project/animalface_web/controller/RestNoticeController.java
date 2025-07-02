package com.project.animalface_web.controller;

import com.project.animalface_web.dto.NoticeDTO;
import com.project.animalface_web.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
@Log4j2
@RequiredArgsConstructor
public class RestNoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<List<NoticeDTO>> list() {
        List<NoticeDTO> notices = noticeService.getNotices();
        return ResponseEntity.ok(notices);
    }

    @GetMapping("/{noticeNo}")
    public ResponseEntity<NoticeDTO> read(@PathVariable("noticeNo") Long noticeNo) {
        NoticeDTO noticeDTO = noticeService.read(noticeNo);
        if (noticeDTO != null) {
            return ResponseEntity.ok(noticeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<NoticeDTO> create(@RequestBody NoticeDTO noticeDTO) {
        NoticeDTO createdNotice = noticeService.save(noticeDTO);
        return ResponseEntity.status(201).body(createdNotice);
    }

    @PutMapping("/{noticeNo}")
    public ResponseEntity<NoticeDTO> update(
            @PathVariable("noticeNo") Long noticeNo,
            @RequestBody NoticeDTO noticeDTO) {

        NoticeDTO updatedNotice = noticeService.update(noticeNo, noticeDTO);
        if (updatedNotice != null) {
            return ResponseEntity.ok(updatedNotice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<Void> delete(@PathVariable("noticeNo") Long noticeNo) {
        boolean deleted = noticeService.delete(noticeNo);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
