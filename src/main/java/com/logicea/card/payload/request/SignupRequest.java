package com.logicea.card.payload.request;

import jakarta.validation.constraints.NotBlank;

public class SignupRequest {


	@NotBlank
	private String password;

	@NotBlank
	private String email;

	@NotBlank
	private String role;



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    public String getEmail() {
		return email;
	}

	public void setRole(String role) {
		this.role = role;
	}
    public String getRole() {
		return role;
	}
}
