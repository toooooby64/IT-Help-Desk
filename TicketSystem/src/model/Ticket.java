package model;

public class Ticket {
	private long ticketID;
	private String nameOfTicket;
	private long userID;
	private String contact;
	private String description;
	private String status;
	private boolean assigned;
	private long assignedTo;

	public Ticket() {
	}

	public Ticket(long ticketID, String nameOfTicket, long nameOfUser, String contact, String description,
			String status, boolean assigned, long assignedTo) {
		this.ticketID = ticketID;
		this.nameOfTicket = nameOfTicket;
		this.userID = nameOfUser;
		this.contact = contact;
		this.description = description;
		this.status = status;
		this.assigned = assigned;
		this.assignedTo = assignedTo;
	}

	public long getTicketID() {
		return ticketID;
	}

	public void setTicketID(long ticketID) {
		this.ticketID = ticketID;
	}

	public String getNameOfTicket() {
		return nameOfTicket;
	}

	public void setNameOfTicket(String nameOfTicket) {
		this.nameOfTicket = nameOfTicket;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	@Override
	public String toString() {
		return "Ticket [ticketID=" + ticketID + ", nameOfTicket=" + nameOfTicket + ", userID=" + userID + ", contact="
				+ contact + ", description=" + description + ", status=" + status + "]";
	}

	public long getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(long assignedTo) {
		this.assignedTo = assignedTo;
	}

}
