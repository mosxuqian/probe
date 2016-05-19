package com.blinkfox.test;

/**
 * Created by blinkfox on 16-5-14.
 */
public class StringTest {

    public static void main(String[] args) {
        int n = 1000000;
        createByConcat(n);
        createBySb(n);
    }

    public static void createByConcat(int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            String sql = "SELECT" + " * " + "FROM" + " class as c " + " LEFT JOIN " + " student as s " + " ON c.id = s.classId " + " WHERE s.age > 18 " + " ORDER BY s.age ASC";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("-----------createByConcat:" + (endTime - startTime));
    }

    public static void createBySb(int n) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT").append(" * ").append("FROM").append(" class as c ")
            .append(" LEFT JOIN ").append(" student as s ").append(" ON c.id = s.classId ")
            .append(" WHERE s.age > 18 ").append(" ORDER BY s.age ASC");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("-----------createBySb:" + (endTime - startTime));
    }

}