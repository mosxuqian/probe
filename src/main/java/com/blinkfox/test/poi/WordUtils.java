package com.blinkfox.test.poi;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * word导出相关的工具类
 * Created by blinkfox on 2017-02-27.
 */
public final class WordUtils {

    /**
     * 私有构造方法
     */
    private WordUtils() {
        super();
    }

    /**
     * word换行
     * @param run
     * @param num 换行次数
     */
    public static void addBreak(XWPFRun run, int num) {
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                run.addBreak();
            }
        }
    }

    /**
     * 生成某个段落的XWPFRun信息
     * @param paragraph
     * @param text
     * @return
     */
    public static XWPFParagraph createRun(XWPFParagraph paragraph, String text) {
        XWPFRun errRun = paragraph.createRun();
        errRun.setText(text);
        errRun.setFontSize(12);
        errRun.addBreak();
        return paragraph;
    }

}