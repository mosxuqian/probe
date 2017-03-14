package com.blinkfox.learn.guice.two;

/**
 * 笔记本电脑
 * Created by blinkfox on 2017-03-14.
 */
public class Laptop {

    // 名称
    private String name;

    // 品牌
    private String brand;

    // 价格
    private double price;

    /**
     * 空构造方法
     */
    public Laptop() {
        super();
    }

    /**
     * 全构造方法
     * @param name 名称
     * @param brand 品牌
     * @param price 价格
     */
    public Laptop(String name, String brand, double price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    /* getter和setter方法 */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * toString方法
     * @return str
     */
    @Override
    public String toString() {
        return new StringBuilder("Laptop{name='").append(name).append("', brand='").append(brand)
                .append("', price='").append(price).append("'}").toString();
    }
}