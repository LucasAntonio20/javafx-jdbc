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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomersListController implements Initializable {

    private CustomerService service;

    @FXML
    private TableView<Customer> tableViewCustomer;

    @FXML
    private TableColumn<Customer, String> tableColumnCustomerID;
    @FXML
    private TableColumn<Customer, String> tableColumnCompanyName;
    @FXML
    private TableColumn<Customer, String> tableColumnContactName;
    @FXML
    private TableColumn<Customer, String> tableColumnContactTitle;
    @FXML
    private TableColumn<Customer, String> tableColumnAddress;
    @FXML
    private TableColumn<Customer, String> tableColumnCity;
    @FXML
    private TableColumn<Customer, String> tableColumnRegion;
    @FXML
    private TableColumn<Customer, String> tableColumnPostalCode;
    @FXML
    private TableColumn<Customer, String> tableColumnCountry;
    @FXML
    private TableColumn<Customer, String> tableColumnPhone;
    @FXML
    private TableColumn<Customer, String> tableColumnFax;
    @FXML
    private Button btnNew;
    private ObservableList<Customer> obsList;

    @FXML
    public void onBtnNewAction() {
        System.out.println("onBtnNewAction");
    }

    public void setCustomerService(CustomerService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tableColumnCompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        tableColumnContactName.setCellValueFactory(new PropertyValueFactory<>("ContactName"));
        tableColumnContactTitle.setCellValueFactory(new PropertyValueFactory<>("ContactTitle"));
        tableColumnAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        tableColumnCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        tableColumnRegion.setCellValueFactory(new PropertyValueFactory<>("Region"));
        tableColumnPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        tableColumnCountry.setCellValueFactory(new PropertyValueFactory<>("Country"));
        tableColumnPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        tableColumnFax.setCellValueFactory(new PropertyValueFactory<>("Fax"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewCustomer.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView(){
        if (service == null) throw new IllegalStateException("Service was null");
        List<Customer>  list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewCustomer.setItems(obsList);
    }
}
