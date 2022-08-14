package model;

import dao.EmployeeDAO;

public class EmployeeModel {

	private EmployeeDAO employeeDAO;
	
	public EmployeeModel(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	public Employee getEmployeeByID(long id) {
		return employeeDAO.getEmployeeByID(id);
	}
	
}
