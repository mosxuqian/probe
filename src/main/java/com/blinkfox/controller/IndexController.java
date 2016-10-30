package com.blinkfox.controller;

import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.core.ZealotParse;
import com.blinkfox.zealot.demo.DemoZealotConfig;
import com.jfinal.core.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * JFinal测试类
 * Created by blinkfox on 16/7/24.
 */
public class IndexController extends Controller {

    public void index() {
        renderText("Hello JFinal World!");
    }

    public void testZealot() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", "11");
        paramMap.put("bookName", "深入浅出MyBatis技术原理与实战");
        paramMap.put("bookIsbn", "9787121295942");
        // paramMap.put("author", "杨开振");
        paramMap.put("startMoney", "20.50");
        paramMap.put("endMmoney", "58.70");
        paramMap.put("offset", 2);
        paramMap.put("pageSize", 15);
        SqlInfo sqlInfo = ZealotParse.getSqlInfo(DemoZealotConfig.MY_TEST,
                "queryBookCondPaging", paramMap);
        renderJson(sqlInfo);
    }

}