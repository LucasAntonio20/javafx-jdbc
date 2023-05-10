package com.lucasantonio.projetobd;

import com.lucasantonio.projetobd.model.services.CustomerService;
import com.lucasantonio.projetobd.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainController implements Initializable {

    @FXML
    private MenuItem menuItemCustomers;
    @FXML
    private MenuItem menuItemOrders;
    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuIemCustomersAction(){
        loadView("CustomersList.fxml", (CustomersListController controller) -> {
            controller.setCustomerService(new CustomerService());
            controller.updateTableView();
        });
    }

    @FXML
    public void onMenuItemOrdersAction(){
        System.out.println("onMenuItemOrdersAction");
    }

    @FXML
    public void onMenuItemAboutAction(){
        loadView("About.fxml", x -> {});
    }

    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializeAction) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVbox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVbox.getChildren().get(0);
            mainVbox.getChildren().clear();
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVbox.getChildren());

            T controller = loader.getController();
            initializeAction.accept(controller);
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}