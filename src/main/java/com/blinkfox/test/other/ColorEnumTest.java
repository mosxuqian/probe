package com.blinkfox.test.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;

/**
 * ColorEnumTest.
 *
 * @author blinkfox on 2017/9/17.
 */
public class ColorEnumTest {

    private static final Logger log = LoggerFactory.getLogger(ColorEnumTest.class);

    public static void main(String[] args) {
        for (ColorEnum color: ColorEnum.values()) {
            log.info("ordinal:{}, name:{}", color.ordinal(), color.name());
        }

        ColorEnum color = ColorEnum.GREEN;
        switch (color) {
            case RED: log.info("进入了 RED 的分支");
                break;
            case GREEN: log.info("进入了 GREEN 的分支");
                break;
            case BLUE: log.info("进入了 BLUE 的分支");
                break;
            default: log.info("进入了 default 的分支");
        }

        ColorEnum color2 = ColorEnum.valueOf("RED");
        log.info("{}", color2.toString());

        log.info("color name:{}", ColorEnum.getNameByCode(2));

        ColorEnum.GREEN.paint();

        EnumMap<ColorEnum, String> colorEnumMap = new EnumMap<ColorEnum, String>(ColorEnum.class);
        colorEnumMap.put(ColorEnum.RED, "这是EnumMap中的'RED'");
        colorEnumMap.put(ColorEnum.GREEN, "这是EnumMap中的'GREEN'");
        colorEnumMap.put(ColorEnum.BLUE, "这是EnumMap中的'BLUE'");
        log.info("{}", colorEnumMap);
    }

}