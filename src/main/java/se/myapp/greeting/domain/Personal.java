package se.myapp.greeting.domain;

import javax.validation.ValidationException;
import java.util.Optional;

public class Personal implements CustomerBehaviour {

	private static final String MESSAGE_FOR_NULL = "Id cannot be null.";
	private static final String MESSAGE_FOR_NEGATIVE = "Id cannot be negative.";
	private static final String GREETING_PERSONAL = "Hi, userId ";

	protected Personal() {
	}

	public String greet(Optional<String> message) {

		try {

			String id = message.orElseThrow(() -> new ValidationException(MESSAGE_FOR_NULL));
			Integer parsedId = Integer.parseInt(id);

			if (parsedId < 0)
				throw new ValidationException(MESSAGE_FOR_NEGATIVE);

			return GREETING_PERSONAL + id;

		} catch (NumberFormatException nfe) {

			throw new ValidationException(nfe.getMessage());

		}

	}
}
