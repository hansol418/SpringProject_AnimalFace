package com.project.animalface_web.security.filter;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

@Log4j2
public class APILoginFilter extends AbstractAuthenticationProcessingFilter {

    public APILoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        log.info("lsy APILoginFilter-----------------------------------");

        if (request.getMethod().equalsIgnoreCase("GET")) {
            log.info("GET METHOD NOT SUPPORTED");
            return null;
        }

        log.info("Request Method: " + request.getMethod());

        Map<String, String> jsonData = parseRequestJSON(request);

        log.info("Parsed JSON Data: " + jsonData);
        String memberId = jsonData.get("memberId");
        String memberPw = jsonData.get("memberPw");

        // 로그 찍기
        log.info("입력된 아이디: " + memberId);
        log.info("입력된 비밀번호: " + memberPw);

        // 인증 토큰 생성 시 변수 사용
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(memberId, memberPw);

        // 인증 시도
        return getAuthenticationManager().authenticate(authenticationToken);
    }


    private Map<String,String> parseRequestJSON(HttpServletRequest request) {


        try(Reader reader = new InputStreamReader(request.getInputStream())){

            Gson gson = new Gson();

            return gson.fromJson(reader, Map.class);

        }catch(Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error("Authentication failed: " + failed.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"message\": \"아이디 또는 비밀번호가 잘못되었습니다.\"}");
    }
}
