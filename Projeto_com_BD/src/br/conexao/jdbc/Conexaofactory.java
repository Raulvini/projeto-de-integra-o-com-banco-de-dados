package br.conexao.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexaofactory {
    private static Connection conn;


    public static Connection getConection() throws SQLException {
        if(conn == null){
            conn = initConection();
        }else if (conn.isClosed()){
            conn = initConection();
        }
        return conn;
    }
    private static Connection initConection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:3433/vendas_online",
                    "postgres",
                    "postgres"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
