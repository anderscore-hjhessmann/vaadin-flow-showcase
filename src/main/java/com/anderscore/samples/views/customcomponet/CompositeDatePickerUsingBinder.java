package com.anderscore.samples.views.customcomponet;

import com.vaadin.flow.component.AbstractCompositeField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.data.validator.AbstractValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.invoke.MethodHandles;
import java.time.DateTimeException;
import java.time.LocalDate;

public class CompositeDatePickerUsingBinder extends AbstractCompositeField<HorizontalLayout, CompositeDatePickerUsingBinder, LocalDate> {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final IntegerField dayOfMonth = new IntegerField("Tag");
    private final IntegerField month = new IntegerField("Monat");
    private final IntegerField year = new IntegerField("Jahr");
    private final BeanValidationBinder<LocalDateModel> binder = new BeanValidationBinder(LocalDateModel.class);

    /**
     * Creates a new field. The provided default value is used by
     * {@link #getEmptyValue()} and is also used as the initial value of this
     * instance.
     */
    public CompositeDatePickerUsingBinder() {
        super(null);
        dayOfMonth.addValueChangeListener(this::update);
        month.addValueChangeListener(this::update);
        year.addValueChangeListener(this::update);
        getContent().add(dayOfMonth, month, year);
        binder.withValidator(createValidator()).bindInstanceFields(this);
    }

    private Validator<LocalDateModel> createValidator() {
        return new AbstractValidator<LocalDateModel>("Kein gültiges Datum") {
            @Override
            public ValidationResult apply(LocalDateModel value, ValueContext context) {
                try {
                    value.getDate();
                } catch (DateTimeException ex) {
                    log.info("Validation failed: {}", ex.getMessage());
                    return ValidationResult.error(ex.getMessage());
                }
                return ValidationResult.ok();
            }
        };
    }

    private void update(ValueChangeEvent<Integer> ev) {
        LocalDateModel model = new LocalDateModel();
        binder.writeBeanIfValid(model);
        setModelValue(model.getDate(), true);
    }

    @Override
    protected void setPresentationValue(LocalDate date) {
        LocalDateModel model = new LocalDateModel();
        model.setDate(date);
        binder.readBean(model);
    }

    @Override
    public void setRequiredIndicatorVisible(boolean requiredIndicatorVisible) {
        dayOfMonth.setRequiredIndicatorVisible(requiredIndicatorVisible);
        month.setRequiredIndicatorVisible(requiredIndicatorVisible);
        year.setRequiredIndicatorVisible(requiredIndicatorVisible);
    }

    @Override
    public boolean isRequiredIndicatorVisible() {
        return year.isRequiredIndicatorVisible();
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        dayOfMonth.setReadOnly(readOnly);
        month.setReadOnly(readOnly);
        year.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() {
        return year.isReadOnly();
    }

    public static class LocalDateModel {
        private Integer dayOfMonth;
        private Integer month;
        private Integer year;

        @Min(1)
        @Max(31)
        public Integer getDayOfMonth() {
            return dayOfMonth;
        }

        public void setDayOfMonth(Integer dayOfMonth) {
            log.debug("setDayOfMonth({})...", dayOfMonth);
            this.dayOfMonth = dayOfMonth;
        }

        @Min(1)
        @Max(12)
        public Integer getMonth() {
            return month;
        }

        public void setMonth(Integer month) {
            log.debug("setMonth({})...", month);
            this.month = month;
        }

        @Min(1900)
        @Max(2100)
        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            log.debug("setYear({})...", year);
            this.year = year;
        }

        public LocalDate getDate() {
            if (year == null || month == null || dayOfMonth == null) {
                return null;
            }
            return LocalDate.of(year, month, dayOfMonth);
        }

        public void setDate(LocalDate date) {
            log.debug("setDate({})...", date);
            if (date == null) {
                year = null;
                month = null;
                dayOfMonth = null;
            } else {
                year = date.getYear();
                month = date.getMonthValue();
                dayOfMonth = date.getDayOfMonth();
            }
        }
    }
}
