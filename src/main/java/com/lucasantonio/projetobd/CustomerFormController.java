package com.lucasantonio.projetobd;

import com.lucasantonio.projetobd.db.DbException;
import com.lucasantonio.projetobd.listeners.DataChangeListener;
import com.lucasantonio.projetobd.model.entities.Customer;
import com.lucasantonio.projetobd.model.services.CustomerService;
import com.lucasantonio.projetobd.util.Alerts;
import com.lucasantonio.projetobd.util.Constraints;
import com.lucasantonio.projetobd.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    private Customer entity;

    private CustomerService service;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField txtCustomerID;
    @FXML
    private TextField txtCompanyName;
    @FXML
    private TextField txtContactName;
    @FXML
    private TextField txtContactTitle;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtRegion;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtFax;
    @FXML
    private Label labelErrorName;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    public void setCustomer(Customer entity){
        this.entity = entity;
    }

    public void setCustomerService(CustomerService service){
        this.service = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    @FXML
    public void onBtnSaveAction(ActionEvent event) {
        if (entity == null) throw new IllegalStateException("Entity was null");
        if (service == null) throw new IllegalStateException("Service was null");

        try {
            entity = getFormData();
            service.saveOrUpdate(entity);
            notifyDataChangeListener();
            Utils.currentStage(event).close();
        } catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    private void notifyDataChangeListener() {
        for (DataChangeListener listerner: dataChangeListeners) {
            listerner.onDataChanged();
        }
    }

    private Customer getFormData() {
        Customer obj = new Customer();
        obj.setCustomerID(txtCustomerID.getText());
        obj.setCompanyName(txtCompanyName.getText());
        obj.setContactName(txtContactName.getText());
        obj.setContactTitle(txtContactTitle.getText());
        obj.setAddress(txtAddress.getText());
        obj.setCity(txtCity.getText());
        obj.setRegion(txtRegion.getText());
        obj.setPostalCode(txtPostalCode.getText());
        obj.setCountry(txtCountry.getText());
        obj.setPhone(txtPhone.getText());
        obj.setFax(txtFax.getText());
        return obj;
    }

    @FXML
    public void onBtnCancelAction(ActionEvent event) {
        Utils.currentStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes(){
        Constraints.setTextFieldMaxLength(txtCustomerID, 5);
        Constraints.setTextFieldMaxLength(txtCompanyName, 40);
        Constraints.setTextFieldMaxLength(txtContactName, 30);
        Constraints.setTextFieldMaxLength(txtContactTitle, 30);
        Constraints.setTextFieldMaxLength(txtAddress, 60);
        Constraints.setTextFieldMaxLength(txtCity, 15);
        Constraints.setTextFieldMaxLength(txtRegion, 15);
        Constraints.setTextFieldMaxLength(txtPostalCode, 10);
        Constraints.setTextFieldMaxLength(txtCountry, 15);
        Constraints.setTextFieldMaxLength(txtPhone, 24);
        Constraints.setTextFieldMaxLength(txtFax, 24);
    }

    public void updateFormData() {
        if (entity == null) throw new IllegalStateException("Entity was null");
        txtCustomerID.setText(entity.getCustomerID());
        txtCompanyName.setText(entity.getCompanyName());
        txtContactName.setText(entity.getContactName());
        txtContactTitle.setText(entity.getContactTitle());
        txtAddress.setText(entity.getAddress());
        txtCity.setText(entity.getCity());
        txtRegion.setText(entity.getRegion());
        txtPostalCode.setText(entity.getPostalCode());
        txtCountry.setText(entity.getCountry());
        txtPhone.setText(entity.getPhone());
        txtFax.setText(entity.getFax());
    }
}
