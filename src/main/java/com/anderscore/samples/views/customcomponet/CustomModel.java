package com.anderscore.samples.views.customcomponet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;

public class CustomModel {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private LocalDate someDate;

    public LocalDate getSomeDate() {
        log.debug("getSomeDate...");
        return someDate;
    }

    public void setSomeDate(LocalDate someDate) {
        log.debug("setSomeDate({})...", someDate);
        this.someDate = someDate;
    }
}
