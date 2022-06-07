package it.economicaircompany.security.model;

import java.util.List;

public class LoginResponse {

	private final String type = "Bearer";
	private String token;
	private List<String> roles;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getType() {
		return type;
	}

}
