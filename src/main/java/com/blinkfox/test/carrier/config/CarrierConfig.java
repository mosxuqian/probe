package com.blinkfox.test.carrier.config;

import com.blinkfox.carrier.config.AbstractCarrierConfig;
import java.util.Set;

/**
 * Created by blinkfox on 2017/1/25.
 */
public class CarrierConfig extends AbstractCarrierConfig {

    @Override
    public void scanPackagePaths(Set<String> set) {
        set.add("com.blinkfox");
    }

}