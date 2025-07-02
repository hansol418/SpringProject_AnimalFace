package com.project.animalface_web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class NoticeDTO {

    private Long noticeNo;

    @NotEmpty
    private String noticeName;

    @NotEmpty
    private String noticeContents;

    private LocalDate date;

}
