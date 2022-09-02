package ers.project.repo;

import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import ers.project.models.EmployeeAccount;
import ers.project.util.ConnectionUtil;

public class EmployeeRepoImpl implements EmployeeRepo{
	
	/**
	 * List all employee accounts already in DB.
	 */
	public List<EmployeeAccount> findAllEmployees() {
		
		/*
		 * Connection declaration up here so it will be in scope
		 * for "try" and "finally" blocks.
		 */
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		//Declaring the list and initializing the List we will return at the end of this method.
		List<EmployeeAccount> employees = new ArrayList<>();
		
		//Declare my SQL query string ahead of time:
		final String SQL = "select * from employees";
		
		try {
			// Get a connection.
			conn = ConnectionUtil.getNewConnection();
			
			// Create statement for Statement Interface
			stmt = conn.createStatement();
			
			// Store results of running query
			set = stmt.executeQuery(SQL);
			
			while(set.next()) {
				/* 
				 * While there is another record in the result set, let's print the
				 * the contents of the table rows here.
				 */
				
				EmployeeAccount employee = new EmployeeAccount(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getBoolean(4));
				/*
				 * Add EmployeeAccount objects to List<EmployeeAccount>
				 * on each iteration.
				 */
	
				employees.add(employee);
				 
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			// Always close connections
			try {
			conn.close();
			stmt.close();
			set.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return employees;
	}
	@Override
	public void saveAccount(EmployeeAccount employee) {
		
		Connection conn = null;

		PreparedStatement stmt = null;
		
		// Alter SQL String to a parameterized SQL String (question marks being parameters).
		final String SQL = "insert into employees values(default, ?, ?, false)";
		
		try {
			conn = ConnectionUtil.getNewConnection();
			stmt = conn.prepareStatement(SQL);
			
			// Specify values of ? parameters
			stmt.setString(1, employee.getUsername());
			stmt.setString(2, employee.getPasswrd());
			stmt.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				
			// Close connections
			conn.close();
			stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public EmployeeAccount findByUsername(String username) {
		
		EmployeeAccount employee = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		final String SQL = "select * from employees where username = ?";
		
		try {
			conn = ConnectionUtil.getNewConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			set = stmt.executeQuery();
			
			//Now extract the data from the record in the ResultSet
			if(set.next()) {
				employee = new EmployeeAccount(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getBoolean(5)
						);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				set.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return employee;
	}
	
	@Override
	public boolean checkPassword(String username, String passwrd) {
		EmployeeRepo pswrdchk = new EmployeeRepoImpl();
		return pswrdchk.findByUsername(username).getPasswrd().equals(passwrd);
		
	}
	
}
