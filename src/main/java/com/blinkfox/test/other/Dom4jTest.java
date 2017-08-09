package com.blinkfox.test.other;

import com.blinkfox.zealot.consts.ZealotConst;
import com.blinkfox.zealot.helpers.XmlNodeHelper;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.pmw.tinylog.Logger;

/**
 * Dom4j测试类.
 * @author blinkfox on 2017-08-07.
 */
public class Dom4jTest {

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        Document doc = XmlNodeHelper.getDocument("zealot/zealot-user.xml");
        if (doc == null) {
            Logger.error("未获取到doc实例!");
            return;
        }

        Node node = XmlNodeHelper.getZealotNodeById(doc, "queryUserInfo");
        if (node == null) {
            Logger.error("queryUserInfo的节点为null!");
            return;
        }

        @SuppressWarnings("unchecked")
        List<Node> nodes = node.selectNodes(ZealotConst.ATTR_CHILD);
        for (Node n: nodes) {
            if (ZealotConst.NODETYPE_TEXT.equals(n.getNodeTypeName())) {
                Logger.info("这是文本节点.节点内容为:{}", n.getText());
            } else if (ZealotConst.NODETYPE_ELEMENT.equals(n.getNodeTypeName())) {
                if (n.getName().equals("include")) {
                    Logger.info("include标签:{}", n.getName());
                }
                Logger.info("这是元素节点.节点内容为:{}", n.getName());
            }
        }
        Logger.info("node:", node);
    }

}