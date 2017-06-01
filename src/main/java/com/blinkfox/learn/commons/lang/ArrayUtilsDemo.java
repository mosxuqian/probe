package com.blinkfox.learn.commons.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.pmw.tinylog.Logger;

import java.util.Arrays;

/**
 * ArrayUtilsDemo.
 * Created by blinkfox on 2017/5/31.
 */
public class ArrayUtilsDemo {

    public static void main(String[] args) {
        String[] arr = ArrayUtils.nullToEmpty((String[]) null);
        String[] arr2 = ArrayUtils.nullToEmpty(new String[]{"1", "2"});
        Logger.info("arr:{}, arr2:{}", Arrays.toString(arr), Arrays.toString(arr2));

        String[] arr3 = new String[]{"a", "b", "c", "d", "e"};
        ArrayUtils.reverse(arr3, 1, 2);
        Logger.info("arr3:{}", Arrays.toString(arr3));

        int a = ArrayUtils.indexOf(new String[]{"aa","bb","bb"},"bb",2);
        Logger.info("索引a:{}", a);

        String[] arr4 = ArrayUtils.addAll(new String[]{"aa", "bb"}, new String[]{"cc"});
        Logger.info("arr4:{}", Arrays.toString(arr4));
    }

}