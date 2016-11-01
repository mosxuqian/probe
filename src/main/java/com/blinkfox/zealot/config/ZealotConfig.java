package com.blinkfox.zealot.config;

import com.blinkfox.zealot.bean.TagHandler;
import com.blinkfox.zealot.bean.XmlContext;
import com.blinkfox.zealot.consts.ZealotConst;
import com.blinkfox.zealot.core.concrete.BetweenHandler;
import com.blinkfox.zealot.core.concrete.EqualHandler;
import com.blinkfox.zealot.core.concrete.InHandler;
import com.blinkfox.zealot.core.concrete.LikeHandler;
import org.dom4j.Document;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Zealot主配置类
 * Created by blinkfox on 2016/10/30.
 */
public abstract class ZealotConfig {

    // 所有zealots XML文档的缓存map，key是资源的路径（转换成下划线分割），value是dom4j文档
    private static Map<String, Document> zealots = new ConcurrentHashMap<String, Document>();

    // 初始化默认的一些tagHandlers到HashSet集合中
    private static Set<TagHandler> tagHandlers = new HashSet<TagHandler>() {{
        add(new TagHandler(ZealotConst.EQUAL, EqualHandler.class));
        add(new TagHandler(ZealotConst.AND_EQUAL, ZealotConst.AND_PREFIX, EqualHandler.class));
        add(new TagHandler(ZealotConst.OR_EQUAL, ZealotConst.OR_PREFIX, EqualHandler.class));
        add(new TagHandler(ZealotConst.LIKE, LikeHandler.class));
        add(new TagHandler(ZealotConst.AND_LIKE, ZealotConst.AND_PREFIX, LikeHandler.class));
        add(new TagHandler(ZealotConst.OR_LIKE, ZealotConst.OR_PREFIX, LikeHandler.class));
        add(new TagHandler(ZealotConst.BETWEEN, BetweenHandler.class));
        add(new TagHandler(ZealotConst.AND_BETWEEN, ZealotConst.AND_PREFIX, BetweenHandler.class));
        add(new TagHandler(ZealotConst.OR_BETWEEN, ZealotConst.OR_PREFIX, BetweenHandler.class));
        add(new TagHandler(ZealotConst.IN, InHandler.class));
        add(new TagHandler(ZealotConst.AND_IN, ZealotConst.AND_PREFIX, InHandler.class));
        add(new TagHandler(ZealotConst.OR_IN, ZealotConst.OR_PREFIX, InHandler.class));
    }};

    /**
     * 获取全局的Zealots文档缓存数据
     * @return
     */
    public static Map<String, Document> getZealots() {
        return zealots;
    }

    /**
     * 获取全局的tagHandlers集合对象
     * @return
     */
    public static Set<TagHandler> getTagHandlers() {
        return tagHandlers;
    }

    /**
     * 添加自定义标签和其对应的Handler.class
     * @param tagName
     * @param handlerCls
     */
    protected static void add(String tagName, Class<?> handlerCls) {
        tagHandlers.add(new TagHandler(tagName, handlerCls));
    }

    /**
     * 添加自定义标签和其对应的Handler.class
     * @param tagName
     * @param handlerCls
     */
    protected static void add(String tagName, String prefix,Class<?> handlerCls) {
        tagHandlers.add(new TagHandler(tagName, prefix, handlerCls));
    }

    /**
     * 配置xml文件的标识和资源路径
     * @param ctx
     */
    public abstract void configXml(XmlContext ctx);

    /**
     * 配置标签和其对应的处理类
     */
    public abstract void configTagHandler();

}