package ers.project.models;

import java.util.Objects;

public class EmployeeAccount {
	
	
	// Create instances of Employee info
	private int id;
	private String username;
	private String passwrd;
	private boolean manager;
	
	public EmployeeAccount() {
		
	}

	public EmployeeAccount(int id, String username, String passwrd, boolean manager) {
		this.id = id;
		this.username = username;
		this.passwrd = passwrd;
		this.manager = manager;
	}
	
	public EmployeeAccount(String username, String passwrd) {
		this.username = username;
		this.passwrd = passwrd;
		this.manager = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswrd() {
		return passwrd;
	}

	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, manager, passwrd, username);
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
		EmployeeAccount other = (EmployeeAccount) obj;
		return id == other.id && manager == other.manager && Objects.equals(passwrd, other.passwrd)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "EmployeeAccount [id=" + id + ", username=" + username + ", passwrd=" + passwrd + ", manager="
				+ manager + "]";
	}
}
