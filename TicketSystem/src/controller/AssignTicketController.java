package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Employee;
import model.Ticket;
import model.TicketModel;

public class AssignTicketController implements Initializable {
	private String type;
	private TicketModel model;
	private Parent root;
	private Scene scene;
	private Stage stage;
	private Ticket ticket;
	private Employee employee;
	@FXML
	Button backButton;
	@FXML
	Button updateButton;
	@FXML
	TextField userID;
	@FXML
	TextField ticketID;
	@FXML
	TextField title;
	@FXML
	TextField contact;
	@FXML
	TextField assign;
	@FXML
	TextArea description;
	@FXML
	ChoiceBox<String> status;

	public AssignTicketController(TicketModel model, Ticket ticket,String type, Employee employee) {
		this.model = model;
		this.ticket = ticket;
		this.type = type;
		this.employee = employee;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ticketID.setText(Long.toString(ticket.getTicketID()));
		userID.setText(Long.toString(ticket.getUserID()));
		title.setText(ticket.getNameOfTicket());
		contact.setText(ticket.getContact());
		assign.setText(Long.toString(ticket.getAssignedTo()));
		description.setText(ticket.getDescription());
		description.setWrapText(true);
		status.setValue(ticket.getStatus());
	}

	public void goToViewTickets(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/ViewTicket.fxml"));
		loader.setControllerFactory(c -> {
			return new ViewTicketController(model,employee, type);
		});
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void goToHomePage(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/HomeView.fxml"));
		loader.setControllerFactory(c -> {
			return new HomePageController(model,employee, type);
		});
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scene = new Scene(root);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void goToCreatePage(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/CreateTicketView.fxml"));
		loader.setControllerFactory(c -> {
			return new CreateTicketController(model,employee,type);
		});
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void updateTable() {
		if (Long.valueOf(assign.getText()) == -1) {
			ticket.setAssigned(false);
		} else
			ticket.setAssigned(true);
		ticket.setAssignedTo(Long.valueOf(assign.getText()));
		model.updateTicket(ticket);
		System.out.println("worked");
	}
}
