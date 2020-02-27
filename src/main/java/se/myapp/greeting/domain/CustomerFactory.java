package se.myapp.greeting.domain;

import javax.validation.ValidationException;

public class CustomerFactory {

	private CustomerFactory() {
	}

	public static CustomerBehaviour createCustomer(String account, String type) {

		try {

			AccountType accountType = AccountType.getByDescription(account);

			if (accountType == AccountType.PERSONAL) {

				return new Personal();

			} else if (accountType == AccountType.BUSINESS) {

				BusinessType businessType = BusinessType.getByDescription(type);

				if (businessType == BusinessType.BIG) {

					return new BigBusiness();

				} else if (businessType == BusinessType.SMALL) {

					return new SmallBusiness();

				}

			}

			throw new ValidationException(
					"Could not create customer for account[" + account + "] and type[" + type + "]");

		} catch (RuntimeException e) {

			throw new ValidationException(e.getMessage());

		}

	}

}
