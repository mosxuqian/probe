package com.blinkfox.learn.poitl;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * poi-tl库的使用示例.
 * Created by blinkfox on 2017/6/27.
 */
public class PoitlTest {

    private static final Logger log = LoggerFactory.getLogger(PoitlTest.class);

    /** 项目资源路径. */
    private static final String PATH = "/Users/blinkfox/Documents/dev/gitrepo/probe/src/main/resources";

    /** word模板路径. */
    private static final String DOC_FILE = PATH + "/others/poitl/test.docx";

    /** 图片路径. */
    private static final String PIC_FILE = PATH + "/others/poitl/pic.png";

    /** 输出文件及路径. */
    private static final String OUTPUT_PATH = "/Users/blinkfox/Downloads/out_word.docx";

    /**
     * 构造map型的data数据.
     * @return map
     */
    private static Map<String, Object> buildData() {
        Map<String, Object> testMap = new HashMap<String, Object>();
        testMap.put("title", "我的旅游日记");
        testMap.put("smallTitle", "初写日记");
        testMap.put("startDate", "2017-01-01");
        testMap.put("endDate", "2017-06-28");
        testMap.put("count", 5);
        testMap.put("place1", "九寨沟");
        testMap.put("place2", "天涯海角");
        testMap.put("money", 2425.02);
        testMap.put("pic", new PictureRenderData(600, 400, PIC_FILE));
        return testMap;
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile(DOC_FILE).render(buildData());

        FileOutputStream out = new FileOutputStream(OUTPUT_PATH);
        template.write(out);
        out.flush();
        out.close();
        template.close();
        log.info("通过'poi-tl'导出word成功!");
    }

}