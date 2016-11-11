package com.blinkfox.handler;

import com.blinkfox.zealot.bean.BuildSource;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.consts.ZealotConst;
import com.blinkfox.zealot.core.IConditHandler;
import com.blinkfox.zealot.helpers.ParseHelper;
import com.blinkfox.zealot.helpers.StringHelper;
import com.blinkfox.zealot.helpers.XmlNodeHelper;
import org.dom4j.Node;
import java.util.List;

/**
 * 自定义的ID和Email条件查询的SQL处理器
 * Created by blinkfox on 2016/11/11.
 */
public class UserIdEmailHandler implements IConditHandler {

    @Override
    public SqlInfo buildSqlInfo(BuildSource source) {
        /* 获取拼接的参数和Zealot节点 */
        SqlInfo sqlInfo = source.getSqlInfo();
        Node node = source.getNode();

        // 获取配置的属性值,getAndCheckNodeText()方法会检测属性值是否为空，如果为空，会抛出运行时异常
        String idField = XmlNodeHelper.getAndCheckNodeText(node, "attribute::idField");
        String emailField = XmlNodeHelper.getAndCheckNodeText(node, "attribute::emailField");
        // getAndCheckNodeText()方法仅仅只获取属性的值，即使未配置或书写值，也会返回空字符串
        String idValue = XmlNodeHelper.getNodeAttrText(node, "attribute::idValue");
        String emailValue = XmlNodeHelper.getNodeAttrText(node, "attribute::emailValue");

        /* 获取match属性值,如果匹配中 字符值没有，则认为是必然生成项 */
        String matchText = XmlNodeHelper.getNodeAttrText(node, ZealotConst.ATTR_MATCH);
        if (StringHelper.isBlank(matchText)) {
            sqlInfo = buildIdEmailSqlInfo(source, idField, emailField, idValue, emailValue);
        } else {
			/* 如果match匹配成功，则生成数据库sql条件和参数 */
            Boolean isTrue = (Boolean) ParseHelper.parseWithMvel(matchText, source.getParamObj());
            if (isTrue) {
                sqlInfo = buildIdEmailSqlInfo(source, idField, emailField, idValue, emailValue);
            }
        }

        return sqlInfo;
    }

    /**
     * 构建自定义的SqlInfo信息，区分是根据id做等值查询，还是根据email做模糊查询的情况
     * @param source
     * @param idField
     * @param emailField
     * @param idValue
     * @param emailValue
     */
    private SqlInfo buildIdEmailSqlInfo(BuildSource source, String idField, String emailField,
            String idValue, String emailValue) {
        SqlInfo sqlInfo = source.getSqlInfo();
        StringBuilder join = sqlInfo.getJoin();
        List<Object> params = sqlInfo.getParams();

        // 如果userId不为空，则根据id来做等值查询
        Integer idText = (Integer) ParseHelper.parseWithMvel(idValue, source.getParamObj());
        if (idText != null) {
            // prefix是前缀，如"and","or"之类，没有则默认为空字符串""
            join.append(source.getPrefix()).append(idField).append(ZealotConst.EQUAL_SUFFIX);
            params.add(idText);
            return sqlInfo.setJoin(join).setParams(params);
        }

        // 获取userEmail的值,如果userEmail不为空，则根据email来做模糊查询
        String emailText = (String) ParseHelper.parseWithMvel(emailValue, source.getParamObj());
        if (StringHelper.isNotBlank(emailText)) {
            // prefix是前缀，如"and","or"之类，没有则默认为空字符串""
            join.append(source.getPrefix()).append(emailField).append(ZealotConst.LIEK_SUFFIX);
            params.add("%" + emailText + "%");
            return sqlInfo.setJoin(join).setParams(params);
        }

        return sqlInfo;
    }

}