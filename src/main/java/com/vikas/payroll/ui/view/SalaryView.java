package com.vikas.payroll.ui.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.ParameterDeserializer;
import com.vaadin.flow.router.Route;
import com.vikas.payroll.ui.MainLayout;
import com.vikas.payroll.ui.rest.Employee;
import com.vikas.payroll.ui.rest.PayrollService;
import com.vikas.payroll.ui.rest.Salary;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;

import java.util.Collections;

@Route(value = "salary", layout = MainLayout.class)
public class SalaryView extends VerticalLayout implements HasUrlParameter<Long> {

    private Long employeeId;

    ParameterDeserializer parameterDeserializer;

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long employeeId) {
        this.employeeId = employeeId;
    }

    public SalaryView(PayrollService service) {
        add(new Span("Salary View"));

        GridCrud<Salary> salaryGridCrud = getSalaryGridCrud(service);

        setSizeFull();
        add(salaryGridCrud);
    }

    private GridCrud<Salary> getSalaryGridCrud(PayrollService service) {
        GridCrud<Salary> salaryGridCrud = new GridCrud<>(Salary.class);

        // custom form factory
        DefaultCrudFormFactory<Salary> salaryDefaultCrudFormFactory = new DefaultCrudFormFactory<>(Salary.class);
        salaryGridCrud.setCrudFormFactory(salaryDefaultCrudFormFactory);

        // customise which columns to show on different UI(Grid, Add, Edit and Delete Salary)
        setVisiblePropertiesForCrudOperations(salaryGridCrud);

        // customise which endpoints to call for different CRUD operations (Add, Edit and Delete Salary)
        setCrudOperations(service, salaryGridCrud);

        // add employeeId column
        salaryGridCrud.getGrid().addColumn(new ComponentRenderer<>(employee -> new Text(String.valueOf(employeeId))))
                .setHeader("Employee Id");


        salaryGridCrud.setSizeFull();
        return salaryGridCrud;
    }

    private void setCrudOperations(PayrollService service, GridCrud<Salary> crud) {
        crud.setAddOperation(salary -> {
            if (service.getEmployeeCount() == 0) {
                return service.createSalary(this.employeeId, salary);
            } else {
                Notification errorNotification = Notification.show("Add Salary Failed - Only one row of Salary is allowed for an employee.");
                errorNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return null;
            }
        }); // CREATE
        crud.setFindAllOperation(() -> Collections.singleton(service.getSalary(employeeId))); // GET
        crud.setUpdateOperation(salary -> service.updateSalary(employeeId, salary)); // UPDATE
        crud.setDeleteOperationVisible(false); // DELETE (don't allow)
    }

    private void setVisiblePropertiesForCrudOperations(GridCrud<Salary> crud) {
        crud.getGrid().setColumns("id", "ctc", "basicSalary", "hra", "specialAllowance");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "ctc", "basicSalary", "hra", "specialAllowance");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.UPDATE, "id", "ctc", "basicSalary", "hra", "specialAllowance");
    }

}