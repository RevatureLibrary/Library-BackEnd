package com.library.util;

import com.library.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.function.Function;


public enum JWTUtil {
    ;
    private final static String SECRET = "8Zz5tw0Ionm3XPZZfN0NOml3WillvR9fp6ryDIoGRM8EPHAB6iHsc0fb" ;

    public static String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public static Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public static SimpleGrantedAuthority extractAuthority(String token) {
        return new SimpleGrantedAuthority(extractAllClaims(token).get("auth",String.class));
    }

    public static String extractCredentials(String token) { return (String) extractAllClaims(token).get("password");}


    private static <T> T extractClaim(java.lang.String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private static Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    private static Boolean isTokenExpired(String token){return extractExpiration(token).before(new Date());}

    public static String generateToken(User user){
        Map<String,Object> claims = createClaims(user);
        return createToken(claims,user.getUsername());
    }

    private static Map<String, Object> createClaims(User user) {
        HashMap<String,Object> claims = new HashMap<>();
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("password",user.getPassword());
        claims.put("auth", user.getAccountType().toString());;
        return claims;
    }

    private static String createToken(Map<String,Object> claims, String username) {
        return Jwts.builder().setClaims(claims)
                .setSubject(username)
                . setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +1000*60*60*8))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();
    }

    public static Boolean validateToken(String token){
        final String username = extractUsername(token);
        return username!= null
                && !isTokenExpired(token);
    }
}
