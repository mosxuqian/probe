package com.blinkfox.build;

/**
 * Created by blinkfox on 16-5-13.
 */
public class KhalaTest {

    public static void main(String[] args) {
        String sql = new Khala()
            .createSql()
            .select("*")
            .from("blog")
            .where("name like ?")
            .orderBy("date").getSql();
        System.out.println("生成的sql:" + sql);

        String sql2 = new Khala()
            .createSql()
            .select("*")
            .from("class as c")
            .leftJoin("student as s")
            .on("c.id = s.classId")
            .where("s.age > 18")
            .orderBy("s.age", Khala.ASC)
            .getSql();
        System.out.println("生成的sql2:" + sql2);
        printMsg(true, "aaa", "bbb", "ccc");

        // time test
        int n = 1000000;
        createOld(n);
        createNew(n);
        createStaticNew(n);

    }

    public static void printMsg(boolean debug, String ... msgs){
        StringBuffer sb = new StringBuffer();
        for (String s: msgs) {
            sb.append(s);
        }
        System.out.println("conn strings:" + sb.toString());
    }

    public static void createOld(int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            String sql = "SELECT *  FROM class as c  LEFT JOIN student as s  ON c.id = s.classId  WHERE s.age > 18  ORDER BY s.age ASC";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("-----------oldTime:" + (endTime - startTime));
    }

    public static void createNew(int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            String sql = new Khala()
                    .createSql()
                    .select("*")
                    .from("class as c")
                    .leftJoin("student as s")
                    .on("c.id = s.classId")
                    .where("s.age > 18")
                    .orderBy("s.age", Khala.ASC)
                    .getSql();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("==============newTime:" + (endTime - startTime));
    }

    public static void createStaticNew(int n) {
        long startTime = System.currentTimeMillis();

        String sel = "*";
        String cls = "class as c";
        String std = "student as s";
        String on = "c.id = s.classId";
        String whereAge = "s.age > 18";
        String odr = "s.age";

        for (int i = 0; i < n; i++) {
            String sql = new Khala()
                    .createSql()
                    .select(sel)
                    .from(cls)
                    .leftJoin(std)
                    .on(on)
                    .where(whereAge)
                    .orderBy(odr, Khala.ASC)
                    .getSql();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("==============newStaticTime:" + (endTime - startTime));
    }

}