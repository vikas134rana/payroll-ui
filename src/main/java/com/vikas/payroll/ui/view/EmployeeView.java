package com.vikas.payroll.ui.view;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vikas.payroll.ui.MainLayout;
import com.vikas.payroll.ui.rest.Employee;
import com.vikas.payroll.ui.rest.PayrollService;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;

@Route(value = "employee", layout = MainLayout.class)
public class EmployeeView extends VerticalLayout {

    public EmployeeView(PayrollService service) {
        add(new Span("Employee View"));

        GridCrud<Employee> crud = new GridCrud<>(Employee.class);

        // custom form factory
        DefaultCrudFormFactory<Employee> customFormFactory = new DefaultCrudFormFactory<>(Employee.class);
        crud.setCrudFormFactory(customFormFactory);

        // search filter
        TextField searchFilter = createSearchFilter(crud);

        // customise which columns to show on different UI(Grid, Add, Edit and Delete Employee)
        setVisiblePropertiesForCrudOperations(crud);

        // customise which endpoints to call for different CRUD operations (Add, Edit and Delete Employee)
        setCrudOperations(service, crud, searchFilter);

        // customise fields (dropdown, checkbox etc)
        customiseFields(service, crud);

        setSizeFull();
        add(crud);
    }

    private static void customiseFields(PayrollService service, GridCrud<Employee> crud) {

        // department dropdown
        crud.getCrudFormFactory().setFieldProvider("department", emp -> {
            return new ComboBox<>("Department", service.getAllDepartments());
        });

        // gender dropdown
        crud.getCrudFormFactory().setFieldProvider("gender", emp -> {
            return new ComboBox<>("Gender", service.getAllGenders());
        });

        // other fields
    }

    private static TextField createSearchFilter(GridCrud<Employee> crud) {
        // search searchFilter
        TextField searchFilter = new TextField();
        searchFilter.setPlaceholder("Filter by name");
        searchFilter.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(searchFilter);
        searchFilter.addValueChangeListener(e -> crud.refreshGrid());
        return searchFilter;
    }

    private static void setCrudOperations(PayrollService service, GridCrud<Employee> crud, TextField filter) {
        crud.setFindAllOperation(() -> service.getEmployeesContainingName(filter.getValue()));
        //        crud.setAddOperation(service::add);
        //        crud.setUpdateOperation(service::update);
        //        crud.setDeleteOperation(service::delete);
    }

    private static void setVisiblePropertiesForCrudOperations(GridCrud<Employee> crud) {
        crud.getGrid().setColumns("id", "name", "gender", "contactNumber", "department", "designation", "status");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "name", "gender", "dateOfBirth", "maritalStatus", "address",
                "contactNumber", "email", "department", "designation", "type", "status", "joiningDate", "lastWorkingDay");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.UPDATE, "id", "name", "gender", "dateOfBirth", "maritalStatus", "address",
                "contactNumber", "email", "department", "designation", "type", "status", "joiningDate", "lastWorkingDay");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.DELETE, "id", "name");
    }
}