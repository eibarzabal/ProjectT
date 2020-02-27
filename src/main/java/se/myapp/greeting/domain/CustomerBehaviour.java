package se.myapp.greeting.domain;

import java.util.Optional;

public interface CustomerBehaviour {
	
	String greet(Optional<String> message);
	
}
