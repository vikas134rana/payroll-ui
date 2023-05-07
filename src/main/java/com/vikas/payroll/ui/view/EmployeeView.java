package com.vikas.payroll.ui.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vikas.payroll.ui.MainLayout;
import com.vikas.payroll.ui.rest.Employee;
import com.vikas.payroll.ui.rest.PayrollService;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "employee", layout = MainLayout.class)
public class EmployeeView extends VerticalLayout {

    public EmployeeView(PayrollService service) {
        add(new Span("Employee View"));

        GridCrud<Employee> crud = new GridCrud<>(Employee.class);

        // search filter
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by name");
        filter.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(filter);

        crud.setFindAllOperation(() -> service.getEmployeesContainingName(filter.getValue()));
        //        crud.setAddOperation(service::add);
        //        crud.setUpdateOperation(service::update);
        //        crud.setDeleteOperation(service::delete);

        filter.addValueChangeListener(e -> crud.refreshGrid());


        crud.getGrid().setColumns("id", "name", "gender", "contactNumber", "department", "designation", "status");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "name", "gender", "dateOfBirth", "maritalStatus", "address",
                "contactNumber", "email", "department", "designation", "type", "status", "joiningDate", "lastWorkingDay");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.UPDATE, "id", "name", "gender", "dateOfBirth", "maritalStatus", "address",
                "contactNumber", "email", "department", "designation", "type", "status", "joiningDate", "lastWorkingDay");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.DELETE, "id", "name");

       /* // logic configuration
        crud.setOperations(
                () -> service.findByNameContainingIgnoreCase(filter.getValue()),
                user -> service.save(user),
                user -> service.save(user),
                user -> service.delete(user)
        );*/

        setSizeFull();
        add(crud);
    }
}