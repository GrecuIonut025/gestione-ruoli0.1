package it.economicaircompany.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String helloAdmin() {
		return "Welcome Admin";
	}

	@GetMapping("/user")
	
	public String helloUser() {
		return "Welcome User";
	}

	@GetMapping("/public")
	public String helloPublic() {
		return "Welcome Everybody";
	}

}
