package ers.project.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import ers.project.models.EmployeeAccount;
import ers.project.models.Ticket;
import ers.project.repo.EmployeeRepo;
import ers.project.repo.EmployeeRepoImpl;
import ers.project.repo.TicketRepo;
import ers.project.repo.TicketRepoImpl;

public class DispatcherServlet extends HttpServlet{
	
	
	/*
	 * Servlets for Employee Account:
	 * 
	 * 1) List all employee accounts
	 * 
	 * 2) Create / register new employee account
	 * 
	 * 3) Login to employee account
	 * 
	 * 4) Logout of employee account
	 */
	
	/*
	 * Servlets for Tickets:
	 * 
	 * 1) List all tickets
	 * 
	 * 2) Create new ticket
	 * 
	 * 3) Manager access / approval of tickets
	 */
	

}
