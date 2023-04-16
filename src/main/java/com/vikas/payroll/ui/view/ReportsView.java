package com.vikas.payroll.ui.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "reports", layout = MainLayout.class)
public class ReportsView extends VerticalLayout {

    public ReportsView() {
        add(new Span("Reports View"));
    }
}