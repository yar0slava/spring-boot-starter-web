package kma.topic3.webstarter.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kma.topic3.webstarter.service.JwtTokenGenerator;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenGenerator jwtTokenGenerator;

    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final FilterChain filterChain)
            throws ServletException, IOException {

        final String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(token)) {
            System.out.println("anonymous request to endpoint " + httpServletRequest.getMethod() + " " + getRequestPath(httpServletRequest));
            filterChain.doFilter(httpServletRequest, httpServletResponse);      // <--- important
            return;
        }

        final String username = jwtTokenGenerator.getUsernameFromToken(token);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        System.out.println("user " + username + " send request to endpoint " + httpServletRequest.getMethod() + " " + getRequestPath(httpServletRequest));
        filterChain.doFilter(httpServletRequest, httpServletResponse);          // <--- important
    }

    private static String getRequestPath(final HttpServletRequest request) {
        return request.getRequestURI() + "?" + request.getQueryString();
    }
}
