package com.lucasantonio.projetobd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        try {
            ScrollPane scrollPane = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            primaryStage.setTitle("Sample JavaFX application");
            mainScene = new Scene(scrollPane);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    public static void main(String[] args) {
        launch();
    }
}