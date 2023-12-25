package dev.tugba.taskapp.auth.config.concretes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import dev.tugba.taskapp.auth.helper.Helper;
import dev.tugba.taskapp.entities.concretes.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  public String getToken(User user) {
      return getToken(new HashMap<>(), user);
  }

  private String getToken(Map<String,Object> extraClaims, User user) {
      return Jwts
          .builder()
          .claims(extraClaims)
          .claim("userId", user.getId())
          .claim(("firstname"), user.getFirstname())
          .claim("lastname", user.getLastname())
          .subject(user.getUsername())
          .issuedAt(new Date(System.currentTimeMillis()))
          .expiration(new Date(System.currentTimeMillis()+1000*60*24))
          .signWith(getKey())
          .compact();
  }

  private SecretKey getKey() {
     byte[] keyBytes=Decoders.BASE64.decode(Helper.generateSecretKey());
     return Keys.hmacShaKeyFor(keyBytes);
  }

  public String extractUsername(String token) {
      return getClaim(token, Claims::getSubject);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
      final String username=extractUsername(token);
      return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
  }

  public Claims getAllClaims(String token)
  {
      return Jwts
          .parser()
          .verifyWith(getKey())
          .build()
          .parseSignedClaims(token)
          .getPayload();
  }

  public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
  {
      final Claims claims=getAllClaims(token);
      return claimsResolver.apply(claims);
  }

  private Date getExpiration(String token)
  {
      return getClaim(token, Claims::getExpiration);
  }

  private boolean isTokenExpired(String token)
  {
      return getExpiration(token).before(new Date());
  }
  
}