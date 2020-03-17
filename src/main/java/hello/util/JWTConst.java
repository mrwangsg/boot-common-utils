package hello.util;

import com.auth0.jwt.algorithms.Algorithm;

/**
 * @创建人 sgwang
 * @name JWTConst
 * @user Anti
 * @创建时间 2020/3/17
 * @描述
 */
public class JWTConst {

    public final static String JWT_issuer = "sgwang"; // JWT的签发者
    public final static Long JWT_exp_time = 1L * 60L * 1000L; // 默认一分钟

    public final static String JWT_secret = "JWTUtils secret"; // 密钥字符串
    public final static Algorithm JWT_alg = Algorithm.HMAC512(JWTConst.JWT_secret); // 密钥算法

    public final static String JWT_msg_user_id = "user_id";  // 用户标识
    public final static String JWT_msg_username = "user_name"; // 用户名

}
