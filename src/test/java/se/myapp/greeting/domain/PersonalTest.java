package se.myapp.greeting.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;

import javax.validation.ValidationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PersonalTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenInvalidId_whenValidate_thenThrowValidationException() {

        expectedException.expect(ValidationException.class);

        //given
        String account = "personal";
        String idRequest = "agag";
        CustomerBehaviour personal = CustomerFactory.createCustomer(account, null);

        //when
        personal.greet(Optional.ofNullable(idRequest));

    }

    @Test
    public void givenNegativeId_whenValidate_thenThrowValidationException() {

        expectedException.expect(ValidationException.class);

        //given
        String account = "personal";
        CustomerBehaviour personal = CustomerFactory.createCustomer(account, null);
        String idRequest = "-1";

        //when
        personal.greet(Optional.ofNullable(idRequest));

    }

    @Test
    public void givenNulId_whenValidate_thenThrowValidationException() {

        expectedException.expect(ValidationException.class);

        //given
        String account = "personal";
        CustomerBehaviour personal = CustomerFactory.createCustomer(account, null);
        String idRequest = null;

        //when
        personal.greet(Optional.ofNullable(idRequest));

    }

    @Test
    public void givenValidPersonalAccount_whenValidatingAccount_thenGetAccountTypePersonal() {

        //given
        String account = "personal";
        CustomerBehaviour personal = CustomerFactory.createCustomer(account, null);
        String idRequest = "123";

        //when
        String response = personal.greet(Optional.ofNullable(idRequest));

        //then
        assertThat(response, equalTo("Hi, userId 123"));

    }
}