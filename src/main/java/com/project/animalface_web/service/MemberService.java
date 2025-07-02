package com.project.animalface_web.service;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.domain.MemberRole;
import com.project.animalface_web.dto.MemberDTO;
import com.project.animalface_web.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Log4j2
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Member> getAllUsers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getUserById(Long memberNo) {
        return memberRepository.findById(memberNo);
    }

    public boolean isMemberIdDuplicate(String memberId) {
        return memberRepository.findByMemberId(memberId).isPresent();
    }

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public Member createUser(Member user) {
        if (isMemberIdDuplicate(user.getMemberId())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        String encodedPassword = passwordEncoder.encode(user.getMemberPw());
        user.setMemberPw(encodedPassword);
        user.addRole(MemberRole.USER);
        return memberRepository.save(user);
    }

    public Member updateUser(Long id, Member userDetails) {
        Member user = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setMemberName(userDetails.getMemberName());
        user.setMemberId(userDetails.getMemberId());

        return memberRepository.save(user);
    }

    public void deleteMemberById(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if (member.isPresent()) {
            memberRepository.delete(member.get());
            memberRepository.flush(); // 변경 사항을 DB에 강제로 반영
            log.info("회원탈퇴 성공: " + memberId);
        } else {
            log.error("회원 정보를 찾을 수 없습니다: " + memberId);
            throw new IllegalArgumentException("회원 정보를 찾을 수 없습니다.");
        }
    }

    public Optional<MemberDTO> getMemberDTOById(Long memberNo) {
        return memberRepository.findById(memberNo)
                .map(member -> new MemberDTO(
                        member.getMemberNo(),
                        member.getMemberId(),
                        member.getMemberPw(),
                        member.getMemberName(),
                        member.getMemberGameResult()
                ));
    }

    public void updateMember(Long memberNo, MemberDTO updatedMember) {
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new RuntimeException("User not found"));

        member.setMemberName(updatedMember.getMemberName());
        memberRepository.save(member);
    }

}

