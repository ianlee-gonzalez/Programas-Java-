package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Conexão com o banco de dados
 * 
 * @author ian.lggonzalez
 * @version 1.0
 */
public class DAO {
	// parametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.45.100:3306/carrinhodecompras";
	private String user = "dba";
	private String password = "123@Senac";

	/**
	 * Metodo resposavel pela conexão com o banco *
	 * 
	 * @return con
	 */
	public Connection conectar() {
		// alinha abaixo cria um objeto com nome con
		Connection con = null;
		// tratamento de exceções
		try {
			// as duas linhas abaixo estabelecem a conexão
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
