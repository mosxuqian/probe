package com.blinkfox.test.query;

import java.util.List;
import java.util.Map;

/**
 * Created by blinkfox on 2016/12/21.
 */
public class Book {

    private List<Author> authorList;
    private Map<String, Author> authorMap;
    private Author mainAuthor;
    private String name;
    private double price;

    public Book() {
        super();
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public Map<String, Author> getAuthorMap() {
        return authorMap;
    }

    public void setAuthorMap(Map<String, Author> authorMap) {
        this.authorMap = authorMap;
    }

    public Author getMainAuthor() {
        return mainAuthor;
    }

    public void setMainAuthor(Author mainAuthor) {
        this.mainAuthor = mainAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}