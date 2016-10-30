package com.blinkfox.zealot.demo.servlet;

import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.core.ZealotParse;
import com.blinkfox.zealot.demo.DemoZealotConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by blinkfox on 2016/10/30.
 */
public class TestServlet extends HttpServlet {

    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Document mytest = MapperCache.getMappers().get(DemoZealotConfig.MY_TEST);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", "11");
        paramMap.put("bookName", "深入浅出MyBatis技术原理与实战");
        paramMap.put("bookIsbn", "9787121295942");
        // paramMap.put("author", "杨开振");
        paramMap.put("money", "58.70");
        paramMap.put("offset", 2);
        paramMap.put("pageSize", 15);
        SqlInfo sqlInfo = ZealotParse.getSqlInfo(DemoZealotConfig.MY_TEST,
                "queryBookCondPaging", paramMap);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the GET method");
        out.println("<br/>");
        out.println(sqlInfo.getSql());
        out.println("<br/>");
        out.println(sqlInfo.getParamsArr());
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

}