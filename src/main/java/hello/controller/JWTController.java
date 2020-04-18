package hello.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import hello.pojo.User;
import hello.util.JWTUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/**
 * @创建人 sgwang
 * @name JWTController
 * @user Anti
 * @创建时间 2020/4/14
 * @描述
 */
@RestController
@RequestMapping(value = "/jwt")
public class JWTController {

    @GetMapping("/index")
    public String get(@RequestBody JSONObject params) {

        System.err.println("params: " + params.toJSONString());

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername("wang shi guang");
        user.setPassword("123456");

        String token = "";

        try {
            token = JWTUtils.getAccessToken(user);
            System.out.println("Bearer " + token);
        } catch (JWTCreationException e) {
            System.err.println(e.getMessage());
        }

        return "Bearer " + token;
    }

    @PostMapping("/index")
    public String post(@RequestBody JSONObject params) {
        String token = params.getString("token");
        if (token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }

        try {
            DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
            for (Map.Entry<String, Claim> temp : decodedJWT.getClaims().entrySet()) {
                System.out.println("key: " + temp.getKey() + "  value: " + (temp.getValue().asString() == null ? temp.getValue().asDate() : temp.getValue().asString()));
            }

            // 空值判断
            System.err.println(decodedJWT.getClaim("null").isNull());
            return "success";
        } catch (JWTVerificationException e) {
            // 超时报错： "The Token has expired on Tue Mar 17 22:31:44 CST 2020."
            System.err.println(" JWTVerificationException: " + e.getMessage());
        }

        return "fail";
    }

}
