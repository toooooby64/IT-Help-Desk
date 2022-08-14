package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Employee;
import model.Ticket;

public class PostgrseEmployeeDAO implements EmployeeDAO{
	private Connection connection;
	private final String url = "jdbc:postgresql://localhost/ticketSystem";
	private final String username = "postgres";
	private final String passoword = "1973";
	@Override
	public void connect() throws Exception {
		connection = DriverManager.getConnection(url, username, passoword);
		if (connection != null) {
			System.out.println("Connected to Employee Server");
		} else
			System.out.println("Failed to connect to server");
	}

	@Override
	public void close() throws Exception {
		connection.close();
		System.out.println("Connection to server is closed.");
	}

	@Override
	public Employee getEmployeeByID(long ID) {
		Statement statement;
		String query = "SELECT * FROM employees WHERE \"employeeID\" = " + String.valueOf(ID);
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				long id = result.getLong(1);
				String firstName = result.getString(2);
				String lastName = result.getString(3);
				String title = result.getString(4);
				String password = result.getString(5);
				Employee foundEmployee = new Employee(id,firstName,lastName,title,password);
				return foundEmployee;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}



}
