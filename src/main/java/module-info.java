module com.lucasantonio.projetobd {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lucasantonio.projetobd to javafx.fxml;
    exports com.lucasantonio.projetobd;
}