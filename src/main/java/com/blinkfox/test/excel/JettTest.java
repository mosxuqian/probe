package com.blinkfox.test.excel;

import com.blinkfox.utils.Log;
import net.sf.jett.transform.ExcelTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Jett测试类
 * Created by blinkfox on 2017/1/12.
 */
public class JettTest {

    private static final Log log = Log.get(JettTest.class);

    private static Map<String, Object> buildTestData() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("sf", "四川");
        dataMap.put("ds", "成都");

        List<String> sfmcs = Arrays.asList("四川", "浙江", "山东");
        dataMap.put("sfmcs", sfmcs);

        List<String> dsmcs = Arrays.asList("成都", "绵阳", "达州");
        dataMap.put("dsmcs", dsmcs);

        List<String> sexs = Arrays.asList("男", "女");
        dataMap.put("sexs", sexs);

        return dataMap;
    }

    /**
     * @param args
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static void main(String[] args) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream("/Users/blinkfox/Downloads/excelexport/test.xlsx");
        OutputStream os = new FileOutputStream("/Users/blinkfox/Downloads/excelexport/export.xlsx");
        Map<String, Object> dataMap = buildTestData();
        Workbook workbook = new ExcelTransformer().transform(fis, dataMap);
        Name countyNames = workbook.getName("provinces");
        // log.info(countyNames.getRefersToFormula());
        workbook.write(os);
        log.info("导出成功!");
    }

}