package ers.project.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import ers.project.models.Ticket;
import ers.project.util.ConnectionUtil;

public class TicketRepoImpl implements TicketRepo{
	
	/**
	 * List all tickets already in DB.
	 */
	@Override
	public List<Ticket> findAllTickets() {
		
		/*
		 * Connection declaration up here so it will be in scope
		 * for "try" and "finally" blocks.
		 */
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		//Declaring the list and initializing the List we will return at the end of this method.
		List<Ticket> tickets = new ArrayList<>();
		
		//Declare my SQL query string ahead of time:
		final String SQL = "select * from tickets";
		
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
				
				Ticket ticket = new Ticket(
						set.getInt(1),
						set.getDouble(2),
						set.getString(3),
						set.getString(4)
						);
				
				/*
				 * Add Ticket objects to List<Ticket>
				 * on each iteration.
				 */
	
				tickets.add(ticket);
				 
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
		
		return tickets;
	}
	
	@Override
	public void createNewTicket(Ticket ticket) {
		
		Connection conn = null;

		PreparedStatement stmt = null;
		
		// Alter SQL String to a parameterized SQL String (question marks being parameters).
		final String SQL = "insert into tickets values(default, ?, ?, 'PENDING')";
		
		try {
			conn = ConnectionUtil.getNewConnection();
			stmt = conn.prepareStatement(SQL);
			
			// Specify values of ? parameters
			stmt.setDouble(2, ticket.getAmount());
			stmt.setString(3, ticket.getDescription());
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
	public List<Ticket> findAllPending() {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		List<Ticket> allPendingTix = new ArrayList<>();
		
		final String SQL = "select * from tickets where actionType = 'PENDING'";
		
		try {
			conn = ConnectionUtil.getNewConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			while(set.next()) {
				Ticket ticket = new Ticket(
						set.getInt(1),
						set.getDouble(2),
						set.getString(3),
						set.getString(4));
				
				allPendingTix.add(ticket);
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
		return allPendingTix;
	}
	
	@Override
	public Ticket findByTicketNumber(int ticketNumber) {
		
		Ticket ticketSearch = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		final String SQL = "select * from tickets where ticketNumber = ?";
		
		try {
			conn = ConnectionUtil.getNewConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, ticketNumber);
			set = stmt.executeQuery();
			
			if(set.next()) {
				ticketSearch = new Ticket(
						set.getInt(1),
						set.getDouble(2),
						set.getString(3),
						set.getString(4));
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
		
		return ticketSearch;
	}
	
	@Override
	public void updateActionType(int ticketNumber, String actionType) {
		//The first step should always be to open a Connection to your DB.
		Connection conn = null;
		//I also know that I need a Statement object if I want to run queries against the DB.
		PreparedStatement stmt = null;
		
		final String SQL = "update ticket set actionType = ?, reviewedBy = ? where ticketNumber = ?";
		
		try {
			//Using our utility class and method to grab a new connection to the DB
			conn = ConnectionUtil.getNewConnection();
			//Using the connection to get a Statement object.
			stmt = conn.prepareStatement(SQL);
			//Setting the parameters in our parameterized query
			stmt.setString(1, actionType);
			stmt.setInt(2, ticketNumber);
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
}