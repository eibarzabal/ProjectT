package se.myapp.greeting.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;
import se.myapp.greeting.service.GreetingService;

import javax.validation.ValidationException;

@RestController
public class GreetingController {

    private Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private GreetingService greetingService;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = { "application/json" })
    public ResponseEntity<String> greeting(@RequestParam("account") String account, @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "type", required = false) String type) {

        try {

            String response = greetingService.greetCustomer(account, id, type);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ValidationException ve) {

            logger.error("Validation error", ve);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ve.getMessage(), ve);

        } catch (Exception e) {

            logger.error("Internal error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

        }

    }

}
