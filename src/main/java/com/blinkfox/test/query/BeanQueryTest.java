package com.blinkfox.test.query;

import java.util.ArrayList;
import java.util.List;
import static cn.jimmyshi.beanquery.BeanQuery.*;

/**
 * bean-query包的使用示例类
 * Created by blinkfox on 2016/12/21.
 */
public class BeanQueryTest {

    private BeanQueryTest() {
    }

    /**
     * 构建地址集合
     * @return
     */
    private static List<Address> buildAddrs() {
        List<Address> addrs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int index = i + 1;
            Address addr = new Address();
            addr.setAddress("街道" + index);
            addr.setPostCode("街道编码" + index);
            addrs.add(addr);
        }
        return addrs;
    }

    /**
     * 打印地址的集合信息
     */
    private static void printAddrs(List<Address> addrs) {
        for (Address addr: addrs) {
            System.out.println("地址:" + addr.getAddress() + ",地址编码:" + addr.getPostCode());
        }
    }

    /**
     * main方法主入口
     * @param args
     */
    public static void main(String[] args) {
        List<Address> addrs = buildAddrs();
        printAddrs(addrs);
        System.out.println("----------------------------------");

        List<Address> newAddrs = select(Address.class)
            .from(addrs)
            .where(
                value("address", containsString("6")),
                value("postCode", is("街道编码6"))
            )
            .execute();
        printAddrs(newAddrs);
    }

}