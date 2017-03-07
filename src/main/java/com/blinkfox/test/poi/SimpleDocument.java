package com.blinkfox.test.poi;

import com.blinkfox.utils.Log;
import com.blinkfox.utils.StringUtils;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 导出word的示例
 * Created by blinkfox on 2017-03-07.
 */
public class SimpleDocument {

    private static final Log log = Log.get(SimpleDocument.class);

    /**
     * 私有构造方法
     */
    private SimpleDocument() {
        super();
    }

    /**
     * 创建封面
     * @param doc 文档对象
     */
    private static XWPFParagraph createCover(XWPFDocument doc) {
        // 创建封面的段落和内容信息
        XWPFParagraph coverParagraph = doc.createParagraph();
        coverParagraph.setAlignment(ParagraphAlignment.CENTER);

        // 最先的空白,跳12行
        XWPFRun blankRun = coverParagraph.createRun();
        WordUtils.addBreak(blankRun, 12);

        // 主标题
        XWPFRun mainTitleRun = coverParagraph.createRun();
        mainTitleRun.setText("使用POI导出Word");
        mainTitleRun.setBold(true);
        mainTitleRun.setFontSize(28);
        mainTitleRun.addBreak();

        // 副标题
        XWPFRun subTitleRun = coverParagraph.createRun();
        subTitleRun.setText("基础示例");
        subTitleRun.setBold(true);
        subTitleRun.setFontSize(20);
        WordUtils.addBreak(subTitleRun, 10);

        // 导出日期
        XWPFRun dateRun = coverParagraph.createRun();
        dateRun.setText("2017年03月11日");
        dateRun.setFontSize(16);
        dateRun.setBold(true);

        // 创建主要的校验内容信息
        XWPFParagraph mainParagraph = doc.createParagraph();
        mainParagraph.setAlignment(ParagraphAlignment.LEFT);
        mainParagraph.setPageBreak(true);
        return mainParagraph;
    }

    /**
     * 创建循环散列的信息
     * @param paragraph 段落
     */
    private static void createValidInfo(XWPFParagraph paragraph) {
        // 校验结果信息标题
        XWPFRun validTitleRun = paragraph.createRun();
        validTitleRun.setText("一、每条信息的标题");
        validTitleRun.setBold(true);
        validTitleRun.setFontSize(16);
        validTitleRun.addBreak();

        for (int i = 0; i < 20; i++) {
            XWPFRun msgRun = paragraph.createRun();
            msgRun.setText("信息名称：你好世界你好世界你好世界你好世界你好世界你好世界，类型：测试测试测试，是否发出：已发出；");
            msgRun.setFontSize(12);
            msgRun.addBreak();

            int len = new Random().nextInt(5);
            for (int j = 0; j < len; j++) {
                XWPFRun errRun = paragraph.createRun();
                errRun.setText(StringUtils.concat("（", String.valueOf(j + 1), "）", "这发生了什么", String.valueOf(j + 1)));
                errRun.setFontSize(10);
                errRun.setColor("215868");
                errRun.addBreak();

                errRun.setText(StringUtils.concat("信息标签：这是发沙发" + j));
                errRun.addBreak();

                errRun.setText(StringUtils.concat("信息位置：第", String.valueOf(j + 1), "行"));
                errRun.addBreak();

                errRun.setText(StringUtils.concat("信息描述描述：发发生分类法然后暗示疗法", String.valueOf(j + 1)));
                errRun.addBreak();

                errRun.setText(StringUtils.concat("正确示例：北京哦忘了叫", String.valueOf(j + 1)));
                errRun.addBreak();
            }
        }
    }

    /**
     * 创建更多的细节循环散列的信息
     * @param paragraph 段落
     */
    private static void createStatisInfo(XWPFParagraph paragraph) {
        // 统计信息标题
        XWPFRun statisTitleRun = paragraph.createRun();
        statisTitleRun.setText("二、测试结果统计");
        statisTitleRun.setBold(true);
        statisTitleRun.setFontSize(16);
        statisTitleRun.addBreak();

        // 测试文件夹名称
        XWPFRun statisRun = paragraph.createRun();
        statisRun.setText("测试文件夹名称：啊别没事把虎王");
        statisRun.setFontSize(12);
        statisRun.addBreak();

        // 测试文件数量
        statisRun.setText("测试信息数量：20个");
        statisRun.addBreak();

        // 测试通过
        statisRun.setText("测试通过：0个");
        statisRun.addBreak();

        // 测试不通过
        statisRun.setText("测试不通过：20个");
        statisRun.addBreak();

        // 测试耗时
        statisRun.setText("测试耗时2.5秒");
        statisRun.addBreak();
    }

    /**
     * 主运行方法
     * @param args 数组参数
     */
    public static void main(String[] args) {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph paragraph = createCover(doc);
        createValidInfo(paragraph);
        createStatisInfo(paragraph);
        try (
            OutputStream out = new FileOutputStream("H:\\simple.docx")
        ) {
            doc.write(out);
            log.info("导出成功!");
        } catch (IOException e) {
            log.error("输出文档异常", e);
        }
    }

}