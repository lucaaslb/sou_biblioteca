/*
 * Projeto Integrado 3� Semestre CCO
 * 
 *  Author: Lucas Lacerda
 */
package dbSQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import Library.auxData;

public class dbConnection {

	private Connection c;

	public boolean connect() {
		String url = "jdbc:sqlite:database/dblibrary.sqlite";

		try {
			this.c = DriverManager.getConnection(url);
			// System.out.println("conectado");
			return true;

		} catch (SQLException exc) {
			System.err.println(exc.getMessage());
			return false;
		}

	}

	public void disconnect() {

		try {
			if (c != null) {
				this.c.close();
				// System.out.println("desconectado");
			}

		} catch (SQLException exc) {
			System.err.println(exc.getMessage());
		}

	}

	/* Metodo que insere no banco com o login do facebook */
	public void insertStudent(String ra, String nome, String email, String token, String password) {

		Statement stmt = null;
		try {
			connect();

			stmt = c.createStatement();
			String sql = "INSERT INTO STUDENT (RA, NAME, EMAIL, TOKEN, PASSWORD) " + "VALUES ('" + ra + "', '" + nome + "', '"
					+ email + "', '" + token + "', '" + password + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			disconnect();
			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "RA ou Email ja cadastrado!");
		}
	}

	
	public String[] student(String email) {

		String[] dados = new String[4];
		String query = "SELECT * FROM STUDENT WHERE EMAIL = '" + email + "'";
		Statement stm;
		ResultSet rs;

		try {
			connect();
			stm = c.createStatement();
			rs = stm.executeQuery(query);

			while (rs.next()) {
				dados[0] = rs.getString("RA");
				dados[1] = rs.getString("NAME");
				dados[2] = rs.getString("EMAIL");
				dados[3] = rs.getString("TOKEN");
			}
			stm.close();
			rs.close();
			disconnect();

		} catch (SQLException exc) {

		}
		return dados;
	}

	/* obtem dados com login apartir da senha */
	public String[] studentLogin(String RA) {

		String[] dados = new String[4];
		String query = "SELECT * FROM STUDENT WHERE RA = '" + RA + "'";
		Statement stm;
		ResultSet rs;

		try {
			connect();
			stm = c.createStatement();
			rs = stm.executeQuery(query);

			while (rs.next()) {
				dados[0] = rs.getString("RA");
				dados[1] = rs.getString("NAME");
				dados[2] = rs.getString("EMAIL");
				dados[3] = rs.getString("TOKEN");
			}
			stm.close();
			rs.close();
			disconnect();

		} catch (SQLException exc) {

		}
		return dados;
	}

	public void insertBook(String title, String author, String isbn, int quant) {
		Statement stmt = null;

		try {
			connect();
			stmt = c.createStatement();
			String sql = "INSERT INTO BOOK(TITLE, AUTHOR, ISBN, QUANT) VALUES ('" + title + "', '" + author + "', '"
					+ isbn + "', '" + quant + "')";

			stmt.executeUpdate(sql);
			stmt.close();

			disconnect();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Livro ja cadastrado na base de dados");
		}
	}

	public Vector<String> listBooks() {

		Vector<String> books = new Vector<String>();
		
		Statement stmt = null;
		ResultSet rs;
		String sql = "SELECT * FROM BOOK ORDER BY TITLE";

		try {
			connect();
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				final Object value = rs.getString("TITLE");
				books.add(value.toString());
			}

			stmt.close();
			rs.close();
			disconnect();
		} catch (SQLException exc) {

		}

		return books;
	}

	/* conta a quantidade de livros cadastrados no banco */

	/* Retorna informa��es do livro que for passado como parametro */

	public String[] bookInformations(String title) {

		String[] dados = new String[4];
		String query = "SELECT * FROM BOOK WHERE TITLE = '" + title + "'";

		Statement stm;
		ResultSet rs;

		try {
			connect();
			stm = c.createStatement();
			rs = stm.executeQuery(query);

			while (rs.next()) {
				dados[0] = rs.getString("ID");
				dados[1] = rs.getString("AUTHOR");
				dados[2] = rs.getString("ISBN");
				dados[3] = rs.getString("QUANT");
			}

			stm.close();
			rs.close();
			disconnect();

		} catch (SQLException exc) {

		}
		return dados;
	}

	/* Verifica se o aluno do login ja esta na base de dados */

	public boolean UserExist(String email) {

		String query = "SELECT * FROM STUDENT WHERE EMAIL = '" + email + "'";
		String RA = null;

		Statement stm;
		ResultSet rs;

		try {

			connect();
			stm = c.createStatement();
			rs = stm.executeQuery(query);

			while (rs.next()) {
				RA = rs.getString("RA");
			}

			rs.close();
			stm.close();
			disconnect();
		} catch (SQLException exc) {

		}
		return RA != null;
	}

	/*
	 * METODOS RELACIONADOS AO EMPRESTIMO E DEVOLU��O DE LIVROS
	 */

	/* Localiza o livro pela ID e retorna o title */
	public String bookTitle(String ID) {

		String title = null;
		String query = "SELECT * FROM BOOK WHERE ID = '" + ID + "'";

		Statement stm;
		ResultSet rs;

		try {
			connect();
			stm = c.createStatement();
			rs = stm.executeQuery(query);

			while (rs.next()) {
				title = rs.getString("TITLE");
			}

			stm.close();
			rs.close();
			disconnect();

		} catch (SQLException exc) {

		}
		return title;
	}

	auxData date = new auxData();

	/* Vincula um livro a um aluno (insert) */
	public void Rent(String title, String ra) {

		Statement stmt = null;
		try {
			connect();

			stmt = c.createStatement();
			String sql = "INSERT INTO RENT (BOOK_TITLE, USER_RA, RENTAL_DATE) " + "VALUES ('" + title + "', '" + ra
					+ "', '" + date.getDateTime() + "')";
			stmt.executeUpdate(sql);
			stmt.close();

			disconnect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	/*
	 * Verifica se o ra informado possui algum livro e devolve um vector com os
	 * livros pra preencher a jlist
	 */

	public Vector<String> listBookUser(String ra) {

		Vector<String> books = new Vector<String>();

		Statement stmt = null;
		ResultSet rs;
		String sql = "SELECT * FROM RENT WHERE USER_RA = '" + ra + "'";

		try {
			connect();
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				final Object value = rs.getString("BOOK_TITLE");
				books.add(value.toString());

			}

			stmt.close();
			rs.close();
			disconnect();
		} catch (SQLException exc) {

		}

		return books;
	}

	public String dateRent(String title, String ra) {
		String date = "";

		Statement stmt = null;
		ResultSet rs;
		String sql = "SELECT * FROM RENT WHERE BOOK_TITLE = '" + title + "' AND USER_RA = '" + ra + "'";

		try {
			connect();
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				date = rs.getString("RENTAL_DATE");
			}

			stmt.close();
			rs.close();
			disconnect();
		} catch (SQLException exc) {

		}

		return date;
	}

	/* Metodo que realiza o login verificando ra e senha */
	public boolean Login(String user, String pass) {

		String query = "SELECT * FROM STUDENT WHERE RA = '" + user + "'";
		String RA = "";
		String pass_origin = "";

		Statement stm;
		ResultSet rs;

		try {

			connect();
			stm = c.createStatement();
			rs = stm.executeQuery(query);

			while (rs.next()) {
				RA = rs.getString("RA");
				pass_origin = rs.getString("PASSWORD");
			}

			rs.close();
			stm.close();
			disconnect();

			if (user.equals(RA) && pass.equals(pass_origin)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "RA ou senha invalido");
				return false;
			}

		} catch (SQLException exc) {
			return false;
		}

	}

	/* Verifica se o usuario possui token de acesso ao facebook */
	public boolean TokenIsEmpty(String ra) {
		String query = "SELECT * FROM STUDENT WHERE RA = '" + ra + "'";
		String token = "";

		Statement stm;
		ResultSet rs;

		try {

			connect();
			stm = c.createStatement();
			rs = stm.executeQuery(query);

			while (rs.next()) {
				token = rs.getString("TOKEN");
			}

			rs.close();
			stm.close();
			disconnect();
		} catch (SQLException exc) {

		}
		return token != "";

	}

	/* cadastra o token do aluno que logou sem o facebook */
	public void updateToken(String RA, String token) {
		// TODO Auto-generated method stub
		String update = "UPDATE STUDENT SET TOKEN = '" + token + "' WHERE RA = '" + RA + "'";
		Statement stmt = null;
		try {
			connect();
			stmt = c.createStatement();

			stmt.executeUpdate(update);
			stmt.close();
			disconnect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void returnBook(String ra, String title) {
		String delete = "DELETE FROM RENT WHERE USER_RA = '" + ra + "' AND BOOK_TITLE = '" + title + "'";
		Statement stmt = null;
		try {
			connect();
			stmt = c.createStatement();

			stmt.executeUpdate(delete);
			stmt.close();
			disconnect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
