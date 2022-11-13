package com.ute.farmhome.utility;

import com.ute.farmhome.service.implement.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private String jwtSecret = "secret";
    public String generateJwtToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", authentication.getAuthorities().stream().map(item -> new SimpleGrantedAuthority(item.getAuthority())).collect(Collectors.toList()));
        return Jwts.builder()
                .setClaims(claims).setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 7 * 24 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, "secret").compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    // lấy role từ token
    public List<Map<String, String>> getRolesFromToken(String token) {
        // ví dụ:
        // "roles": [
        //      {
        //          "authority" : "Role_Admin"
        //      }
        // ]
        return (List<Map<String, String>>) Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("roles");
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {

        }
        return false;

    }
}
