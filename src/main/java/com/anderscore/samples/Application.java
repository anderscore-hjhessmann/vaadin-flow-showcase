package com.anderscore.samples;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.component.page.Push;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "vaadin-flow-showcase", variant = Lumo.DARK)
@PWA(name = "Vaadin-Flow-Showcase", shortName = "Vaadin-Flow-Showcase", offlineResources = {"images/logo.png"})
@Push
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        if (false) {
            LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
        } else {
            SpringApplication.run(Application.class, args);
        }
    }

}
