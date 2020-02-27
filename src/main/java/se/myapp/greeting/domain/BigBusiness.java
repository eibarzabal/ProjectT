package se.myapp.greeting.domain;

import java.util.Optional;

public class BigBusiness implements CustomerBehaviour {

	private static final String GREETING = "Welcome, business user!";

	protected BigBusiness() {
	}

	@Override
	public String greet(Optional<String> message) {
		return GREETING;
	}
}
