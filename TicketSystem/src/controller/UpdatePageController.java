package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Employee;
import model.Ticket;
import model.TicketModel;

public class UpdatePageController implements Initializable {

	private TicketModel model;
	private Parent root;
	private Scene scene;
	private Stage stage;
	private Ticket ticket;
	private String type;
	private Employee employee;
	@FXML
	private TextField userID;
	@FXML
	private TextField ticketID;
	@FXML
	private TextField title;
	@FXML
	private TextField contact;
	@FXML
	private TextField assign;
	@FXML
	private RadioButton unassign;
	@FXML
	private TextArea description;
	@FXML
	private ChoiceBox<String> status;
	@FXML
	private Button homeButton;
	@FXML
	private Button viewButton;
	@FXML
	private Button assignButton;

	public UpdatePageController(TicketModel model, Ticket ticket, String type, Employee employee) {
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
		description.setText(ticket.getDescription());
		description.setWrapText(true);
		status.getItems().addAll(choiceBoxOptions());
		status.setValue(ticket.getStatus());
	}

	public void goToViewTickets(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+type+"/ViewTicket.fxml"));
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
	public void goToAssignScene(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+type+"/AssignTicketTable.fxml"));
		loader.setControllerFactory(c -> {
			return new AssignTicketTableController(model,employee, type);
		});
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	public void goToHomePage(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+type+"/HomeView.fxml"));
		loader.setControllerFactory(c -> {
			return new HomePageController(model,employee,type);
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

	public void adminUpdateTable() {
		ticket.setContact(contact.getText());
		ticket.setDescription(description.getText());
		ticket.setStatus(status.getValue());
		if (assign.getText().isEmpty()) {
			ticket.setAssignedTo(-1);
		} else
			ticket.setAssignedTo(Long.valueOf(assign.getText()));
		ticket.setAssigned(!unassign.isSelected());
		model.updateTicket(ticket);
		showConfirmAlert();
	}
	
	public void itUpdateTable() {
		ticket.setContact(contact.getText());
		ticket.setDescription(description.getText());
		ticket.setStatus(status.getValue());
		model.updateTicket(ticket);
		showConfirmAlert();
		
	}

	private ObservableList<String> choiceBoxOptions() {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("Open");
		list.add("Closed");
		list.add("In Progress");
		return list;
	}
	
	private void showConfirmAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Ticket Updated");
		alert.setHeaderText("This ticket has been updated!");
		//This wont be able to scale if multiple people were to submit a ticket at the same time but it works for this specific application
		alert.setContentText("Thank you!" );
		alert.showAndWait();
		alert.onCloseRequestProperty().set(null);
	}
}
