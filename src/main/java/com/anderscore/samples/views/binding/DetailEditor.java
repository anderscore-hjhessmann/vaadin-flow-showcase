package com.anderscore.samples.views.binding;

import com.vaadin.flow.component.AbstractCompositeField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class DetailEditor extends AbstractCompositeField<HorizontalLayout, DetailEditor, DetailModel> {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final TextField valueField = new TextField("Detail-Value");
    private final Binder<DetailModel> binder = new Binder<>();

    public DetailEditor() {
        super(null);
        getContent().add(valueField);
        binder.forField(valueField)
                .withValidator(this::isValueValid, "value must contain 'def'")
                .bind(DetailModel::getValue, DetailModel::setValue);
        binder.addValueChangeListener(this::update);
    }

    private void update(ValueChangeEvent<?> valueChangeEvent) {
        if (binder.getBean() == null) {
            DetailModel model = new DetailModel();
            if (binder.writeBeanIfValid(model)) {
                if (model.isEmpty()) {
                    setModelValue(null, true);
                } else {
                    setModelValue(model, true);
                }
            } else {
                log.info("input not valid", model);
            }
        } else {
            DetailModel model = binder.getBean();
            if (binder.isValid()) {
                if (model.isEmpty()) {
                    setModelValue(null, true);
                } else {
                    setModelValue(model, true);
                }
            } else {
                log.info("{} not valid", model);
            }
        }
    }

    @Override
    protected void setModelValue(DetailModel newModelValue, boolean fromClient) {
        log.info("set model value to {} from {}", newModelValue, fromClient ? "client" : "server");
        super.setModelValue(newModelValue, fromClient);
    }

    private boolean isValueValid(String value) {
        return StringUtils.isBlank(value) || value.contains("def");
    }

    @Override
    protected void setPresentationValue(DetailModel detailModel) {
        binder.setBean(detailModel);
        setModelValue(detailModel, false);
    }
}
