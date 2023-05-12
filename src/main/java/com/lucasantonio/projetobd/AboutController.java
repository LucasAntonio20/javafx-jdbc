package com.lucasantonio.projetobd;

import com.lucasantonio.projetobd.model.entities.Customer;
import com.lucasantonio.projetobd.model.services.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AboutController implements Initializable {

    private CustomerService service;

    @FXML
    private TextField txtCountryName;
    @FXML
    private TableView<Customer> tableViewCustomer;
    @FXML
    private TableColumn<Customer, String> tableColumnCustomerID;
    @FXML
    private TableColumn<Customer, String> tableColumnCompanyName;
    @FXML
    private Button btnClick;
    private ObservableList<Customer> obsList;

    public void setCustomerService(CustomerService service) {
        this.service = service;
    }

    @FXML
    public void onBtnClickAction() {
        setCustomerService(new CustomerService());
        if (service == null) throw new IllegalStateException("Service was null");
        List<Customer> list = service.getByCountry(txtCountryName.getText());
        obsList = FXCollections.observableArrayList(list);
        tableViewCustomer.setItems(obsList);
    }

    public void updateTableView(){


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tableColumnCompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewCustomer.prefHeightProperty().bind(stage.heightProperty());
    }


}
