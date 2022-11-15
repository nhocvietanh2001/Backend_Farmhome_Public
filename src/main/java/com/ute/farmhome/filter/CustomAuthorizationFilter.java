package com.ute.farmhome.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getServletPath();
        if(	uri.equals("/signin")
                || uri.startsWith("/refreshToken")
                || uri.equals("/api/checkPass")
                || uri.startsWith("/api/test")
                || uri.equals("/api/users")) {
            filterChain.doFilter(request, response); //all the above routing can access directly without decentralization
        }
        else {
            String authorizationHeader = request.getHeader("AUTHORIZATION");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("ute".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                    });
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (TokenExpiredException ex) {
                    response.setHeader("error", ex.getMessage());
                    response.setStatus(UNAUTHORIZED.value());
                    Map<String, String> maps = new HashMap<String, String>();
                    String error = "Token hết hạn";
                    maps.put("message", error);
                    maps.put("message system", ex.getMessage());
                    maps.put("httpCode", String.valueOf(UNAUTHORIZED.value()));
                    response.setContentType("APPLICATION_JSON_VALUE");//SET BODY JSON, NEU KHONG SET MAC DINH SE LA TEXT
                    new ObjectMapper().writeValue(response.getOutputStream(), maps);//GHI RA BODY
                } catch (Exception e) {
                    log.error("Error logging in: {}",e.getMessage());
                    response.setHeader("error", e.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> maps = new HashMap<String, String>();
                    String error = "Token lỗi";
                    maps.put("message", error);
                    maps.put("message system", e.getMessage());
                    maps.put("httpCode", String.valueOf(FORBIDDEN.value()));
                    response.setContentType("APPLICATION_JSON_VALUE");//SET BODY JSON, NEU KHONG SET MAC DINH SE LA TEXT
                    new ObjectMapper().writeValue(response.getOutputStream(), maps);//GHI RA BODY
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
