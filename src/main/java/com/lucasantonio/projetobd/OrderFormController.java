package com.lucasantonio.projetobd;

import com.lucasantonio.projetobd.db.DbException;
import com.lucasantonio.projetobd.listeners.DataChangeListener;
import com.lucasantonio.projetobd.model.entities.Customer;
import com.lucasantonio.projetobd.model.entities.Order;
import com.lucasantonio.projetobd.model.exceptions.ValidationException;
import com.lucasantonio.projetobd.model.services.CustomerService;
import com.lucasantonio.projetobd.model.services.OrderService;
import com.lucasantonio.projetobd.util.Alerts;
import com.lucasantonio.projetobd.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderFormController {

    private Order entity;

    private OrderService service;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField txtOrderID;
    @FXML
    private TextField txtCustomerID;
    @FXML
    private TextField txtEmployeeID;
    @FXML
    private TextField txtOrderDate;
    @FXML
    private TextField txtRequiredDate;
    @FXML
    private TextField txtShippedDate;
    @FXML
    private TextField txtShipVia;
    @FXML
    private TextField txtFreight;
    @FXML
    private TextField txtShipName;
    @FXML
    private TextField txtShipAddress;
    @FXML
    private TextField txtShipCity;
    @FXML
    private TextField txtShipRegion;
    @FXML
    private TextField txtShipPostalCode;
    @FXML
    private TextField txtShipCountry;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnNewProduct;
    @FXML
    private Label labelErrorName;

    @FXML
    public void onBtnSaveAction(ActionEvent event) {
        if (entity == null) throw new IllegalStateException("Entity was null");
        if (service == null) throw new IllegalStateException("Service was null");

        try {
            entity = getFormData();
            service.save(entity);
            notifyDataChangeListener();
            Utils.currentStage(event).close();
        } catch (ValidationException e)  {
            setErrorMessages(e.getErros());
        }
        catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void notifyDataChangeListener() {
        for (DataChangeListener listerner: dataChangeListeners) {
            listerner.onDataChanged();
        }
    }

    @FXML
    public void onBtnCancelAction(ActionEvent event) {
        Utils.currentStage(event).close();
    }

    @FXML
    public void onBtnNewProductAction() {
        System.out.println("New Product");
    }

    public void setOrder(Order entity){
        this.entity = entity;
    }

    public void setOrderService(OrderService service){
        this.service = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    private Order getFormData() {
        Order obj = new Order();

        ValidationException exception = new ValidationException("Validation error");

        obj.setOrderID(Integer.parseInt(txtOrderID.getText()));
        obj.setCustomerID(txtCustomerID.getText());
        obj.setEmployeeID(Integer.parseInt(txtEmployeeID.getText()));
        obj.setOrderDate(Date.valueOf(txtOrderDate.getText()));
        obj.setRequiredDate(Date.valueOf(txtRequiredDate.getText()));
        obj.setShippedDate(Date.valueOf(txtShippedDate.getText()));
        obj.setShipVia(Integer.parseInt(txtShipVia.getText()));
        obj.setFreight(Double.parseDouble(txtFreight.getText()));
        obj.setShipName(txtShipName.getText());
        obj.setShipAddress(txtShipAddress.getText());
        obj.setShipCity(txtShipCity.getText());
        obj.setShipRegion(txtShipRegion.getText());
        obj.setShipPostalCode(txtShipPostalCode.getText());
        obj.setShipCountry(txtShipCountry.getText());

        if (exception.getErros().size() > 0) throw exception;
        return obj;
    }

    private void setErrorMessages(Map<String, String> errors){
        Set<String> fields = errors.keySet();

        if (fields.contains("CompanyName")){
            labelErrorName.setText(errors.get("CompanyName"));
        }
    }
}
