package com.vikas.payroll.ui.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vikas.payroll.ui.MainLayout;

@Route(value = "tax", layout = MainLayout.class)
public class TaxView extends VerticalLayout {

    public TaxView() {
        add(new Span("Tax View"));
    }
}