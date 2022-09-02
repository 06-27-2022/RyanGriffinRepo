package ers.project.repo;

import java.util.List; 

import ers.project.models.EmployeeAccount;

public interface EmployeeRepo {
	
	/**
	 * List all employee accounts
	 * @return
	 */
	List<EmployeeAccount> findAllEmployees();
	
	/**
	 * This method creates new employee accounts to be added to the employees table.
	 * 
	 * @param employee
	 * 
	 */
	void saveAccount(EmployeeAccount employee);
	
	/**
	 * This method locates an employee account by their username
	 * 
	 * @param 
	 */
	EmployeeAccount findByUsername(String username);
	
	/**
	 * This method checks if the input password given with the input username
	 * matches in the DB.
	 */
	boolean checkPassword(String username, String passwrd);

}