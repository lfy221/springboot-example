package com.neu.java.spring.springboot.jwt.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Auth0工具类，根据博客文章修改
 * 博客参考地址：https://blog.csdn.net/u014204541/article/details/103906208
 * github:  https://github.com/auth0/java-jwt
 */
public class JwtAuth0Utils {
    //过期时间 30分钟
    private static final long EXPIRE_TIME = 2 * 60 * 1000;

    /**
     * 生成签名
     * 根据内部改造，支持6中类型，Integer,Long,Boolean,Double,String,Date
     * @param map
     * @return
     */
    public static String sign(String tokenSecret, Map<String,Object> map) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "jwt");
            // 返回token字符串
            JWTCreator.Builder builder = JWT.create()
                    .withHeader(header)
                    .withIssuedAt(new Date()) //发证时间
                    .withExpiresAt(date);  //过期时间
            //   .sign(algorithm);  //密钥
            // map.entrySet().forEach(entry -> builder.withClaim( entry.getKey(),entry.getValue()));
            map.entrySet().forEach(entry -> {
                if (entry.getValue() instanceof Integer) {
                    builder.withClaim( entry.getKey(),(Integer)entry.getValue());
                } else if (entry.getValue() instanceof Long) {
                    builder.withClaim( entry.getKey(),(Long)entry.getValue());
                } else if (entry.getValue() instanceof Boolean) {
                    builder.withClaim( entry.getKey(),(Boolean) entry.getValue());
                } else if (entry.getValue() instanceof String) {
                    builder.withClaim( entry.getKey(),String.valueOf(entry.getValue()));
                } else if (entry.getValue() instanceof Double) {
                    builder.withClaim( entry.getKey(),(Double)entry.getValue());
                } else if (entry.getValue() instanceof Date) {
                    builder.withClaim( entry.getKey(),(Date)entry.getValue());
                }
            });
            return builder.sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     * @param **token**
     * @return
     */
    public static boolean verify(String tokenSecret, String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);

            return true;
        } catch (Exception e){
//            e.printStackTrace();
            throw e;
        }
    }

    /**
     *获取用户自定义Claim集合
     * @param token
     * @return
     */
    public static Map<String, Claim> getClaims(String tokenSecret, String token){
        Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        Map<String, Claim> jwt = verifier.verify(token).getClaims();
        return jwt;
    }

    /**
     * 获取过期时间
     * @param token
     * @return
     */
    public static Date getExpiresAt(String tokenSecret, String token){
        Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
        return  JWT.require(algorithm).build().verify(token).getExpiresAt();
    }

    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedAt(String tokenSecret, String token){
        Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
        return JWT.require(algorithm).build().verify(token).getIssuedAt();
    }

    /**
     * 验证token是否失效
     *
     * @param token
     * @return true:过期   false:没过期
     */
    public static boolean isExpired(String tokenSecret, String token) {
        try {
            final Date expiration = getExpiresAt(tokenSecret, token);
            return expiration.before(new Date());
        }catch (TokenExpiredException e) {
            return true;
        }
    }

    /**
     * 直接Base64解密获取header内容
     * @param token
     * @return
     */
    public static String getHeaderByBase64(String token){
        if (token == null || "".equals(token)){
            return null;
        }else {
            byte[] payloadByte = Base64.getDecoder().decode(token.split("\\.")[0]);
            String header = new String(payloadByte);
            return header;
        }

    }

    /**
     * 直接Base64解密获取payload内容
     * @param token
     * @return
     */
    public static String getPayloadByBase64(String token){

        if (token == null || "".equals(token)){
            return null;
        }else {
            byte[] payloadByte = Base64.getDecoder().decode(token.split("\\.")[1]);
            String payload = new String(payloadByte);
            return payload;
        }

    }
    public static void main(String[] args) {
        //私钥
        String TOKEN_SECRET = "fa09fecc568d4f1a97ae2b04ab95aaaa";
        Map<String,Object> map = new HashMap<>();
        map.put("userId","123456");
        map.put("rose","admin");
        map.put("integer",1111);
        map.put("double",112.222);
        map.put("Long",112L);
        map.put("bool",true);
        map.put("date",new Date());
        String token = sign(TOKEN_SECRET, map); //生成token
        System.out.println(verify(TOKEN_SECRET, token));//验证token是否正确
        String dd = getClaims(TOKEN_SECRET, token).get("userId").asString(); //使用方法
        System.out.println(dd);
        System.out.println("获取签发token时间：" +getIssuedAt(TOKEN_SECRET, token));
        System.out.println("获取过期时间："+getExpiresAt(TOKEN_SECRET, token));
        // Thread.sleep(1000*40);
        System.out.println("检查是否已过期："+isExpired(TOKEN_SECRET, token));
        System.out.println("获取头"+getHeaderByBase64(token));
        System.out.println("获取负荷"+getPayloadByBase64(token));
    }
}
