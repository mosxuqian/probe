package com.blinkfox.zealot.helpers;

import com.blinkfox.zealot.bean.BuildSource;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.consts.ZealotConst;

import java.util.List;

/**
 * 构建sql查询相关的帮助类
 * Created by blinkfox on 2016/10/30.
 */
public class BuildSqlInfoHelper {

    static SqlInfo sqlInfo = null; // sqlInfo对象
    static StringBuffer join = null; // sql拼接器
    static List<Object> params = null; // 有序的参数结合

    /**
     * 根据构建的资源参数初始化数据
     * @param source
     */
    private static void init(BuildSource source) {
        sqlInfo = source.getSqlInfo();
        join = sqlInfo.getJoin();
        params = sqlInfo.getParams();
    }

    /**
     * 构建普通的sql信息
     * @param source
     * @param fieldText
     * @param valueText
     * @return
     */
    public static SqlInfo buildEqualSql(BuildSource source, String fieldText, String valueText) {
        init(source);

        join.append(source.getPrefix()).append(fieldText).append(ZealotConst.EQUAL_SUFFIX);
        params.add(OgnlHelper.parseWithOgnl(valueText, source));

        return sqlInfo.setJoin(join).setParams(params);
    }

    /**
     * 构建Like模糊查询的sql信息
     * @param source
     * @param fieldText
     * @param valueText
     * @return
     */
    public static SqlInfo buildLikeSql(BuildSource source, String fieldText, String valueText) {
        init(source);

        join.append(source.getPrefix()).append(fieldText).append(ZealotConst.LIEK_SUFFIX);
        Object obj = OgnlHelper.parseWithOgnl(valueText, source);
        params.add("%" + obj + "%");

        return sqlInfo.setJoin(join).setParams(params);
    }

    /**
     * 构建数字查询的sql信息
     * @param source
     * @param fieldText
     * @param startText
     * @param endText
     * @return
     */
    public static SqlInfo buildBetweenSql(BuildSource source, String fieldText,
            String startText, String endText) {
        init(source);

        /* 根据开始文本和结束文本判断执行是大于、小于还是区间的查询sql和参数的生成 */
        if (StringHelper.isNotBlank(startText) &&
                StringHelper.isBlank(endText)) {
            join.append(source.getPrefix()).append(fieldText).append(ZealotConst.GT_SUFFIX);
            params.add(OgnlHelper.parseWithOgnl(startText, source));
        } else if (StringHelper.isBlank(startText) &&
                StringHelper.isNotBlank(endText)) {
            join.append(source.getPrefix()).append(fieldText).append(ZealotConst.LT_SUFFIX);
            params.add(OgnlHelper.parseWithOgnl(endText, source));
        } else if (StringHelper.isNotBlank(startText) &&
                StringHelper.isNotBlank(endText)) {
            join.append(source.getPrefix()).append(fieldText).append(ZealotConst.BT_AND_SUFFIX);
            params.add(OgnlHelper.parseWithOgnl(startText, source));
            params.add(OgnlHelper.parseWithOgnl(endText, source));
        }

        return sqlInfo.setJoin(join).setParams(params);
    }

}