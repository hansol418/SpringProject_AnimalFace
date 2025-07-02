package com.project.animalface_web.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberGameResult;



    private boolean del;
    private boolean social;


    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();


    public void changePassword(String memberPw) {
        this.memberPw = memberPw;
    }
    public void addRole(MemberRole memberRole) {
        this.roleSet.add(memberRole);

    }
}

