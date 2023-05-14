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
import com.vikas.payroll.ui.rest.BankDetails;
import com.vikas.payroll.ui.rest.PayrollService;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;

import java.util.Collections;

@Route(value = "bank_details", layout = MainLayout.class)
public class BankDetailsView extends VerticalLayout implements HasUrlParameter<Long> {

    private Long employeeId;

    ParameterDeserializer parameterDeserializer;

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long employeeId) {
        this.employeeId = employeeId;
    }

    public BankDetailsView(PayrollService service) {
        add(new Span("BankDetails View"));

        GridCrud<BankDetails> bankDetailsGridCrud = getBankDetailsGridCrud(service);

        setSizeFull();
        add(bankDetailsGridCrud);
    }

    private GridCrud<BankDetails> getBankDetailsGridCrud(PayrollService service) {
        GridCrud<BankDetails> bankDetailsGridCrud = new GridCrud<>(BankDetails.class);

        // custom form factory
        DefaultCrudFormFactory<BankDetails> bankDetailsDefaultCrudFormFactory = new DefaultCrudFormFactory<>(BankDetails.class);
        bankDetailsGridCrud.setCrudFormFactory(bankDetailsDefaultCrudFormFactory);

        // customise which columns to show on different UI(Grid, Add, Edit and Delete BankDetails)
        setVisiblePropertiesForCrudOperations(bankDetailsGridCrud);

        // customise which endpoints to call for different CRUD operations (Add, Edit and Delete BankDetails)
        setCrudOperations(service, bankDetailsGridCrud);

        // add employeeId column
        bankDetailsGridCrud.getGrid().addColumn(new ComponentRenderer<>(employee -> new Text(String.valueOf(employeeId))))
                .setHeader("Employee Id");


        bankDetailsGridCrud.setSizeFull();
        return bankDetailsGridCrud;
    }

    private void setCrudOperations(PayrollService service, GridCrud<BankDetails> crud) {
        crud.setAddOperation(bankDetails -> {
            if(service.getEmployeeCount() == 0) {
                return service.createBankDetails(this.employeeId, bankDetails);
            }
            else {
                Notification errorNotification = Notification.show ("Add BankDetails Failed - Only one row of Bank Details is allowed for an employee.");
                errorNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return null;
            }
        }); // CREATE
        crud.setFindAllOperation(() -> Collections.singleton(service.getBankDetails(employeeId))); // GET
        crud.setUpdateOperation(bankDetails -> service.updateBankDetails(employeeId, bankDetails)); // UPDATE
        crud.setDeleteOperationVisible(false); // DELETE (don't allow)
    }

    private void setVisiblePropertiesForCrudOperations(GridCrud<BankDetails> crud) {
        crud.getGrid().setColumns("id", "accountNumber", "bankName", "bankBranch", "ifscCode");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "accountNumber", "bankName", "bankBranch", "ifscCode");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.UPDATE, "id", "accountNumber", "bankName", "bankBranch", "ifscCode");
    }

}