package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexao {
	public static Connection obterConexao() throws SQLException {
	    //Estabelecer uma conexão com o banco de dados.
	String url = "jdbc:mysql://localhost:3306/pw";
	String user = "root";
	String password = "aula321";
	    Connection conn = DriverManager
	        .getConnection(url, user, password);
	    return conn;
	}
}