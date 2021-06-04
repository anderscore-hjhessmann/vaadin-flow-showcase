package com.anderscore.samples.views.fancy;

import com.anderscore.samples.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "fancy", layout = MainView.class)
@PageTitle("Fancy View")
public class FancyView extends Div {

    public FancyView() {
        addClassName("fancy-view");
        add(new Text("Some Fancy Text"));
    }
}
