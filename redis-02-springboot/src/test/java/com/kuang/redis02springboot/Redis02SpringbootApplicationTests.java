package com.kuang.redis02springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kuang.redis02springboot.pojo.User;
import com.kuang.redis02springboot.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class Redis02SpringbootApplicationTests<RedisTemplateMy> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1(){
        redisUtil.set("name","温纤纤");
        System.out.println(redisUtil.get("name"));
    }


    @Test
    void contextLoads() {

        // opsForValue 操作字符串， 类似 String
        // opsForList 操作 List， 类似 List
        // opsForSet
        // opsForHash
        // opsForZSet
        // opsForGeo
        // opsForHyperLogLog

        //获取 redis的连接对象
//        RedisConnection connection= redisTemplateMy.getConnectionFactory().getConnection();
//        connection.flushDb();
        redisTemplate.opsForValue().set("key1","wenqianqian222");
        System.out.println(redisTemplate.opsForValue().get("key1"));

    }

    @Test
    public void test() throws JsonProcessingException {
        //真实开发一般使用json来传递对象
        User user=new User("温芊芊",18);
        //转换为json格式
        //String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));
        System.out.println(redisTemplate.opsForList().leftPop("wenqianqian7"));

    }


}
