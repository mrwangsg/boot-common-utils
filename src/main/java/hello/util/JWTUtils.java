package hello.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import hello.pojo.User;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @创建人 sgwang
 * @name JWTUtils
 * @user Anti
 * @创建时间 2020/3/17
 * @描述
 */
public class JWTUtils {

    /**
     * @param user
     * @return String
     */
    public static String getAccessToken(User user) throws JWTCreationException {
        String jwtId = UUID.randomUUID().toString();
        long nowTime = System.currentTimeMillis();
        Date issuedAt = new Date(nowTime);
        Date expiresAt = new Date(nowTime + JWTConst.JWT_exp_time);

        return JWT.create()
                .withJWTId(jwtId) //JWT的唯一身份标识
                .withIssuer(JWTConst.JWT_issuer) // JWT的签发者
                .withIssuedAt(issuedAt) // 该JWT签发的时间
                .withExpiresAt(expiresAt) // JWT的过期时间
                .withClaim(JWTConst.JWT_msg_user_id, user.getUserId())
                .withClaim(JWTConst.JWT_msg_username, user.getUsername())
                .sign(JWTConst.JWT_alg);
    }

    /**
     * @param token
     * @return DecodedJWT
     * @throws JWTVerificationException
     */
    public static DecodedJWT verifyToken(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(JWTConst.JWT_alg).build();
        return jwtVerifier.verify(token);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername("wang shi guang");
        user.setPassword("123456");

        String token = "";

        try {
            token = JWTUtils.getAccessToken(user);
            System.out.println("token: " + token);
        } catch (JWTCreationException e) {
            System.err.println(e.getMessage());
        }

        try {
            DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
            for (Map.Entry<String, Claim> temp : decodedJWT.getClaims().entrySet()) {
                System.out.println("key: " + temp.getKey() + "  value: " + (temp.getValue().asString() == null ? temp.getValue().asDate() : temp.getValue().asString()));
            }

            // 空值判断
            System.err.println(decodedJWT.getClaim("null").isNull());
        } catch (JWTVerificationException e) {
            // 超时报错： "The Token has expired on Tue Mar 17 22:31:44 CST 2020."
            System.err.println(" JWTVerificationException: " + e.getMessage());
        }

    }
}
