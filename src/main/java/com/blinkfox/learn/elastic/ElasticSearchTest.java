package com.blinkfox.learn.elastic;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.pmw.tinylog.Logger;

/**
 * ElasticSearch测试类.
 * @author blinkfox on 2017-07-31.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ElasticSearchTest {

    private static final String HOST = "localhost";

    private static TransportClient client;

    /**
     * 初始化方法.
     */
    @BeforeClass
    public static void init() {
        InetAddress address;
        try {
            address = InetAddress.getByName(HOST);
        } catch (UnknownHostException e) {
            Logger.error("未获取对应地址的InetAddress实例.", e);
            return;
        }

        // 得到TransportClient实例.
        client  = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(address, 9300));
        Logger.info("初始化了TransportClient实例.");
    }

    /**
     * 测试方法.
     */
    @Test
    public void testAaaPut() {
        Map<String, Object> map = new HashMap<>();
        map.put("first_name", "李胃");
        map.put("last_name", "疼");
        map.put("age", 26);
        map.put("about", "他喜欢玩Dota");
        map.put("interests", new String[]{"游戏", "学习"});
        IndexResponse response = client.prepareIndex("megacorp", "employee")
                .setSource(map).get();
        String resp = response.toString();
        Logger.info("执行了添加索引的方法.结果为:\n{}", resp);
    }

    /**
     * 测试方法.
     */
    @Test
    public void testBbbQuery() {
        SearchResponse sr = client.prepareSearch()
                .setQuery(QueryBuilders.matchQuery("first_name", "胃"))
                .execute().actionGet();
        String resp = sr.toString();
        Logger.info("执行了测试的方法.结果为:\n{}", resp);
    }

    /**
     * 关闭client的方法.
     */
    @AfterClass
    public static void destroy() {
        if (client != null) {
            client.close();
            Logger.info("关闭了TransportClient实例.");
        }
    }

}