package com.lucasantonio.projetobd;

import com.lucasantonio.projetobd.listeners.DataChangeListener;
import com.lucasantonio.projetobd.model.entities.Customer;
import com.lucasantonio.projetobd.model.entities.Order;
import com.lucasantonio.projetobd.model.services.CustomerService;
import com.lucasantonio.projetobd.model.services.OrderService;
import com.lucasantonio.projetobd.util.Alerts;
import com.lucasantonio.projetobd.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderListController implements Initializable, DataChangeListener {

    private OrderService service;

    @FXML
    private TableView<Order> tableViewOrder;

    @FXML
    private TableColumn<Order, String> tableColumnOrderID;
    @FXML
    private TableColumn<Order, String> tableColumnCustomerID;
    @FXML
    private TableColumn<Order, String> tableColumnEmployeeID;
    @FXML
    private TableColumn<Order, String> tableColumnOrderDate;
    @FXML
    private TableColumn<Order, String> tableColumnRequiredDate;
    @FXML
    private TableColumn<Order, String> tableColumnShippedDate;
    @FXML
    private TableColumn<Order, String> tableColumnShipVia;
    @FXML
    private TableColumn<Order, String> tableColumnFreight;
    @FXML
    private TableColumn<Order, String> tableColumnShipName;
    @FXML
    private TableColumn<Order, String> tableColumnShipAddress;
    @FXML
    private TableColumn<Order, String> tableColumnShipCity;
    @FXML
    private TableColumn<Order, String> tableColumnShipRegion;
    @FXML
    private TableColumn<Order, String> tableColumnShipPostalCode;
    @FXML
    private TableColumn<Order, String> tableColumnShipCountry;
    @FXML
    private Button btnNew;

    private ObservableList<Order> obsList;

    @FXML
    public void onBtnNewAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        Order obj = new Order();
        createDialogForm(obj, "OrderForm.fxml", parentStage);
    }

    public void setOrderService(OrderService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnOrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        tableColumnCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tableColumnEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        tableColumnOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tableColumnRequiredDate.setCellValueFactory(new PropertyValueFactory<>("requiredDate"));
        tableColumnShippedDate.setCellValueFactory(new PropertyValueFactory<>("shippedDate"));
        tableColumnShipVia.setCellValueFactory(new PropertyValueFactory<>("shipVia"));
        tableColumnFreight.setCellValueFactory(new PropertyValueFactory<>("freight"));
        tableColumnShipName.setCellValueFactory(new PropertyValueFactory<>("shipName"));
        tableColumnShipAddress.setCellValueFactory(new PropertyValueFactory<>("shipAddress"));
        tableColumnShipCity.setCellValueFactory(new PropertyValueFactory<>("shipCity"));
        tableColumnShipRegion.setCellValueFactory(new PropertyValueFactory<>("shipRegion"));
        tableColumnShipPostalCode.setCellValueFactory(new PropertyValueFactory<>("shipPostalCode"));
        tableColumnShipCountry.setCellValueFactory(new PropertyValueFactory<>("shipCountry"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewOrder.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView(){
        if (service == null) throw new IllegalStateException("Service was null");
        List<Order>  list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewOrder.setItems(obsList);
    }

    private void createDialogForm(Order obj, String absoluteName, Stage parentStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            OrderFormController controller = loader.getController();
            controller.setOrder(obj);
            controller.setOrderService(new OrderService());
            controller.subscribeDataChangeListener(this);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter Order data");
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
}
