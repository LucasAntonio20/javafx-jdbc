package com.lucasantonio.projetobd;

import com.lucasantonio.projetobd.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

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

    @FXML
    public void onBtnSaveAction() {
        System.out.println("Salvei");
    }

    @FXML
    public void onBtnCancelAction() {
        System.out.println("Cancelei");
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
}
