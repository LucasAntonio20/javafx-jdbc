package com.lucasantonio.projetobd.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private Connection connection;

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Northwind";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (SQLException e) {
            System.out.println("Falha ao estabelecer conexão.");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Falha ao fechar conexão.");
            e.printStackTrace();
        }
    }
}
