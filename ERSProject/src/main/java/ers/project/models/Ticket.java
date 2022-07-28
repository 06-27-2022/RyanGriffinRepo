package ers.project.models;

import java.util.Objects;

public class Ticket {
	
	private int ticketNumber;
	private String employeeId;
	private int amount;
	private String description;
	private String actionType;
	private int reviewedBy;
	
	public Ticket() {
		
	}

	public Ticket(int ticketNumber, String employeeId, int amount, String description, String actionType,
			int reviewedBy) {
		super();
		this.ticketNumber = ticketNumber;
		this.employeeId = employeeId;
		this.amount = amount;
		this.description = description;
		this.actionType = actionType;
		this.reviewedBy = reviewedBy;
	}

	/**
	 * @return the ticketNumber
	 */
	public int getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * @param ticketNumber the ticketNumber to set
	 */
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the reviewedBy
	 */
	public int getReviewedBy() {
		return reviewedBy;
	}

	/**
	 * @param reviewedBy the reviewedBy to set
	 */
	public void setReviewedBy(int reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actionType, amount, description, employeeId, reviewedBy, ticketNumber);
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
		return Objects.equals(actionType, other.actionType) && amount == other.amount
				&& Objects.equals(description, other.description) && Objects.equals(employeeId, other.employeeId)
				&& Objects.equals(reviewedBy, other.reviewedBy) && ticketNumber == other.ticketNumber;
	}

	@Override
	public String toString() {
		return "Ticket [ticketNumber=" + ticketNumber + ", employeeId=" + employeeId + ", amount=" + amount
				+ ", description=" + description + ", actionType=" + actionType + ", reviewedBy=" + reviewedBy + "]";
	}

}
