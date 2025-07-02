package com.project.animalface_web.repository;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

//import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
//@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testSaveAndFindById() {
        // Create a new Member entity
        Member member = new Member();
        member.setMemberId("testUser");
        member.setMemberPw("encodedPassword");
        member.setMemberName("Test User");

        // Save the member entity to the database
        Member savedMember = memberRepository.save(member);

        // Retrieve the member entity from the database
        Member foundMember = memberRepository.findById(savedMember.getMemberNo()).orElse(null);

        // Assert that the member entity is not null and its details are correct
        assertNotNull(foundMember);
        assertEquals("testUser", foundMember.getMemberId());
        assertEquals("Test User", foundMember.getMemberName());
    }

    @Test
    public void testDeleteById() {
     memberRepository.deleteByMemberId("asd1");

    }
}