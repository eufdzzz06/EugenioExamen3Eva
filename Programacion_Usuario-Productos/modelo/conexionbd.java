package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionbd {
    private static final String URL = "jdbc:mysql://localhost:3306/nombre_base_datos";
    private static final String USER = "usuario";
    private static final String PASSWORD = "contraseña";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

