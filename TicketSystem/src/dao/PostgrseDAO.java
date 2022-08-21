package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.SearchTypes;
import model.Ticket;

public class PostgrseDAO implements TicketDAO {

	private Connection connection;
	private final String url = "jdbc:postgresql://localhost/ticketSystem";
	private final String username = "postgres";
	private final String passoword = "1973";

	@Override
	public void connect() throws Exception {
		connection = DriverManager.getConnection(url, username, passoword);
		if (connection != null) {
			System.out.println("Connected to Ticket Server");
		} else
			System.out.println("Failed to connect to server");
	}

	@Override
	public void close() throws Exception {
		connection.close();
		System.out.println("Connection to server is closed.");
	}

	@Override
	public void insertTicket(Ticket ticket) {
		Statement statement;
		String query = "INSERT INTO \"Tickets\"(\"title\", \"userID\", \"contactInfo\", \"description\", \"status\", \"assigned\", \"assignedTo\") ";
		String query2 = "VALUES('" + ticket.getNameOfTicket() + "' , '" + ticket.getUserID() + "', '"
				+ ticket.getContact() + "', '" + ticket.getDescription() + "', '" + ticket.getStatus() + "', '"
				+ ticket.isAssigned() + "' , " + ticket.getAssignedTo() + " )";
		try {
			statement = connection.createStatement();
			int result = statement.executeUpdate(query + query2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean updateTicket(Ticket ticket) {
		
		Statement statement;
		System.out.println(ticket.getAssignedTo());
		
		String query = "UPDATE \"Tickets\" SET \"status\" = '" + ticket.getStatus() + "', \"contactInfo\" = " + "'"
				+ ticket.getContact() + "'" + ", \"description\" = " + "'" + ticket.getDescription() + "'" + ","
				+ "\"assigned\" = " + ticket.isAssigned() + ", \"assignedTo\" = " + "'" + ticket.getAssignedTo() + "'"
				+ " WHERE \"ticketID\" =  " + ticket.getTicketID();
		try {
			statement = connection.createStatement();
			int result = statement.executeUpdate(query);
			System.out.println("as;ldkfjasgoihasdfnaskdfa;le");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Ticket> findAll() {
		Statement statement;
		String selectQuery = "SELECT * FROM \"Tickets\"";
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>(getRowCount());
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(selectQuery);
			while (result.next()) {
				long id = result.getLong(1);
				long userID = result.getLong(2);
				String ticketName = result.getString(3);
				String contactInfo = result.getString(4);
				String description = result.getString(5);
				boolean assigned = result.getBoolean(6);
				String status = result.getString(7);
				long assigedTo = result.getLong(8);
				Ticket foundTicket = new Ticket(id, ticketName, userID, contactInfo, description, status, assigned,
						assigedTo);
				ticketList.add(foundTicket);
			}

			return ticketList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int getNumOfOpenTickets() {
		Statement statement;
		ResultSet result;
		String unassignedCountQuery = "SELECT COUNT(*) AS openCount FROM \"Tickets\" WHERE \"status\" = 'Open'";
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(unassignedCountQuery);
			result.next();
			int unassignedCount = result.getInt("openCount");
			return unassignedCount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Ticket> allOpenTickets() {
		Statement statement;
		String openTicketQuery = "SELECT * FROM \"Tickets\" WHERE \"assigned\" IS FALSE AND \"status\" = 'Open'";
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>(getRowCount());
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(openTicketQuery);
			while (result.next()) {
				long id = result.getLong(1);
				long userID = result.getLong(2);
				String ticketName = result.getString(3);
				String contactInfo = result.getString(4);
				String description = result.getString(5);
				boolean assigned = result.getBoolean(6);
				String status = result.getString(7);
				long assigedTo = result.getLong(8);
				Ticket foundTicket = new Ticket(id, ticketName, userID, contactInfo, description, status, assigned,
						assigedTo);
				ticketList.add(foundTicket);
			}

			return ticketList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public List<Ticket> allAssignedTickets(Employee employee) {
		Statement statement;
		String openTicketQuery = "SELECT * FROM \"Tickets\" WHERE \"assignedTo\" = " + employee.getEmployeeID();
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>(getRowCount());
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(openTicketQuery);
			while (result.next()) {
				long id = result.getLong(1);
				long userID = result.getLong(2);
				String ticketName = result.getString(3);
				String contactInfo = result.getString(4);
				String description = result.getString(5);
				boolean assigned = result.getBoolean(6);
				String status = result.getString(7);
				long assigedTo = result.getLong(8);
				Ticket foundTicket = new Ticket(id, ticketName, userID, contactInfo, description, status, assigned,
						assigedTo);
				
				System.out.println(foundTicket);
				ticketList.add(foundTicket);
			}

			return ticketList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int getNumOfUnassignedTickets() {
		Statement statement;
		ResultSet result;
		String unassignedCountQuery = "SELECT COUNT(*) AS unassignCount FROM \"Tickets\" WHERE ASSIGNED IS FALSE AND \"status\" = 'Open'";
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(unassignedCountQuery);
			result.next();
			int unassignedCount = result.getInt("unassignCount");
			return unassignedCount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	private int getRowCount() {
		Statement statement;
		ResultSet result;
		String rowCountQuery = "SELECT COUNT(*) AS rowcount FROM \"Tickets\"";
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(rowCountQuery);
			result.next();
			int rowCount = result.getInt("rowcount");

			return rowCount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
