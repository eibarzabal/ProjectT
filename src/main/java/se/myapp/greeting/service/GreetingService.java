package se.myapp.greeting.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.myapp.greeting.domain.CustomerBehaviour;
import se.myapp.greeting.domain.CustomerFactory;

public class GreetingService {

    private Logger logger = LoggerFactory.getLogger(GreetingService.class);

    public String greetCustomer(String account, String idRequest, String type) {

        logger.debug("account["+account+"], personal["+idRequest+"], type["+type+"]");

        final CustomerBehaviour customer = CustomerFactory.createCustomer(account, type);

        return customer.greet(Optional.ofNullable(idRequest));

    }

}
