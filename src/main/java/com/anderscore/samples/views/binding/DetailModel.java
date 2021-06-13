package com.anderscore.samples.views.binding;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class DetailModel {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static int nextId = 1;

    private final int id = nextId++;
    private String value;

    public String getValue() {
        log.debug("id {}: getValue: {}", id, value);
        return value;
    }

    public void setValue(String value) {
        log.debug("id {}: setValue {} -> {}", id, this.value, value);
        this.value = value;
    }

    public boolean isEmpty() {
        return StringUtils.isBlank(value);
    }

    @Override
    public String toString() {
        return "DetailModel{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
