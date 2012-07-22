/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * Implement the spring request parameter binder to convert empty strings to null by default
 *
 * @author oliver.elder.esq
 */
public class GlobalBindingInitializer implements WebBindingInitializer {

    @Autowired
    private Validator validator;
    @Autowired
    private ConversionService conversionService;

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

        binder.setValidator(validator);
        binder.setConversionService(conversionService);
    }
}
