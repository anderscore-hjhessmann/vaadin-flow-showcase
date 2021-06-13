package com.anderscore.samples.views.binding;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class MasterForm extends VerticalLayout {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final TextField valueField = new TextField("Master-Value");
    private final DetailEditor detailEditor = new DetailEditor();
    private final Binder<MasterModel> binder = new Binder<>();
    private final String requiredText;

    public MasterForm(String name, String requiredText) {
        this.requiredText = requiredText;
        add(new H3(name));
        add(valueField);
        add(detailEditor);
        binder.forField(valueField)
                .withValidator(this::isValueValid, "value must contain '" + requiredText + "'")
                .bind(MasterModel::getValue, MasterModel::setValue);
        binder.forField(detailEditor)
                .asRequired("detail must be given")
                .bind(MasterModel::getDetail, MasterModel::setDetail)
                .setAsRequiredEnabled(false);

        Button validate = new Button("Validate");
        validate.addClickListener(ev -> binder.validate());
        add(new HorizontalLayout(validate));
    }

    private boolean isValueValid(String value) {
        return value.contains(requiredText);
    }

    public Binder<MasterModel> getBinder() {
        return binder;
    }
}
