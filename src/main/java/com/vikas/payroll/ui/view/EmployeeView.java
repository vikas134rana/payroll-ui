package com.vikas.payroll.ui.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
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

        GridCrud<Employee> employeeCrud = getEmployeeGridCrud(service);

        TabSheet tabSheet = new TabSheet();
        tabSheet.add("Employee", employeeCrud);
        tabSheet.add("Bank Details",
                new Div(new Text("This is the Bank Detail tab content")));
        tabSheet.setSizeFull();

        setSizeFull();
        add(tabSheet);
    }

    private static GridCrud<Employee> getEmployeeGridCrud(PayrollService service) {
        GridCrud<Employee> employeeCrud = new GridCrud<>(Employee.class);

        // custom form factory
        DefaultCrudFormFactory<Employee> employeeDefaultCrudFormFactory = new DefaultCrudFormFactory<>(Employee.class);
        employeeCrud.setCrudFormFactory(employeeDefaultCrudFormFactory);

        // search filter
        TextField searchFilter = createSearchFilter(employeeCrud);

        // customise which columns to show on different UI(Grid, Add, Edit and Delete Employee)
        setVisiblePropertiesForCrudOperations(employeeCrud);

        // customise which endpoints to call for different CRUD operations (Add, Edit and Delete Employee)
        setCrudOperations(service, employeeCrud, searchFilter);

        // customise fields (dropdown, checkbox etc)
        customiseFields(service, employeeCrud);


        employeeCrud.setSizeFull();
        return employeeCrud;
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

        // marital_status dropdown
        crud.getCrudFormFactory().setFieldProvider("maritalStatus", emp -> {
            return new ComboBox<>("Marital Status", service.getAllMaritalStatus());
        });

        // designation dropdown
        crud.getCrudFormFactory().setFieldProvider("designation", emp -> {
            return new ComboBox<>("Designation", service.getAllDesignations());
        });

        // type dropdown
        crud.getCrudFormFactory().setFieldProvider("type", emp -> {
            return new ComboBox<>("Type", service.getAllTypes());
        });

        // status dropdown
        crud.getCrudFormFactory().setFieldProvider("status", emp -> {
            return new ComboBox<>("Status", service.getAllStatus());
        });

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
        crud.setAddOperation(service::createEmployees); // CREATE
        crud.setFindAllOperation(() -> service.getEmployeesContainingName(filter.getValue())); // READ
        crud.setUpdateOperation(service::updateEmployees); // UPDATE
        crud.setDeleteOperation(emp -> service.deleteEmployees(emp.getId())); // DELETE
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