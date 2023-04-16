package com.vikas.payroll.ui.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "attendance", layout = MainLayout.class)
public class AttendanceView extends VerticalLayout {

    public AttendanceView() {
        add(new Span("Payroll View"));
    }
}