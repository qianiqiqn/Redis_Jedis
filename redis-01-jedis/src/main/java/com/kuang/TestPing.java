package com.kuang;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestPing {
    public static void main(String[] args) {

        //建立连接
        Jedis jedis=new Jedis("192.168.64.3",6379);

        jedis.flushDB();  //清空数据库的数据
        System.out.println(jedis.ping());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","wenqianqian");
         //开启事务
        Transaction multi = jedis.multi();
        String result= jsonObject.toJSONString();

        try{
            multi.set("user1",result);
            multi.set("user2",result);

           // int i = 1/0;

            multi.exec();  //执行事务
        }catch (Exception e){
            multi.discard(); //放弃事务
        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close(); //关闭连接
        }
    }
}
