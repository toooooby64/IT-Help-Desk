package dao;

import model.Employee;

public interface EmployeeDAO extends DAO {
	public Employee getEmployeeByID(long id);
	
	

}
