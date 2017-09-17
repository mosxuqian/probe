package com.blinkfox.test.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    }

}