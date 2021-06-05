package com.anderscore.samples.views.customcomponet;

import com.anderscore.samples.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Locale;

@Route(value = "customcomponent", layout = MainView.class)
@PageTitle("Custom components")
public class CustomComponentsView extends VerticalLayout implements AfterNavigationObserver {
    private Binder<CustomModel> binder = new Binder();
    private CustomModel customModel;

    private DatePicker vaadinDatePicker;
    private CompositeDatePicker compositeDatePicker;
    private CompositeDatePickerUsingBinder compositeDatePickerUsingBinder;
    private Button syncButton;

    public CustomComponentsView() {
        vaadinDatePicker = new DatePicker();
        vaadinDatePicker.setLabel("Vaadin Date-Picker");
        vaadinDatePicker.setLocale(Locale.GERMANY);
        add(vaadinDatePicker);
        binder.forField(vaadinDatePicker)
                .bind(CustomModel::getSomeDate, CustomModel::setSomeDate);

        compositeDatePicker = new CompositeDatePicker();
        add(compositeDatePicker);
        binder.forField(compositeDatePicker)
                .bind(CustomModel::getSomeDate, CustomModel::setSomeDate);

        compositeDatePickerUsingBinder = new CompositeDatePickerUsingBinder();
        add(compositeDatePickerUsingBinder);
        binder.forField(compositeDatePickerUsingBinder)
                .bind(CustomModel::getSomeDate, CustomModel::setSomeDate);

        syncButton = new Button("Sync");
        syncButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(syncButton);
        syncButton.addClickListener(ev -> binder.readBean(customModel));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        customModel = new CustomModel();
        binder.setBean(customModel);
    }
}
