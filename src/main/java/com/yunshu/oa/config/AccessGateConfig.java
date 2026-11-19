package com.yunshu.oa.config;

import com.yunshu.oa.util.Result;
import com.yunshu.oa.util.JwtBox;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录态校验
 */
@Configuration
public class AccessGateConfig implements WebMvcConfigurer {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String uri = request.getRequestURI();
                if (uri.startsWith("/entry/") || uri.startsWith("/misc/") || uri.startsWith("/inner/")
                        || uri.startsWith("/vault/") || uri.startsWith("/portal/") || uri.startsWith("/open/")
                        || uri.startsWith("/node/") || uri.startsWith("/feed/") || uri.startsWith("/druid/")) {
                    return true;
                }
                if ("GET".equalsIgnoreCase(request.getMethod()) && !uri.contains(";") && uri.lastIndexOf('.') > uri.lastIndexOf('/')) {
                    return true;
                }
                String token = request.getHeader("Authorization");
                try {
                    Claims claims = JwtBox.parse(token);
                    request.setAttribute("uid", claims.get("uid"));
                    return true;
                } catch (Exception e) {
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(MAPPER.writeValueAsString(Result.of(401, "登录状态已失效")));
                    return false;
                }
            }
        }).addPathPatterns("/**");
    }
}
