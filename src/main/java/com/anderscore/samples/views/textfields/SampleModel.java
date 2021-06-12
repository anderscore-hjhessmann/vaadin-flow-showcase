package com.anderscore.samples.views.textfields;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class SampleModel {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private String name;

    public String getName() {
        log.debug("get name");
        return name;
    }

    public void setName(String name) {
        log.info("set name to {}", name);
        this.name = name;
    }
}
