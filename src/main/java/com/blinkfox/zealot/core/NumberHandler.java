package com.blinkfox.zealot.core;

import com.blinkfox.zealot.bean.BuildSource;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.helpers.BuildSqlInfoHelper;
import com.blinkfox.zealot.helpers.StringHelper;
import com.blinkfox.zealot.helpers.ZealotHelper;
import com.blinkfox.zealot.consts.ZealotConst;
import com.blinkfox.zealot.helpers.OgnlHelper;
import org.dom4j.Node;

/**
 * 数字查询动态sql生成的实现类
 * Created by blinkfox on 2016/10/30.
 */
public class NumberHandler implements IConditHandler {

    /**
     * 构建数字区间查询的动态条件sql
     * @param source
     * @return
     */
    @Override
    public SqlInfo buildSqlInfo(BuildSource source) {
        /* 获取拼接的参数 */
        SqlInfo sqlInfo = source.getSqlInfo();
        Node node = source.getNode();

		/* 判断必填的参数是否为空 */
        String fieldText = ZealotHelper.getAndCheckNodeText(node, ZealotConst.ATTR_FIELD);
        String[] valueTextArr = ZealotHelper.getBothNodeText(node);
        Double startNum = StringHelper.isBlank(valueTextArr[0]) ? null
                : Double.parseDouble(valueTextArr[0]);
        Double endNum = StringHelper.isBlank(valueTextArr[1]) ? null
                : Double.parseDouble(valueTextArr[1]) ;

		/* 如果匹配中字符没有，则认为是必然生成项 */
        Node matchNode = (Node) node.selectSingleNode(ZealotConst.ATTR_MATCH);
        String matchText = ZealotHelper.getNodeText(matchNode);
        if (StringHelper.isBlank(matchText)) {
            sqlInfo = BuildSqlInfoHelper.buildNumSql(source, fieldText, startNum, endNum);
        } else {
			/* 如果match匹配成功，则生成数据库sql条件和参数 */
            Boolean isTrue = (Boolean) OgnlHelper.parseWithOgnl(matchText, source);
            if (isTrue) {
                sqlInfo = BuildSqlInfoHelper.buildNumSql(source, fieldText, startNum, endNum);
            }
        }

        return sqlInfo;
    }

}