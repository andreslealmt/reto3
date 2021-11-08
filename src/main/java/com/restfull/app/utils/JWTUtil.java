package com.restfull.app.utils;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${security.jwt.secret}")
	private String key;
	
	@Value("${security.jwt.issuer}")
	private String issuer;
	
	@Value("${security.jwt.ttlMillis}")
	private Long ttlMillis;
	
	private final Logger log = LoggerFactory.getLogger(JWTUtil.class);
	
	
	public String create(String id, String subject) {
		//algoritmo para firmar el token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		Long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		//firmar el jwt con nuestra apikey secreta
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
		Key singningKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		
		//enviar la jwt pedida
		JwtBuilder jwtBuilder = Jwts.builder().setId(id)
				.setIssuedAt(now).setSubject(subject)
				.setIssuer(issuer).signWith(signatureAlgorithm, singningKey);
		
		if(ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			jwtBuilder.setExpiration(exp);
		}
		
		// construir el jwt y serializarlo para compactarlo, url_safe string
		return jwtBuilder.compact();		
	}
	
	
	
	public String getValue(String jwt) {
		// esta linea lanzara una excepcion si no esta firmado el jwt como se esperaba
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
				.parseClaimsJws(jwt).getBody();
		return claims.getSubject();
	}
	
	
	public String getKey(String jwt) {
		// esta linea lanzara una excepcion si no esta firmado el jwt como se esperaba
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
				.parseClaimsJws(jwt).getBody();
		return claims.getId();
	}
	
	
	
	
	
	
	
	
}










