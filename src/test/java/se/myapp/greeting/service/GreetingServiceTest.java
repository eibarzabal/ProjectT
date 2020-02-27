package se.myapp.greeting.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ValidationException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GreetingServiceTest {

    @InjectMocks
    private GreetingService greetingService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenInvalidAccountRequest_whenGreetingCustomer_thenValidationException() {

        expectedException.expect(ValidationException.class);

        //given
        String accountRequest = "personalll";
        String id = "1";

        //when
        greetingService.greetCustomer(accountRequest,id, null);

    }
    
    @Test
    public void givenInvalidId_whenGreetingCustomer_thenValidationException() {

        expectedException.expect(ValidationException.class);

        //given
        String accountRequest = "personal";
        String id = "-1";

        //when
        greetingService.greetCustomer(accountRequest,id, null);

    }

    @Test
    public void givenValidPersonalAccountAndId_whenGreetingCustomer_thenValidGreeting() {

        //given
        String accountRequest = "personal";
        String id = "123";

        //when
        String response = greetingService.greetCustomer(accountRequest,id, null);

        //then
        assertThat(response, equalTo("Hi, userId 123"));

    }

    @Test
    public void givenSmallBusinessType_whenGreetingCustomer_thenNotImplementedException() {

        expectedException.expect(UnsupportedOperationException.class);

        //given
        String accountRequest = "business";
        String type = "small";

        //when
        greetingService.greetCustomer(accountRequest, null, type);

    }

    @Test
    public void givenValidBusinessAccountAndTypeBig_whenGreetingCustomer_thenValidGreeting() {

        //given
        String accountRequest = "business";
        String type = "big";

        //when
        String response = greetingService.greetCustomer(accountRequest, null, type);

        //then
        assertThat(response, equalTo("Welcome, business user!"));

    }

}