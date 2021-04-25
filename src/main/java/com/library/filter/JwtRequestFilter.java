package com.library.filter;

import com.library.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (!httpServletRequest.getMethod().equals("OPTIONS")) {

            final String authorizeHeader = httpServletRequest.getHeader("Authorization");

            String jwt;

            if (authorizeHeader != null && authorizeHeader.startsWith("Bearer ")) {
                jwt = authorizeHeader.substring(7);
                try {
                    if (JWTUtil.validateToken(jwt)) {
                        SecurityContextHolder.getContext()
                                .setAuthentication(new UsernamePasswordAuthenticationToken(
                                        JWTUtil.extractUsername(jwt),
                                        null,
                                        Collections.singleton(JWTUtil.extractAuthority(jwt))
                                ));
                    }

                } catch (ExpiredJwtException e) {
                    httpServletResponse.sendError(403, "Login Expired");
                    return;
                }
            }
        }
       // httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
