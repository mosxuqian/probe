package com.blinkfox.myioc.bean;

import com.blinkfox.myioc.consts.Scope;
import java.util.ArrayList;
import java.util.List;

/**
 * 依赖注入提供者的信息封装类
 * Created by blinkfox on 2017/1/8.
 */
public class ProviderInfo {

    private String id; // 依赖注入提供者的唯一标识

    private Class cls; // 待实例化的类的class

    private List<Class> injects; // 需要注入的类class

    private Scope scope; // 实例化bean的方式,默认实例为单例模式

    /**
     * 私有构造方法
     */
    private ProviderInfo() {
        super();
    }

    /**
     * 初始化默认的依赖注入提供者信息
     * @return 默认的提供者信息
     */
    public static ProviderInfo newInstance() {
        return new ProviderInfo().setScope(Scope.PROTOTYPE).setInjects(new ArrayList<Class>());
    }

    /* getter 和 setter 方法*/
    public String getId() {
        return id;
    }

    public ProviderInfo setId(String id) {
        this.id = id;
        return this;
    }

    public Class getCls() {
        return cls;
    }

    public ProviderInfo setCls(Class cls) {
        this.cls = cls;
        return this;
    }

    public List<Class> getInjects() {
        return injects;
    }

    public ProviderInfo setInjects(List<Class> injects) {
        this.injects = injects;
        return this;
    }

    public Scope getScope() {
        return scope;
    }

    public ProviderInfo setScope(Scope scope) {
        this.scope = scope;
        return this;
    }

}