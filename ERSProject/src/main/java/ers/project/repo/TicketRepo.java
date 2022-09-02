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
	void createNewTicket(Ticket ticket);
	
	/**
	 * List all Pending tickets
	 */
	List<Ticket> findAllPending();
	
	/**
	 * Locate by ticket number
	 */
	Ticket findByTicketNumber(int ticketNumber);
	
	/**
	 * Update Ticket actionType
	 */
	void updateActionType(int ticketNumber, String actionType);

}