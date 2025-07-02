package com.project.animalface_web.controller.kmscontroller;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/member")
@Log4j2
public class MemberRestController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllUsers() {
        return memberService.getAllUsers();
    }

    @GetMapping("/{memberNo}")
    public ResponseEntity<Member> getUserById(@PathVariable Long memberNo) {
        Optional<Member> user = memberService.getUserById(memberNo);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Member user) {
        try {
            Member createdUser = memberService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            // 중복된 아이디가 있을 때
            log.error("아이디 중복 체크 오류: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("회원가입 중 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{memberNo}/update")
    public ResponseEntity<Member> updateUser(
            @PathVariable Long memberNo,
            @RequestBody Member user) {
        try {
            Member updatedUser = memberService.updateUser(memberNo, user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            log.error("회원정보 수정 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token,@PathVariable String memberId) {
        try {
            log.info("lsy 1 controller memberId : " + memberId);
            memberService.deleteMemberById(memberId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("회원 삭제 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/check-duplicate")
    public ResponseEntity<Boolean> checkDuplicate(@RequestParam String memberId) {
        boolean isDuplicate = memberService.isMemberIdDuplicate(memberId);
        return ResponseEntity.ok(isDuplicate);
    }
}
