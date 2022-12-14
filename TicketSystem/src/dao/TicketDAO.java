package dao;

import java.util.List;

import model.Employee;
import model.Ticket;

public interface TicketDAO extends DAO {
	
	public void insertTicket(Ticket ticket);
	public boolean updateTicket(Ticket ticket);

	
	public List<Ticket> findAll();
	public int getNumOfOpenTickets();
	public int getNumOfUnassignedTickets();
	public List<Ticket> allOpenTickets();
	public List<Ticket> allAssignedTickets(Employee employee);
	public List<Ticket> getTicketsByEmployeeID(Employee employee);
}
