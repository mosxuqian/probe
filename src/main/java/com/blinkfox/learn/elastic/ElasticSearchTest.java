package com.blinkfox.learn.elastic;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pmw.tinylog.Logger;

/**
 * ElasticSearch测试类.
 * @author blinkfox on 2017-07-31.
 */
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
    public void test() {
        SearchResponse sr = client.prepareSearch()
                .setQuery(QueryBuilders.matchQuery("first_name", "John"))
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