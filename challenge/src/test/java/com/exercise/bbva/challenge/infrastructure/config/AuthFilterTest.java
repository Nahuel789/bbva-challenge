package com.exercise.bbva.challenge.infrastructure.config;

import com.exercise.bbva.challenge.application.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthFilterTest {
    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthFilter authFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void filterInternal_withEmptyJWT_returnUserContextNull() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        authFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void filter_withInvalidJWT_returnUserContextNull() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("InvalidTokenFormat");

        authFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void filter_withValidToken_generateUserContext() throws Exception {
        String token = "valid.jwt.token";
        String username = "john.doe";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(authService.extractUsername(token)).thenReturn(username);

        authFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);

        var auth = SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(auth);
        assertEquals(username, auth.getPrincipal());
        assertInstanceOf(UsernamePasswordAuthenticationToken.class, auth);
    }

    @Test
    void filter_withoutUsername_returnUserContextNull() throws Exception {
        String token = "invalid.token";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(authService.extractUsername(token)).thenReturn(null);

        authFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void filter_withTokenValid_returnAlreadyAuthenticated() throws Exception {
        String token = "valid.token";
        String username = "john.doe";

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("already.authenticated", null, java.util.Collections.emptyList())
        );

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(authService.extractUsername(token)).thenReturn(username);

        authFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertEquals("already.authenticated", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}