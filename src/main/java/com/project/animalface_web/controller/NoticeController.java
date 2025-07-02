package com.project.animalface_web.controller;

import com.project.animalface_web.dto.NoticeDTO;
import com.project.animalface_web.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notice")
@Log4j2
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/list")
    public String list(Model model) {
        List<NoticeDTO> notices = noticeService.getNotices();
        model.addAttribute("noticesList", notices);
        return "notice/list";
    }

    @GetMapping("/read")
    public String read(@RequestParam("noticeNo") Long noticeNo, Model model) {
        NoticeDTO noticeDTO = noticeService.read(noticeNo);
        model.addAttribute("noticeDTO", noticeDTO);
        return "notice/read";
    }
}
