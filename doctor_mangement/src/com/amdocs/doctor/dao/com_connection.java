package com.amdocs.doctor.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class com_connection {
	static Connection con;

   

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            con= DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:XE","system","admin");
            return con;
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC driver not found", e);
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            	
                e.printStackTrace();
            }
        }
    }
}
