package nf.co.rogerioaraujo.questao_02.node2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    public static Connection getMySql() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/questao_04",
                "mysql","docker");
    }

    public static Connection getPostgres() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/questao_04",
                "postgres","docker");
    }
}
