package com.project.animalface_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

public class SessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setUseHttpOnlyCookie(true);
        serializer.setCookiePath("/");
        serializer.setCookieMaxAge(30 * 24 * 60 * 60);  // 30일
        return serializer;
    }
}
