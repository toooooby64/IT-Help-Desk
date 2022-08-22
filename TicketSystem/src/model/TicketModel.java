package model;

import java.util.List;

import dao.TicketDAO;
import javafx.util.Callback;

public class TicketModel {

	private TicketDAO ticketDAO;
	
	public TicketModel(TicketDAO ticketDAO) {
		this.ticketDAO = ticketDAO;
	}
	
	public void addNewTicket(String nameOfTicket, long userID, String contact,
			String description) {
		Ticket newTicket = new Ticket();
		newTicket.setContact(contact);
		newTicket.setDescription(description);
		newTicket.setNameOfTicket(nameOfTicket);
		newTicket.setUserID(userID);
		newTicket.setStatus("Open");
		newTicket.setAssigned(false);
		newTicket.setAssignedTo(-1);
		
		ticketDAO.insertTicket(newTicket);
	}
	
	public void updateTicket(Ticket ticket) {
		ticketDAO.updateTicket(ticket);
	}
	
	
	public List<Ticket> findAll() {
		return ticketDAO.findAll();
	}
	
	public int getNumOfOpenTicket() {
		return ticketDAO.getNumOfOpenTickets();
	}
	public int getNumOfUnassignedTicket() {
		return ticketDAO.getNumOfUnassignedTickets();
	}
	
	public List<Ticket> findAllOpenTickets() {
		return ticketDAO.allOpenTickets();
	}

	public List<Ticket> findAssignedTickets(Employee employee) {
		return ticketDAO.allAssignedTickets(employee);
	}
	
	public List<Ticket> findTicketsByEmployeeID(Employee employee){
		return ticketDAO.getTicketsByEmployeeID(employee);
	}
	

}
