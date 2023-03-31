package com.kuang.redis02springboot.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author wenqianqian
 * @project_name 增加，查询，删除搜索词记录到redis工具类
 */
@Component
public class SearchHistoryUtil {

    private final String HISTORY_K = "wenqianqian";

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;
    /**
     *@描述 增加搜索记录
     *@参数 uid：参与搜索的用户id，searchTerm：搜索词
     *@返回值 void
     */
    public void saveSearchHistory(Integer uid, String searchTerm) {

        String saveKey = HISTORY_K + uid.toString();

        Jedis jedis = new Jedis(redisHost, redisPort);

        // 移除和搜索词相等的记录
        jedis.lrem(saveKey, 0, searchTerm);
        // 添加关键词到redis中，并放在了第一位，方便展示,如果使用rpush(),放在最后
        jedis.lpush(saveKey,searchTerm);
//         jedis.rpush(saveKey,searchTerm);

        //只保存8个值
        jedis.ltrim(saveKey, 0, 5);

    }

    /**
     *@描述 获取只保存的8个搜索记录
     *@参数 uid：用户id
     *@返回值 搜索记录List集合
     */
    public List<String> getSearchHistory(Integer uid) {

        String saveKey = HISTORY_K + uid.toString();
        List<String> list;

        Jedis jedis = new Jedis(redisHost, redisPort);
        list = jedis.lrange(saveKey, 0, 7);

        return list;
    }

    /**
     *@描述 删除搜索记录
     *@参数 uid：用户id
     *@返回值 void
     */
    public void removeSearchHistory(Integer uid) {

        String saveKey = HISTORY_K + uid.toString();

        Jedis jedis = new Jedis(redisHost, redisPort);
        jedis.del(saveKey);

    }
}
