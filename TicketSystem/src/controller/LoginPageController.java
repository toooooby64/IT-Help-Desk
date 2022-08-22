package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Employee;
import model.EmployeeModel;
import model.Ticket;
import model.TicketModel;

public class LoginPageController {

	@FXML
	PasswordField password;
	@FXML
	TextField employeeID;
	@FXML
	Button signInButton;
	private Parent root;
	private Scene scene;
	private Stage stage;
	private TicketModel model;
	private EmployeeModel employeeModel;
	private Employee foundEmployee; 
	
	public LoginPageController(EmployeeModel employeeModel, TicketModel model) {
		this.model = model;
		this.employeeModel = employeeModel;
	}



	public void signIn(ActionEvent event) {
		
		if (passwordCheck(Long.valueOf(employeeID.getText()), password.getText()) && foundEmployee.getTitle().equals("Admin")) {
			adminLogin(event);
		}else if (passwordCheck(Long.valueOf(employeeID.getText()), password.getText()) && foundEmployee.getTitle().equals("IT")) {
			itLogin(event);
		}
		else staffLogin(event);
		
	}
	
	private boolean passwordCheck(long id, String password) {
		foundEmployee = employeeModel.getEmployeeByID(id);
		if (foundEmployee == null) showErrorAlert();
		if (password.equals(foundEmployee.getPassword())) {
			return true;
		}
		return false;
	}
	
	private void adminLogin(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/HomeView.fxml"));
		loader.setControllerFactory(c -> {
			return new HomePageController(model,foundEmployee,"admin");
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
	
	private void itLogin(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IT/HomeView.fxml"));
		loader.setControllerFactory(c -> {
			return new HomePageController(model,foundEmployee,"IT");
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
	
	private void staffLogin(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/staff/HomeView.fxml"));
		loader.setControllerFactory(c -> {
			return new HomePageController(model,foundEmployee,"staff");
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
	
	private void showErrorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Invalid Credentials");
		alert.setContentText("Employee ID or password is incorrect try again!");
		alert.showAndWait();
	}
	
}
