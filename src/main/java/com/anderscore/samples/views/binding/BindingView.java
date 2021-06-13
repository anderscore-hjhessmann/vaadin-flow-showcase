package com.anderscore.samples.views.binding;

import com.anderscore.samples.views.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@Route(value = "binding/:masterValue?/:detailValue?", layout = MainView.class)
@PageTitle("Binding")
public class BindingView extends VerticalLayout implements BeforeEnterObserver {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private MasterForm primaryForm = new MasterForm("Primary Form", "abc");
    private MasterForm secondaryForm = new MasterForm("Secondary Form", "bc");

    public BindingView() {
        add(primaryForm);
        add(secondaryForm);

        Button readBean = new Button("Read Bean");
        readBean.addClickListener(ev ->
                secondaryForm.getBinder().readBean(primaryForm.getBinder().getBean()));
        Button writeBean = new Button("Write Bean");
        writeBean.addClickListener(ev ->
                secondaryForm.getBinder().writeBeanAsDraft(primaryForm.getBinder().getBean()));
        Button readNewBean = new Button("Read new Bean");
        readNewBean.addClickListener(this::readNewBean);
        Button dumpBean = new Button("Dump Bean");
        dumpBean.addClickListener(this::dumpBean);
        add(new HorizontalLayout(readBean, writeBean, readNewBean, dumpBean));
    }

    private ComponentEventListener<ClickEvent<Button>> getClickEventComponentEventListener() {
        return ev -> log.info("primary bean: ", primaryForm.getBinder().getBean());
    }

    private void readNewBean(ClickEvent<Button> buttonClickEvent) {
        MasterModel bean = new MasterModel();
        try {
            secondaryForm.getBinder().writeBean(bean);
            primaryForm.getBinder().readBean(bean);
        } catch (ValidationException ex) {
            log.error("Secondary form validation failed", ex);
        }
    }

    private void dumpBean(ClickEvent<Button> buttonClickEvent) {
        log.info("primary bean: {}, secondary bean: {}",
                primaryForm.getBinder().getBean(),
                secondaryForm.getBinder().getBean());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        MasterModel masterModel = new MasterModel();
        DetailModel detailModel = new DetailModel();
        masterModel.setDetail(detailModel);
        masterModel.setValue(event.getRouteParameters().get("masterValue").orElse(null));
        detailModel.setValue(event.getRouteParameters().get("detailValue").orElse(null));
        primaryForm.getBinder().setBean(masterModel);
        dumpBean(null);
    }
}
