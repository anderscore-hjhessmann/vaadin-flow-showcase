package com.anderscore.samples.views.customcomponet;

import com.vaadin.flow.component.AbstractCompositeField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

public class CompositeDatePicker extends AbstractCompositeField<HorizontalLayout, CompositeDatePicker, LocalDate> {

    private final NumberField dayOfMonth = new NumberField("Tag");
    private final NumberField month = new NumberField("Monat");
    private final NumberField year = new NumberField("Jahr");

    /**
     * Creates a new field. The provided default value is used by
     * {@link #getEmptyValue()} and is also used as the initial value of this
     * instance.
     */
    public CompositeDatePicker() {
        super(null);
        dayOfMonth.addValueChangeListener(this::update);
        month.addValueChangeListener(this::update);
        year.addValueChangeListener(this::update);
        getContent().add(dayOfMonth, month, year);
    }

    private void update(ValueChangeEvent<Double> ev) {
        LocalDate newDate;
        if (dayOfMonth.isEmpty() || month.isEmpty() || year.isEmpty()) {
            newDate = null;
        } else {
            newDate = LocalDate.of(
                    year.getValue().intValue(),
                    month.getValue().intValue(),
                    dayOfMonth.getValue().intValue()
            );
        }
        setModelValue(newDate, true);
    }

    @Override
    protected void setPresentationValue(LocalDate date) {
        if (date == null) {
            dayOfMonth.clear();
            month.clear();
            year.clear();
        } else {
            dayOfMonth.setValue(Double.valueOf(date.getDayOfMonth()));
            month.setValue(Double.valueOf(date.getMonthValue()));
            year.setValue(Double.valueOf(date.getYear()));
        }
    }
}
