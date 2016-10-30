package com.blinkfox.zealot.bean;

import com.blinkfox.zealot.helpers.OgnlHelper;
import org.dom4j.Node;
import java.util.List;

/**
 * Zealot相关的帮助类
 * Created by blinkfox on 2016/10/30.
 */
public class ZealotHelper {

    /**
     * 获取xml节点的文本值，如果是对象是空的，则转为空字符串
     * @param node
     * @return
     */
    public static String getNodeText(Node node) {
        return node == null ? "": node.getText();
    }

    /**
     * 检查节点是否为空
     * @param node
     * @return
     */
    public static String getAndCheckNodeText(Node node, String nodeName) {
		/* 判断必填的参数是否为空 */
        Node fieldNode = (Node) node.selectSingleNode(nodeName);
        String fieldText = ZealotHelper.getNodeText(fieldNode);
        if (StringHelper.isBlank(fieldText)) {
            throw new RuntimeException("数据库字段是空的");
        }
        return fieldText;
    }

    /**
     * 构建普通的sql信息
     * @param source
     * @param fieldText
     * @param valueText
     */
    public static SqlInfo buildEqualSql(BuildSource source, String fieldText, String valueText) {
        SqlInfo sqlInfo = source.getSqlInfo();
        StringBuffer join = sqlInfo.getJoin();
        List<Object> params = sqlInfo.getParams();

        join.append(source.getPrefix()).append(fieldText).append(source.getSuffix());
        params.add(OgnlHelper.parseWithOgnl(valueText, source));

        return sqlInfo.setJoin(join).setParams(params);
    }

    /**
     * 构建普通的sql信息
     * @param source
     * @param fieldText
     * @param valueText
     */
    public static SqlInfo buildLikeSql(BuildSource source, String fieldText, String valueText) {
        SqlInfo sqlInfo = source.getSqlInfo();
        StringBuffer join = sqlInfo.getJoin();
        List<Object> params = sqlInfo.getParams();

        join.append(source.getPrefix()).append(fieldText).append(source.getSuffix());
        Object obj = OgnlHelper.parseWithOgnl(valueText, source);
        params.add("%" + obj + "%");

        return sqlInfo.setJoin(join).setParams(params);
    }

}