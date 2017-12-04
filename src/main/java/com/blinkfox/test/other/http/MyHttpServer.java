package com.blinkfox.test.other.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * 自定义的http服务器.
 *
 * @author blinkfox on 2017-12-04.
 */
public class MyHttpServer {

    /**
     * 启动服务，监听来自客户端的请求.
     *
     * @throws IOException IO异常
     */
    private static void httpserverService() throws IOException {
        HttpServerProvider provider = HttpServerProvider.provider();
        HttpServer httpserver = provider.createHttpServer(new InetSocketAddress(8888), 200); // 监听端口8888,能同时接受100个请求
        httpserver.createContext("/mytest", new MyHttpHandler());
        httpserver.setExecutor(null);
        httpserver.start();
        System.out.println("server started");
    }

    /**
     * Http请求处理类.
     */
    private static class MyHttpHandler implements HttpHandler {

        public void handle(HttpExchange httpExchange) throws IOException {
            String responseMsg = "ok"; //响应信息
            InputStream in = httpExchange.getRequestBody(); //获得输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String temp = null;
            while((temp = reader.readLine()) != null) {
                System.out.println("client request:" + temp);
            }
            httpExchange.sendResponseHeaders(200, responseMsg.length()); //设置响应头属性及响应信息的长度
            OutputStream out = httpExchange.getResponseBody();  //获得输出流
            out.write(responseMsg.getBytes());
            out.flush();
            httpExchange.close();
        }

    }

    public static void main(String[] args) throws IOException {
        httpserverService();
    }

}