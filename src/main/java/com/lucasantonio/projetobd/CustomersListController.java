package com.lucasantonio.projetobd;

import com.lucasantonio.projetobd.listeners.DataChangeListener;
import com.lucasantonio.projetobd.model.entities.Customer;
import com.lucasantonio.projetobd.model.services.CustomerService;
import com.lucasantonio.projetobd.util.Alerts;
import com.lucasantonio.projetobd.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomersListController implements Initializable, DataChangeListener {

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
    private TableColumn<Customer, Customer> tableColumnEDIT;
    @FXML
    private Button btnNew;
    private ObservableList<Customer> obsList;

    @FXML
    public void onBtnNewAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        Customer obj = new Customer();
        createDialogForm(obj, "CustomerForm.fxml", parentStage);
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
        initEditButtons();
    }

    private void createDialogForm(Customer obj, String absoluteName, Stage parentStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            CustomerFormController controller = loader.getController();
            controller.setCustomer(obj);
            controller.setCustomerService(new CustomerService());
            controller.subscribeDataChangeListener(this);
            controller.updateFormData();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter Customer data");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();

        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void onDataChanged() {
        updateTableView();
    }

    private void initEditButtons() {
        tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnEDIT.setCellFactory(param -> new TableCell<Customer, Customer>() {
            private final Button button = new Button("edit");
            @Override
            protected void updateItem(Customer obj, boolean empty) {
                super.updateItem(obj, empty);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> createDialogForm(
                                obj, "CustomerForm.fxml",Utils.currentStage(event)));
            }
        });
    }

}
