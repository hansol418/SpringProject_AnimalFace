package com.project.animalface_web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@ToString

public class APIUserDTO extends User {
    private Long memberNo;
    private String memberName;
    private String memberPw;
    private String memberId;


    public APIUserDTO(Long memberNo, String memberId, String memberPw, String memberName, Collection<GrantedAuthority> authorities) {
        super(memberId, memberPw, authorities);
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
    }

}