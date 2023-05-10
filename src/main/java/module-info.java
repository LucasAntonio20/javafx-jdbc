module com.lucasantonio.projetobd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens com.lucasantonio.projetobd to javafx.fxml;
    opens com.lucasantonio.projetobd.model.entities to javafx.base;
    opens com.lucasantonio.projetobd.model.dao to javafx.base;
    exports com.lucasantonio.projetobd;

}