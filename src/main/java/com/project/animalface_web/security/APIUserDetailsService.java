package com.project.animalface_web.security;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.dto.APIUserDTO;
import com.project.animalface_web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class APIUserDetailsService implements UserDetailsService {


    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {


        Optional<Member> result = memberRepository.findByMemberId(memberId);


        log.info("Received memberId for authentication: " + memberId);
        log.info("APIUserDetailsService - loadUserByUsername method called with memberId: " + memberId);
        Member apiUser = result.orElseThrow(() -> new UsernameNotFoundException("Cannot find mid"));



        log.info("APIUserDetailsService - Found user: " + apiUser);


        List<SimpleGrantedAuthority> authorities = apiUser.getRoleSet().stream()
                .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                .collect(Collectors.toList());


        APIUserDTO dto = new APIUserDTO(
                apiUser.getMemberNo(),
                apiUser.getMemberId(),
                apiUser.getMemberPw(),
                apiUser.getMemberName(),
                apiUser.getRoleSet().stream().map(
                        memberRole -> new SimpleGrantedAuthority("ROLE_"+ memberRole.name())
                ).collect(Collectors.toList()));


        log.info("Created APIUserDTO with memberNo: " + dto.getMemberNo());
        log.info("lsy dto : "+dto);


        return dto;
    }
}