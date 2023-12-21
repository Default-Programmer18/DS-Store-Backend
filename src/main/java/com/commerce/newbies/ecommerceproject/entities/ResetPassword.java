package com.commerce.newbies.ecommerceproject.entities;

public class ResetPassword {
	
	private String CurrentPassword;
	private String NewPassword;
	
	public String getCurrentPassword() {
		return CurrentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		CurrentPassword = currentPassword;
	}
	public String getNewPassword() {
		return NewPassword;
	}
	public ResetPassword() {
		super();}
	public ResetPassword(String currentPassword, String newPassword) {
		super();
		CurrentPassword = currentPassword;
		NewPassword = newPassword;
	}
	public void setNewPassword(String newPassword) {
		NewPassword = newPassword;
	}
	
}
