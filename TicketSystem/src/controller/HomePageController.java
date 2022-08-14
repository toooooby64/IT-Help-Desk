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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Employee;
import model.TicketModel;

public class HomePageController implements Initializable {
	@FXML
	private Button create;
	@FXML
	private Button view;
	@FXML
	private Label openTicketLabel;
	@FXML
	private Label unassignedTicketLabel;
	@FXML
	private Label name;
	private Parent root;
	private Scene scene;
	private Stage stage;
	private TicketModel model;
	private String type;
	
	public HomePageController(TicketModel model, String type) {
		this.model = model;
		this.type = type;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		openTicketLabel.setText(String.valueOf(model.getNumOfOpenTicket()));
		unassignedTicketLabel.setText(String.valueOf(model.getNumOfUnassignedTicket()));
	}
	
	public void goToCreateScene(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+type+"/CreateTicketView.fxml"));
		loader.setControllerFactory(c -> {
			return new CreateTicketController(model,type);
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
	
	public void goToViewScene(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+type+"/ViewTicket.fxml"));
		loader.setControllerFactory(c -> {
			return new ViewTicketController(model,type);
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
	public void goToAssignScene(ActionEvent event,String type) {
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


}
