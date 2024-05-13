package model;

public class adminModel {
	private int idadmin;
	private String nameadmin;
	private String emailadmin;
	private String passwordadmin;
	public adminModel(int idadmin, String nameadmin, String emailadmin, String passwordadmin) {
		super();
		this.idadmin = idadmin;
		this.nameadmin = nameadmin;
		this.emailadmin = emailadmin;
		this.passwordadmin = passwordadmin;
	}
	public adminModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdadmin() {
		return idadmin;
	}
	public void setIdadmin(int idadmin) {
		this.idadmin = idadmin;
	}
	public String getNameadmin() {
		return nameadmin;
	}
	public void setNameadmin(String nameadmin) {
		this.nameadmin = nameadmin;
	}
	public String getEmailadmin() {
		return emailadmin;
	}
	public void setEmailadmin(String emailadmin) {
		this.emailadmin = emailadmin;
	}
	public String getPasswordadmin() {
		return passwordadmin;
	}
	public void setPasswordadmin(String passwordadmin) {
		this.passwordadmin = passwordadmin;
	}
public Object[] toObject() {
	return new Object[]{"" + idadmin, nameadmin, emailadmin, passwordadmin};
}
}
