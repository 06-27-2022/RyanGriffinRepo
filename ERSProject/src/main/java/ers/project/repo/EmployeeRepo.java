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
	void addAccount(EmployeeAccount employee);
	
	/**
	 * 
	 * This method updates employee's account balance.
	 * 
	 * @param associate
	 * 
	 */
	void updateBalance(EmployeeAccount employee);
	
	/**
	 * This method locates an employee account by their username
	 * 
	 * @param 
	 */
	EmployeeAccount findByUsername(String username);

}
