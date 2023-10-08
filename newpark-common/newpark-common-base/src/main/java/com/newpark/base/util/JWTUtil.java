package com.newpark.base.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.newpark.base.constant.Constant;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {



    /**
     * 生成Token
     *
     * @param userId          用户id
     * @param parentId        医院id
     * @return
     */
    public static String generateToken(Long userId, Long parentId) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(Constant.Token.SECRET_KEY);
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + Long.valueOf(Constant.Token.EXPIRE_TIME));
        String token = JWT.create()
                .withIssuer(Constant.Token.ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(expireTime)
                .withClaim(Constant.Token.CLAIM_USER, userId)
                .withClaim(Constant.Token.CLAIM_PARENT, parentId)
                .sign(algorithm);
        return token;
    }

    /**
     * 校验Token
     *
     * @param issuser   签发者
     * @param token     访问秘钥
     * @param secretKey 签名算法以及密匙
     * @return
     * @throws Exception
     */
    public static void verifyToken(String issuser, String token, String secretKey) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuser).build();
            jwtVerifier.verify(token);
        } catch (Exception ex) {
            throw new Exception("");
        }
    }

    /**
     * 从Token中提取用户信息
     *
     * @param token
     * @return userId 用户id
     */
    public static String getUserId(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        String userId = decodedJWT.getClaim(Constant.Token.CLAIM_USER).asString();
        return userId;
    }

    /**
     * 从Token中获取医院id
     *
     * @param token
     * @return parentId 机构id
     */
    public static String getParentId(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        String parentId = decodedJWT.getClaim(Constant.Token.CLAIM_PARENT).asString();
        return parentId;
    }


}

