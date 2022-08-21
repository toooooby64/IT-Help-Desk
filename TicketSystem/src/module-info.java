module TicketSystem {
	
	exports controller;
	exports application;
	exports dao;
	exports model;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires transitive javafx.base;
	
	opens application to javafx.graphics, javafx.fxml,javafx.media;
	opens controller to javafx.fxml;
}
