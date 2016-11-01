package com.blinkfox.test;

import org.mvel2.MVEL;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by blinkfox on 2016-10-31.
 */
public class MvelTest {

    public static void main(String[] args) {
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("foobar", 100);
        vars.put("foo", "  ");
        vars.put("bar", "bar");
        vars.put("cat", "cat");
        vars.put("123", "123");
        List<String> mylist = new ArrayList<String>();
        mylist.add("1");
        vars.put("mylist", mylist);

        Boolean result1 = (Boolean) MVEL.eval("foobar > 99", vars);
        System.out.println("----result1:" + result1);

        Boolean result2 = (Boolean) MVEL.eval("foo == empty", vars);
        System.out.println("----result2:" + result2);

        Boolean result2_1 = (Boolean) MVEL.eval("mylist == empty", vars);
        System.out.println("----result2_1:" + result2_1);

        Boolean result3 = (Boolean) MVEL.eval("foo == null", vars);
        System.out.println("----result3:" + result3);

        Boolean result4 = (Boolean) MVEL.eval("foo == null || '123' == 123", vars);
        System.out.println("----result4:" + result4);

        String result5 = (String) TemplateRuntime.eval("The value is @{foobar}", vars);
        System.out.println("----result5:" + result5);

        String result6 = (String) TemplateRuntime.eval("@code{age = 23; name = 'John Doe'} " +
                " @{name} is @{age} years old.", vars);
        System.out.println("----result6:" + result6);

        String result7 = (String) TemplateRuntime.eval("" +
                "@if{foo != bar}\n" +
                "   Foo not a bar!\n" +
                "@else{bar != cat}\n" +
                "   Bar is not a cat!\n" +
                "@else{}\n" +
                "   Foo may be a Bar or a Cat!\n" +
                "@end{}", vars);
        System.out.println("----result7:" + result7);

        List<String> lists = new ArrayList<String>();
        lists.add("aaa");
        lists.add("bbb");
        lists.add("ccc");
        vars.put("lists", lists);
        String result8 = (String) TemplateRuntime.eval("" +
                "@foreach{item : lists} \n" +
                " - @{item}\n" +
                "@end{}", vars);
        System.out.println("----result8:" + result8);

        String template = "1 + 1 = @{blinkfox}";
        CompiledTemplate compiled = TemplateCompiler.compileTemplate(template);
        vars.put("blinkfox", "闪烁之狐");
        String result9 = (String) TemplateRuntime.execute(compiled, vars);
        System.out.println("----result9:" + result9);
    }

}