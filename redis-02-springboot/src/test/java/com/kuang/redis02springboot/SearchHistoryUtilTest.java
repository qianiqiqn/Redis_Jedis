package com.kuang.redis02springboot;

import com.kuang.redis02springboot.util.SearchHistoryUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SearchHistoryUtilTest {

    @Autowired
    private SearchHistoryUtil searchHistoryUtil;

    @Test
    public void test1() {

        searchHistoryUtil.saveSearchHistory(7, "java");
        searchHistoryUtil.saveSearchHistory(7, "c++");
        searchHistoryUtil.saveSearchHistory(7, "java web");
        searchHistoryUtil.saveSearchHistory(7, "python");
        searchHistoryUtil.saveSearchHistory(7, "c#");
        searchHistoryUtil.saveSearchHistory(7, "c");
        searchHistoryUtil.saveSearchHistory(7, "scala");
        searchHistoryUtil.saveSearchHistory(7, "spark");
        searchHistoryUtil.saveSearchHistory(7, "Vue");

    }

    @Test
    public void test2() {
        searchHistoryUtil.removeSearchHistory(7);
    }

    @Test
    public void test3() {
        List<String> result = searchHistoryUtil.getSearchHistory(7);
        result.forEach(a->{
            System.out.println(a);
        });
    }


}
