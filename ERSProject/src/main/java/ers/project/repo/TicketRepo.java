package ers.project.repo;

import java.util.List;

import ers.project.models.Ticket;

public interface TicketRepo {
	
	/**
	 * List all tickets
	 */
	List<Ticket> findAllTickets();
	
	/**
	 * Create new ticket
	 */
	public void createNewTicket(Ticket ticket);
	
	/**
	 * Update Ticket actionType
	 */
	public void updateActionType(int ticketNumber, String actionType, int reviewedBy);

}
