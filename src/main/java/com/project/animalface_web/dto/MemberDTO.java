package com.project.animalface_web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long memberNo;

    @NotEmpty(message = "아이디를 입력 해 주세요.")
    private String memberId;

    @NotEmpty(message = "비밀번호를 입력 해 주세요.")
    @Length(min=6, max=16, message = "비밀번호는 6자 이상, 16자 이하로 입력해주세요")
    private String memberPw;

    @NotEmpty(message = "닉네임을 입력 해 주세요.")
    private String memberName;

    private String memberGameResult;


}
