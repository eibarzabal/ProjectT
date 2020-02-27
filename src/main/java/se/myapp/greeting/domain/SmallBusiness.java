package se.myapp.greeting.domain;

import java.util.Optional;

public class SmallBusiness implements CustomerBehaviour {

	protected SmallBusiness() {
	}

	@Override
	public String greet(Optional<String> message) {
		throw new UnsupportedOperationException("Greet is not supported for small business customers.");
	}

}
