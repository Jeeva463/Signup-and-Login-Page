package com.example.jwt.util;

import java.sql.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.jwt.entity.Signup;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JWT {
	private static String secretkey = "It a secretkey";
	private static long expirydurition = 60 * 60;
	
	public String  generated(Signup signup) {
		long millistime = System.currentTimeMillis();
	long expirytime = millistime + expirydurition * 1000;
	
	 Date issuedat = new Date(millistime);
	 Date expiryat = new Date(expirytime);
	 UUID uuid = signup.getId();
	 String uui = uuid.toString();
	 
	 //Claims claims = JWT.claims().
	 Claims claims = Jwts.claims().setIssuer(uui).setIssuedAt(issuedat).setExpiration(expiryat);
	 claims.put("name", signup.getName());
	 claims.put("email", signup.getEmail());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secretkey).compact();
     
	 
	}

	public void verify(String authorization) throws Exception {
		try {
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(authorization);
		} catch (Exception e) {
			throw new Exception();
		}
	}

}