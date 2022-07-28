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
		//Declaring the list and initalizing the List we will return at the end of this method.
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
						set.getString(4),
						set.getString(5),
						set.getString(6),
						set.getInt(7));
				
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

	/*
	 * Add EmployeeAccount to the DB.
	 */
	@Override
	public void addAccount(EmployeeAccount employee) {
		
		Connection conn = null;

		PreparedStatement stmt = null;
		
		// Alter SQL String to a parameterized SQL String (question marks being parameters).
		final String SQL = "insert into employees values(default, ?, ?, ?, ?, 'employee', ?)";
		
		try {
			conn = ConnectionUtil.getNewConnection();
			stmt = conn.prepareStatement(SQL);
			
			// Specify values of ? parameters
			stmt.setString(1, employee.getUsername());
			stmt.setString(2, employee.getPassword());
			stmt.setString(3, employee.getFirstName());
			stmt.setString(4, employee.getLastName());
			stmt.setInt(5, employee.getAccountBalance());
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
	
	/**
	 * Update Employee account balance
	 */
	@Override
	public void updateBalance(EmployeeAccount employee) {
		
		// Open a Connection to your DB.
		Connection conn = null;
		
		// Need a Statement object to run queries against the DB.
		PreparedStatement stmt = null;
		final String SQL = "update employees set accountBalance = ? where id = ?";
		
		try {
			//Using our utility class and method to grab a new connection to the DB
			conn = ConnectionUtil.getNewConnection();
			//Using the connection to get a Statement object.
			stmt = conn.prepareStatement(SQL);
			//Setting the parameters in our parameterized query
			stmt.setInt(1, employee.getAccountBalance());
			stmt.setInt(2, employee.getId());
			//Executing the query
			stmt.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
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
		final String SQL = "select * from associates where username = ?";
		
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
						set.getString(4),
						set.getString(5),
						set.getString(6),
						set.getInt(7)
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

}
