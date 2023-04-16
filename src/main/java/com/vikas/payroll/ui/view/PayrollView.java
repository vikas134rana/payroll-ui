package com.vikas.payroll.ui.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "payroll", layout = MainLayout.class)
public class PayrollView extends VerticalLayout {

    public PayrollView() {
        add(new Span("Payroll View"));
    }
}