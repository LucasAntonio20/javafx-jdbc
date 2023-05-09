package com.lucasantonio.projetobd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private MenuItem menuItemCustomers;
    @FXML
    private MenuItem menuItemOrders;
    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuIemCustomersAction(){
        System.out.println("onMenuIemCustomersAction");
    }

    @FXML
    public void onMenuItemOrdersAction(){
        System.out.println("onMenuItemOrdersAction");
    }

    @FXML
    public void onMenuItemAboutAction(){
        System.out.println("onMenuItemAboutAction");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}