module com.lucasantonio.projetobd {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lucasantonio.projetobd to javafx.fxml;
    opens com.lucasantonio.projetobd.models to javafx.base;
    exports com.lucasantonio.projetobd;
}