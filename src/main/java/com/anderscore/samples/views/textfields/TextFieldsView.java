package com.anderscore.samples.views.textfields;

import com.anderscore.samples.views.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@Route(value = "textfields/:name?", layout = MainView.class)
@PageTitle("Text Fields")
public class TextFieldsView extends FlexLayout implements BeforeEnterObserver {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private Binder<SampleModel> binder = new BeanValidationBinder<SampleModel>(SampleModel.class);

    public TextFieldsView() {
        add(createTextFields());
    }

    private VerticalLayout createTextFields() {
        VerticalLayout layout = new VerticalLayout();

        TextField textField = new TextField("Name");
        binder.forField(textField)
                .asRequired("must not be empty")
                .bind(SampleModel::getName, SampleModel::setName);
        layout.add(textField);

        Binder<TextField> textFieldBinder = new Binder<>(TextField.class);

        TextField labelField = new TextField("Label");
        textFieldBinder.forField(labelField)
                .bind(TextField::getLabel, TextField::setLabel);
        layout.add(labelField);

        TextField placeholderField = new TextField("Placehoder");
        textFieldBinder.forField(placeholderField)
                .bind(TextField::getPlaceholder, TextField::setPlaceholder);
        layout.add(placeholderField);

        TextField helperTextField = new TextField("Helper Text");
        textFieldBinder.forField(helperTextField)
                .bind(TextField::getHelperText, TextField::setHelperText);
        layout.add(helperTextField);

        TextField titleField = new TextField("Title");
        textFieldBinder.forField(titleField)
                .bind(TextField::getTitle, TextField::setTitle);
        layout.add(titleField);

        IntegerField minLengthField = new IntegerField("Min Length");
        textFieldBinder.forField(minLengthField)
                .asRequired()
                .bind(TextField::getMinLength, TextField::setMinLength);
        IntegerField maxLengthField = new IntegerField("Max Length");
        textFieldBinder.forField(maxLengthField)
                .asRequired()
                .bind(TextField::getMaxLength, TextField::setMaxLength);
        TextField patternField = new TextField("Pattern");
        textFieldBinder.forField(patternField)
                .bind(TextField::getPattern, TextField::setPattern);
        layout.add(new HorizontalLayout(minLengthField, maxLengthField, patternField));

        HorizontalLayout checkboxLayout = new HorizontalLayout();
        for (String property : "autofocus,autoselect,clearButtonVisible,preventInvalidInput".split(",")) {
            Checkbox checkbox = new Checkbox(StringUtils.capitalize(property));
            textFieldBinder.forField(checkbox).bind(property);
            checkboxLayout.add(checkbox);
        }
        Checkbox enabledCheckbox = new Checkbox("Enabled");
        textFieldBinder.forField(enabledCheckbox).bind(TextField::isEnabled, TextField::setEnabled);
        checkboxLayout.add(enabledCheckbox);
        Checkbox readOnlyCheckbox = new Checkbox("ReadOnly");
        textFieldBinder.forField(readOnlyCheckbox).bind(TextField::isReadOnly, TextField::setReadOnly);
        checkboxLayout.add(readOnlyCheckbox);
        layout.add(checkboxLayout);

        Select<Autocomplete> autocompleteSelect = new Select<>();
        autocompleteSelect.setItems(Autocomplete.values());
        textFieldBinder.forField(autocompleteSelect)
                .bind(TextField::getAutocomplete, TextField::setAutocomplete);
        layout.add(autocompleteSelect);

        Button validateBinderButton = new Button("Validate Binder");
        validateBinderButton.addClickListener(this::validate);
        Button validateTextFieldBinderButton = new Button("Validate TextField-Binder");
        validateTextFieldBinderButton.addClickListener(ev -> textFieldBinder.writeBeanAsDraft(textField));
        layout.add(new HorizontalLayout(validateBinderButton, validateTextFieldBinderButton));

        textFieldBinder.readBean(textField);
        return layout;
    }

    private void validate(ClickEvent<Button> buttonClickEvent) {
        BinderValidationStatus<SampleModel> status = binder.validate();
        if (status.isOk()) {
            log.info("validation ok");
        } else {
            for (ValidationResult validationError : status.getValidationErrors()) {
                log.info("validation {}: {}",
                        validationError.getErrorLevel().orElse(ErrorLevel.SYSTEM),
                        validationError.getErrorMessage());
            }
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        SampleModel sampleModel = new SampleModel();
        sampleModel.setName(event.getRouteParameters().get("name").orElse(null));
        binder.setBean(sampleModel);
    }
}
