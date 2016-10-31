package com.blinkfox.zealot.core;

import com.blinkfox.zealot.bean.BuildSource;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.config.ZealotConfig;
import com.blinkfox.zealot.consts.ZealotConst;
import ognl.OgnlContext;
import org.dom4j.Document;
import org.dom4j.Node;
import java.util.ArrayList;
import java.util.List;

/**
 * Zealot的核心解析类
 * Created by blinkfox on 2016/10/30.
 */
public class Zealot {

    public static SqlInfo getSqlInfo(String spaceKey, String zealotId, Object paramObj) {
        Document doc = ZealotConfig.getZealots().get(spaceKey);
        if (doc == null) {
            throw new RuntimeException("未获取到xml文档,spaceKey为：" + spaceKey);
        }

        String XPath = "//zealot[@id='" + zealotId +"']";
        Node node = doc.selectSingleNode(XPath);
        if (node == null) {
            throw new RuntimeException("未获取到zealot节点,zealotId为：" + zealotId);
        }

        return buildSqlInfo(node, paramObj);
    }

    /**
     * 构建完整的SqlInfo对象
     * @param node
     * @param paramMap
     * @return
     */
    private static SqlInfo buildSqlInfo(Node node, Object paramMap) {
        SqlInfo sqlInfo = new SqlInfo(new StringBuffer(""), new ArrayList<Object>());
        List<Object> params = sqlInfo.getParams();
        StringBuffer join = sqlInfo.getJoin();

        OgnlContext context = new OgnlContext();
        context.put("map", paramMap);
        List<Node> nodes = node.selectNodes("child::node()");
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            System.out.println(" -----遍历第" + (i + 1) +"个node name为:" + n.getName());
            System.out.println(" -----遍历第" + (i + 1) +"个node type为:" + n.getNodeType());
            System.out.println(" -----遍历第" + (i + 1) +"个node typeName为:" + n.getNodeTypeName());
            System.out.println(" -----遍历第" + (i + 1) +"个node path为:" + n.getPath());
            System.out.println(" -----遍历第" + (i + 1) +"个node text为:" + n.getText());
            System.out.println(" -----------------------------------");

            if (ZealotConst.NODETYPE_TEXT.equals(n.getNodeTypeName())) {
                // 如果子节点node 是文本节点，则直接获取其文本
                join.append(n.getText());
            } else if (ZealotConst.NODETYPE_ELEMENT.equals(n.getNodeTypeName())) {
                BuildSource source = new BuildSource(sqlInfo, n, context, paramMap);
                // 如果子节点node 是元素节点，则再判断其是什么元素，动态判断条件和参数
                sqlInfo = ConditContext.buildSqlInfo(source, n.getName());
            }
        }

        return sqlInfo;
    }

}