package com.anderscore.samples;

import com.vaadin.flow.i18n.I18NProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class SampleI18NProvider implements I18NProvider {
    public static final String RESOURCE_BUNDLE_NAME = "i18n.showcase";
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final ResourceBundle RESOURCE_BUNDLE_EN = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.ENGLISH);
    private static final ResourceBundle RESOURCE_BUNDLE_DE = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.GERMAN);

    @Override
    public List<Locale> getProvidedLocales() {
        return List.of(Locale.ENGLISH, Locale.GERMAN);
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        ResourceBundle resourceBundle = RESOURCE_BUNDLE_EN;
        if (Locale.GERMAN.equals(locale)) {
            resourceBundle = RESOURCE_BUNDLE_DE;
        }
        if (!resourceBundle.containsKey(key)) {
            if (params.length == 0) {
                return locale.getLanguage() + ":" + key;
            } else {
                return locale.getLanguage() + ":" + key + Arrays.toString(params);
            }
        } else {
            String text = resourceBundle.getString(key);
            if (params.length == 0) {
                return text;
            } else {
                String filledText = MessageFormat.format(text, params);
                return filledText;
            }
        }
    }
}
