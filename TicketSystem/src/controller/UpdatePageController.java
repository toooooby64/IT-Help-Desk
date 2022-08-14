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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Ticket;
import model.TicketModel;

public class UpdatePageController implements Initializable {

	private TicketModel model;
	private Parent root;
	private Scene scene;
	private Stage stage;
	private Ticket ticket;
	private String type;
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
	RadioButton unassign;
	@FXML
	TextArea description;
	@FXML
	ChoiceBox<String> status;

	public UpdatePageController(TicketModel model, Ticket ticket) {
		this.model = model;
		this.ticket = ticket;
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
			return new ViewTicketController(model, type);
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
			return new AssignTicketTableController(model, type);
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
			return new HomePageController(model, type);
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

	public void updateTable() {
		ticket.setContact(contact.getText());
		ticket.setDescription(description.getText());
		ticket.setStatus(status.getValue());
		if (assign.getText().isEmpty()) {
			ticket.setAssignedTo(-1);
		} else
			ticket.setAssignedTo(Long.valueOf(assign.getText()));
		ticket.setAssigned(!unassign.isSelected());
		model.updateTicket(ticket);
	}

	private ObservableList<String> choiceBoxOptions() {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("Open");
		list.add("Closed");
		list.add("In Progress");
		return list;
	}
}
