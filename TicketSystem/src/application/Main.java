package application;

import controller.HomePageController;
import controller.LoginPageController;
import dao.PostgrseDAO;
import dao.PostgrseEmployeeDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.EmployeeModel;
import model.TicketModel;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	private static TicketModel model;
	private static EmployeeModel employeeModel1;
	private static PostgrseEmployeeDAO employeeModel = new PostgrseEmployeeDAO();
	private static PostgrseDAO dbConnection = new PostgrseDAO();


	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPage.fxml"));
			loader.setControllerFactory(c -> {
				return new LoginPageController(employeeModel1,model);
			});
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
	        primaryStage.setOnCloseRequest(e->{
	            try {
	                dbConnection.close();
	                employeeModel.close();
	                System.out.println("connection closed");
	            } catch (Exception exc) { 
	                System.err.println("couldn't close connection");
	            }
	        });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		model = new TicketModel(dbConnection);
		employeeModel1 = new EmployeeModel(employeeModel);
		try {
			dbConnection.connect();
			employeeModel.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}
}
