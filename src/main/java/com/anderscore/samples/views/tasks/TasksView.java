package com.anderscore.samples.views.tasks;

import com.anderscore.samples.views.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

@Route(value = "tasks/:taskId?", layout = MainView.class)
@PageTitle("Tasks")
public class TasksView extends FlexLayout implements BeforeEnterObserver {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private TaskController taskController;

    private final H1 id = new H1();
    private final TextField name = new TextField("Name");
    private final DatePicker due = new DatePicker("Due");

    private final BeanValidationBinder<Task> binder = new BeanValidationBinder(Task.class);

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button cancel = new Button("Cancel");

    public TasksView() {
        FormLayout layout = new FormLayout();
        layout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("10em", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE)
        );

        layout.add(id, 2);
        layout.add(name, 1);
        layout.add(due, 1);
        layout.add(createButtons(), 2);
        add(layout);
        binder.bindInstanceFields(this);
    }

    private Component createButtons() {
        save.addClickListener(this::save);
        delete.addClickListener(this::delete);
        delete.addClickListener(this::cancel);
        return new HorizontalLayout(save, delete, cancel);
    }

    private void save(ClickEvent<Button> buttonClickEvent) {
        BinderValidationStatus<Task> validationStatus = binder.validate();
        if (validationStatus.isOk()) {
            Task savedTask = taskController.saveTask(binder.getBean());
            setTask(savedTask);
        }
    }

    private void delete(ClickEvent<Button> buttonClickEvent) {
        Task task = binder.getBean();
        if (task.getId() != null) {
            taskController.deleteTask(task);
        }
        setTask(taskController.createTask());
    }

    private void cancel(ClickEvent<Button> buttonClickEvent) {
        Task task = binder.getBean();
        Task loadedTask = taskController.openTask(Optional.ofNullable(task.getId()).map(String::valueOf));
        setTask(loadedTask);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Task task = taskController.openTask(event.getRouteParameters().get("taskId"));
        setTask(task);
    }

    private void setTask(Task task) {
        if (task.getId() == null) {
            id.setText("New Task");
            delete.setEnabled(false);
        } else {
            id.setText("Task: " + task.getId());
            delete.setEnabled(true);
        }
        binder.setBean(task);
    }
}
