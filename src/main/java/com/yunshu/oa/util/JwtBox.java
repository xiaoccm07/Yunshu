package com.yunshu.oa.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 登录令牌签发与解析
 */
public class JwtBox {

    private static final String SECRET = "yunshu123";

    public static String issue(Long uid, String account) {
        return Jwts.builder()
                .setSubject(account)
                .claim("uid", uid)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
