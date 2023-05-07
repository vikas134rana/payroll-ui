package com.vikas.payroll.ui.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vikas.payroll.ui.MainLayout;

@Route(value = "Support", layout = MainLayout.class)
public class SupportView extends VerticalLayout {

    public SupportView() {
        add(new Span("Support View"));
    }
}