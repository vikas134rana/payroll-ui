package com.vikas.payroll.ui.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H3 logo = new H3("Payroll");
        HorizontalLayout header = new HorizontalLayout(logo);
        header.addClassName("header");
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink dashboardLink = new RouterLink("Dashboard", DashboardView.class);
        RouterLink employeeLink = new RouterLink("Employee", EmployeeView.class);
        RouterLink payrollLink = new RouterLink("Payroll", PayrollView.class);
        RouterLink attendanceLink = new RouterLink("Attendance", AttendanceView.class);
        RouterLink taxLink = new RouterLink("Tax", TaxView.class);
        RouterLink reportsLink = new RouterLink("Reports", ReportsView.class);
        RouterLink supportLink = new RouterLink("Support", SupportView.class);
        VerticalLayout drawer = new VerticalLayout(dashboardLink, employeeLink, payrollLink, attendanceLink, taxLink, reportsLink, supportLink);
        drawer.addClassName("drawer");
        addToDrawer(drawer);
    }

}
