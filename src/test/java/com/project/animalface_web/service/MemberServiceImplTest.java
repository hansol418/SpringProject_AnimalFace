//package com.project.animalface_web.service;
//
//import com.project.animalface_web.domain.Member;
//import com.project.animalface_web.dto.MemberDTO;
//import com.project.animalface_web.repository.MemberRepository;
//import com.project.animalface_web.service.kmsserviece.MemberServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.test.annotation.Commit;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@Transactional // 각 테스트가 끝난 후 자동으로 롤백
//public class MemberServiceImplTest {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private MemberServiceImpl memberService;
//
//    private MemberDTO memberDTO;
//
//    @BeforeEach
//    public void setUp() {
//        memberDTO = MemberDTO.builder()
//                .memberId("testuser")
//                .memberPw("password")
//                .memberName("Test User")
//                .build();
//    }
//
//    @Test
//    @Commit
//    public void testSaveDto() {
//        // Given: MemberDTO 객체를 준비
//
//        // When: saveDto 메서드를 호출하여 저장
//        Member savedMember = memberService.saveDto(memberDTO);
//
//        // Then: 데이터베이스에 저장된 엔티티를 가져와서 검증
//        Member foundMember = memberRepository.findById(savedMember.getMemberNo()).orElse(null);
//
//        assertEquals(memberDTO.getMemberId(), foundMember.getMemberId());
//        assertEquals(memberDTO.getMemberPw(), foundMember.getMemberPw());
//        assertEquals(memberDTO.getMemberName(), foundMember.getMemberName());
//    }
//}