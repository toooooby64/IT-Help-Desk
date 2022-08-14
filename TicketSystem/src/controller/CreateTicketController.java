package controller;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.TicketModel;

public class CreateTicketController {

	private TicketModel model;
	private Parent root;
	private Scene scene;
	private Stage stage;
	@FXML
	Button submitButton;
	@FXML
	TextField title;
	@FXML
	TextField userID;
	@FXML
	TextField contact;
	@FXML
	TextArea description;
	private String type;
	public CreateTicketController(TicketModel test, String type) {
		this.model = test;
		this.type = type;
	}

	public void onSubmit(ActionEvent event) {
		if (title.getText().isEmpty() || userID.getText().isEmpty() || contact.getText().isEmpty()) {
			showErrorAlert();
		}
		model.addNewTicket(title.getText(), Long.valueOf(userID.getText()), contact.getText(), description.getText());
		showConfirmAlert();
		submitButton.setVisible(false);
	}
	
	public void goToHomePage(ActionEvent event) {
		System.out.println(type);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+type+"/HomeView.fxml"));
		loader.setControllerFactory(c -> {
			return new HomePageController(model, type);
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
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
	
	public void restartForm(ActionEvent event) {
		title.setText(null);
		userID.setText(null);
		contact.setText(null);
		description.setText(null);
		submitButton.setVisible(true);
	}

	private void showErrorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Please fill out all requried fields.");
		alert.setContentText("One or multiple required fields are blank.");
		alert.showAndWait();
	}
	
	private void showConfirmAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Ticket Confirmed");
		alert.setHeaderText("Your ticket has been submitted!");
		//This wont be able to scale if multiple people were to submit a ticket at the same time but it works for this specific application
		alert.setContentText("Your ticket number is \"ticket number\" \n Thank you!" );
		alert.showAndWait();
		alert.onCloseRequestProperty().set(null);
	}
	

}