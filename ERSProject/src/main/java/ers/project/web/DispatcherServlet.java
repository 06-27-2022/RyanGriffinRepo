package ers.project.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
	
public class DispatcherServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String httpVerb = request.getMethod();
		String resource = request.getRequestURI();
		
		EmployeeRepo currentUser = new EmployeeRepoImpl();
		String isolatedResource = resource.replace("/ERSProject/api", "");
		PrintWriter writer = response.getWriter();
		EmployeeAccount loggedIn = null;
		
		Cookie[] cookieJar = request.getCookies();
		boolean theManager = false;
		boolean theEmployee = false;
		if(cookieJar != null) {
			for(Cookie cookie : cookieJar) {
				if(cookie.getName().equals("management")) {
					theManager = true;
				}
				if(cookie.getName().equals("authenticated")) {
					theEmployee = true;
				}
			}
		}
		
		switch(isolatedResource) {
		// Employee management Uniform Resource Identifier
		case "/userview":
				response.setContentType("text/html");
				String usernameInput = request.getParameter("username");
				String passwordInput = request.getParameter("passwrd");
				EmployeeAccount usernameSearch = currentUser.findByUsername(usernameInput);
				// Create Employee Account
				if(httpVerb.equals("POST")) {
					EmployeeAccount newAccount = new EmployeeAccount();
					// For valid creation
					if(usernameSearch == null) {
						newAccount = new EmployeeAccount(usernameInput, passwordInput);
						writer.write("New Employee Account has been created.");
						currentUser.saveAccount(newAccount);
						loggedIn = currentUser.findByUsername(usernameInput);
						Cookie normCookie = new Cookie("authenticated", "true");
						response.addCookie(normCookie);
						response.setStatus(201);
					// For invalid creation
					}else {
						writer.write("The username you have selected already exists. Please try a different username.");
						response.setStatus(202);
					}
					// Employee login
				}else if(httpVerb.equals("GET")) {
					// For errors
					if(usernameSearch == null) {
						writer.write("Please enter a valid username.");
						response.setStatus(401);
					}else if(currentUser.checkPassword(usernameInput, passwordInput) == false) {
						writer.write("The username or password you've entered is incorrect. Please try again.");
						response.setStatus(401);
						// For valid Employee Login
					}else {
						loggedIn = currentUser.findByUsername(usernameInput);
						writer.write("Login Successful");
						Cookie normCookie = new Cookie("authenticated", "true");
						response.addCookie(normCookie);
						if(loggedIn.isManager() == true) {
							Cookie managerCookie = new Cookie("management", "true");
							response.addCookie(managerCookie);
						}
						response.setStatus(202);
					}
				}
			
				break;
				
			// Ticket Manager view and editor Uniform Resource Identifier
			case "/ticketmanager/view":
					TicketRepo employeeTicket = new TicketRepoImpl();
					if(httpVerb.equals("GET") && theManager) {
						List<Ticket> ticketRepo = employeeTicket.findAllPending();
						
						ObjectMapper theMap = new ObjectMapper();
						String json = theMap.writeValueAsString(ticketRepo);
						response.setContentType("application/json");
						response.setStatus(200);
						writer.write(json);
					// Managers only editor
					}else if(httpVerb.equals("POST") && theManager) {
						Ticket ticketSearch = null;
						int ticketNumberInput = Integer.parseInt(request.getParameter("ticketNumber"));
						String actionTypeInput = request.getParameter("actionType");
						ticketSearch = employeeTicket.findByTicketNumber(ticketNumberInput);
						if(ticketSearch == null) {
							response.setContentType("text/html");
							writer.write("The ticket does not exist.");
							response.setStatus(401);
						}else if(ticketSearch.getActionType().equals("PENDING") == false) {
							response.setContentType("text/html");
							writer.write("The selected ticket has already been acted upon.");
							response.setStatus(401);
						}else if(actionTypeInput.equals("APPROVED") || actionTypeInput.equals("DENIED")) {
							response.setContentType("text/html");
							employeeTicket.updateActionType(ticketNumberInput, actionTypeInput);
							writer.write("Ticket number " + ticketNumberInput + " has been " + actionTypeInput + ".");
							response.setStatus(201);
						}else {
							response.setContentType("text/html");
							writer.write("The action type you've entered is invalid. Enter 'APPROVED' or 'DENIED'.");
							response.setStatus(401);
						}
						
					}else {
						response.setContentType("text/html");
						writer.write("You are not authorized to enter this area.");
						response.setStatus(401);
					}
					break;
				// Create a ticket
				case "/ticketmanager/create":
						TicketRepo newTix = new TicketRepoImpl();
						if(httpVerb.equals("POST") && theEmployee) {
							double amountInput = Double.parseDouble(request.getParameter("amount"));
							String descriptionInput = request.getParameter("description");
							Ticket newTicket = new Ticket(amountInput, descriptionInput);
							newTix.createNewTicket(newTicket);
							response.setContentType("text/html");
							writer.write("New ticket of " + amountInput + " created for " + descriptionInput + ".");
							response.setStatus(201);
						}else {
							response.setContentType("text/html");
							writer.write("You must be logged in to create a ticket.");
							response.setStatus(401);
						}
				default:
						response.setStatus(404);
						break;
							
				}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
