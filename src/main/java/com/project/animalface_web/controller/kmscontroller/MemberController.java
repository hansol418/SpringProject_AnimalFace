package com.project.animalface_web.controller.kmscontroller;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.dto.APIUserDTO;
import com.project.animalface_web.dto.MemberDTO;
import com.project.animalface_web.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/member")
@Log4j2
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/main")
    public void getAllUsers(@AuthenticationPrincipal UserDetails user, Model model) {
        List<Member> users = memberService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", user);
    }

    @GetMapping("/login")
    public void showLoginUserForm() {

    }

    @GetMapping("/register")
    public void showCreateUserForm(@AuthenticationPrincipal UserDetails user, Model model) {

        model.addAttribute("user", user);
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute Member user) {
        log.info("lsy User created" + user);

            user.setMemberPw(bCryptPasswordEncoder.encode(user.getMemberPw()));

            Member savedUser = memberService.createUser(user);

        return "redirect:/member/login";

    }

    @GetMapping("/profile/edit")
    public String showEditProfilePage(@AuthenticationPrincipal APIUserDTO user, Model model) {
        Long memberNo = user.getMemberNo();
        Optional<MemberDTO> memberDTOOptional = memberService.getMemberDTOById(memberNo);
        if (memberDTOOptional.isPresent()) {
            model.addAttribute("user", memberDTOOptional.get());
        } else {
            log.error("User not found with memberNo: " + memberNo);
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        return "member/profile-edit";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute MemberDTO updatedMember, @AuthenticationPrincipal APIUserDTO user) {
        Long memberNo = user.getMemberNo();

        // 사용자 정보를 업데이트
        MemberDTO currentMember = memberService.getMemberDTOById(memberNo)
                .orElseThrow(() -> new RuntimeException("User not found"));

        currentMember.setMemberName(updatedMember.getMemberName());

        memberService.updateMember(memberNo, currentMember);

        return "redirect:/member/profile";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName();

        if (memberId != null && !memberId.isEmpty()) {
            // MemberService에서 Optional<Member>를 반환하도록 수정한 후, Optional에서 Member를 가져옵니다.
            Member member = memberService.getMemberById(memberId).orElse(null);

            if (member != null) {
                model.addAttribute("memberId", member.getMemberId());
                model.addAttribute("memberName", member.getMemberName());
            } else {
                model.addAttribute("error", "No member found with the provided ID.");
            }
        } else {
            model.addAttribute("error", "No authenticated user found.");
        }

        return "profile"; // profile.html 템플릿을 반환
    }

    @PostMapping("/delete")
    public String deleteMember(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            // DB에서 회원 정보 삭제
            memberService.deleteMemberById(username);
            SecurityContextHolder.clearContext(); // 로그아웃 처리
            return "redirect:/member/login?deleteSuccess"; // 탈퇴 후 로그인 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "No authenticated user found");
            return "profile";
        }
    }
}
