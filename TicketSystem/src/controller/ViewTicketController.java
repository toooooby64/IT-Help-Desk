package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
import model.Ticket;
import model.TicketModel;

public class ViewTicketController implements Initializable {

	private TicketModel model;
	private Parent root;
	private Scene scene;
	private Stage stage;
	private Employee employee;
	private ObservableList<Ticket> items;

	@FXML
	private AnchorPane errorMessage;
	@FXML
	private Button backButton;
	@FXML
	private Button updateButton;
	@FXML
	private TextField searchField;
	@FXML
	private TableView<Ticket> table;
	@FXML
	private TableColumn<Ticket, Long> idCol;
	@FXML
	private TableColumn<Ticket, String> titleCol;
	@FXML
	private TableColumn<Ticket, String> userCol;
	@FXML
	private TableColumn<Ticket, String> contactCol;
	@FXML
	private TableColumn<Ticket, String> descriptionCol;
	@FXML
	private TableColumn<Ticket, Boolean> statusCol;
	@FXML
	private TableColumn<Ticket, Boolean> assigned;
	@FXML
	private TableColumn<Ticket, Long> assignedTo;
	String type; 
	public ViewTicketController(TicketModel model, Employee employee, String type) {
		this.model = model;
		this.type =type;
		this.employee = employee;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillInTableView();

		addSearchFunctionalityToSearchBar();
	}

	public void goToHomePage(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+type+"/HomeView.fxml"));
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

	public void goToUpdatePage(ActionEvent event) {
		try {
			Ticket selectedRow = table.getItems().get(table.getSelectionModel().getSelectedIndex());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+type+"/UpdateView.fxml"));
			loader.setControllerFactory(c -> {
				return new UpdatePageController(model, selectedRow,type, employee);
			});
			root = loader.load();
			scene = new Scene(root);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Please select a ticket to update");
			alert.setContentText(null);
			alert.showAndWait();
			e.printStackTrace();
		}
	}

	public void goToAssignPage(ActionEvent event) {
		try {
			Ticket selectedRow = table.getItems().get(table.getSelectionModel().getSelectedIndex());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+type+"/AssignTicket.fxml"));
			loader.setControllerFactory(c -> {
				return new AssignTicketController(model, selectedRow,type,employee);
			});
			root = loader.load();
			scene = new Scene(root);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Please select a ticket to update");
			alert.setContentText(null);
			alert.showAndWait();
		}
	}

	public void goToCreatePage(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+type+"/CreateTicketView.fxml"));
		loader.setControllerFactory(c -> {
			return new CreateTicketController(model,employee, type);
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

	private void fillInTableView() {
		idCol.setCellValueFactory(new PropertyValueFactory<Ticket, Long>("ticketID"));
		titleCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("nameOfTicket"));
		userCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("userID"));
		contactCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("contact"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("description"));
		statusCol.setCellValueFactory(new PropertyValueFactory<Ticket, Boolean>("status"));
		assigned.setCellValueFactory(new PropertyValueFactory<Ticket, Boolean>("assigned"));
		assignedTo.setCellValueFactory(new PropertyValueFactory<Ticket, Long>("assignedTo"));
		items = FXCollections.observableArrayList(model.findTicketsByEmployeeID(employee));
		table.setItems(items);
	}

	private void addSearchFunctionalityToSearchBar() {
		FilteredList<Ticket> filteredData = new FilteredList<>(items, b -> true);
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super Ticket>) ticket -> {
				if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				if (Long.toString(ticket.getUserID()).contains(newValue)) {
					return true;
				}
				if (Long.toString(ticket.getTicketID()).contains(newValue)) {
					return true;
				}
				return false;
			});
		});
		SortedList<Ticket> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);
	}

}
