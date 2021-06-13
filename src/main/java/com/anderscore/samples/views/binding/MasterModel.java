package com.anderscore.samples.views.binding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class MasterModel {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static int nextId = 1;

    private final int id = nextId++;
    private String value;

    private DetailModel detail;

    public String getValue() {
        log.debug("{}: get value", this);
        return value;
    }

    public void setValue(String value) {
        log.debug("{}: set value to {}", this, value);
        this.value = value;
    }

    public DetailModel getDetail() {
        log.debug("{}: get detail", this);
        return detail;
    }

    public void setDetail(DetailModel detail) {
        log.debug("{}: set detail to {}", this, detail);
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "MasterModel{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", detail=" + detail +
                '}';
    }
}
