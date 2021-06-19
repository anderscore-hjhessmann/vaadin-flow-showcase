package com.anderscore.samples.views.customcomponet;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;

@Tag("my-hello-element")
@JsModule("components/hello/my-hello-element.ts")
public class MyHello extends Component {

    public MyHello(String greet) {
        getElement().setProperty("greet", greet);
    }
}
