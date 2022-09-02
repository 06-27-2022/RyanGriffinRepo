package ers.project.models;

import java.util.Objects;

public class Ticket {
	
	private int ticketNumber;
	private double amount;
	private String description;
	private String actionType;
	
	public Ticket() {
		
	}

	public Ticket(int ticketNumber, double amount, String description, String actionType) {
		this.ticketNumber = ticketNumber;
		this.amount = amount;
		this.description = description;
		this.actionType = actionType;
	}
	
	/**
	 * For ticket creation, takes amount + description and sets actionType
	 * @param amount
	 * @param description
	 */
	public Ticket(double amount, String description) {
		this.amount = amount;
		this.description = description;
		this.actionType = "PENDING";
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actionType, amount, description, ticketNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Ticket other = (Ticket) obj;
		return Objects.equals(actionType, other.actionType)
				&& Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(description, other.description) && ticketNumber == other.ticketNumber;
	}

	@Override
	public String toString() {
		return "Ticket [ticketNumber=" + ticketNumber + ", amount=" + amount + ", description=" + description
				+ ", actionType=" + actionType + "]";
	}

}