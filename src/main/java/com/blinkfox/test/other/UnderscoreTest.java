package com.blinkfox.test.other;

import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.github.underscore.$;
import com.github.underscore.Block;
import com.github.underscore.Function1;
import com.github.underscore.FunctionAccum;
import com.github.underscore.Predicate;
import com.github.underscore.Tuple;

/**
 * Underscore-java测试类
 * Created by blinkfox on 2017/2/15.
 */
public class UnderscoreTest {

    /**
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // each -> $.each(collection, iterator)
        $.each(asList(1, 2, 3), new Block<Integer>() {
            public void apply(Integer item) {
                System.out.print(item + ",");
            }
        });

        // map -> $.map(collection, iterator)
        System.out.println();
        List<Integer> list = $.map(asList(1, 2, 3), new Function1<Integer, Integer>() {
            public Integer apply(Integer item) {
                return item * 3;
            }
        });
        System.out.println("list:" + list.toString());

        Map<Integer, String> map = new LinkedHashMap<Integer, String>() {
            private static final long serialVersionUID = 1L;
            {
                put(1, "one");
                put(2, "two");
                put(3, "three");
            }};
        Set<Integer> list2 = $.map(map.entrySet(), new Function1<Map.Entry<Integer, String>, Integer>() {
            public Integer apply(Map.Entry<Integer, String> item) {
                return item.getKey() * 3;
            }
        });
        System.out.println("list2:" + list2.toString());

        // reduce -> $.reduce(collection, iterator, memo)
        int result = $.reduce(asList(1, 2, 3), new FunctionAccum<Integer, Integer>() {
            public Integer apply(Integer item1, Integer item2) {
                return item1 + item2;
            }
        }, 0);
        System.out.println("result:" + result);

        // find -> $.find(collection, iterator)
        Integer result2 = $.find(asList(1, 2, 3, 4, 5, 6), new Predicate<Integer>() {
            public Boolean apply(Integer item) {
                return item % 2 == 0;
            }
        }).get();
        System.out.println("result2:" + result2);

        // filter -> $.filter(collection, iterator)
        List<Integer> result3 = $.filter(asList(1, 2, 3, 4, 5, 6), new Predicate<Integer>() {
            public Boolean apply(Integer item) {
                return item % 2 == 0;
            }
        });
        System.out.println("result3:" + result3);

        // where -> $.where(list, properties)
        class Book {
            public final String title;
            public final String author;
            public final Integer year;
            public Book(final String title, final String author, final Integer year) {
                this.title = title;
                this.author = author;
                this.year = year;
            }
            public String toString() {
                return "title: " + title + ", author: " + author + ", year: " + year;
            }
        }
        List<Book> listOfPlays = new ArrayList<Book>() {
            private static final long serialVersionUID = 1L;
            {
                add(new Book("Cymbeline2", "Shakespeare", 1614));
                add(new Book("Cymbeline", "Shakespeare", 1611));
                add(new Book("The Tempest", "Shakespeare", 1611));
            }};
        List<Book> result4 = $.where(listOfPlays, asList(Tuple.<String, Object>create("author", "Shakespeare"),
                Tuple.<String, Object>create("year", Integer.valueOf(1611))));
        System.out.println("result4:" + result4);

        System.out.println("keys:" + $.keys(map));
    }

}