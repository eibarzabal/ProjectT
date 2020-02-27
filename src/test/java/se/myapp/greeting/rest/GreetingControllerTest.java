package se.myapp.greeting.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import se.myapp.greeting.GreetingApplication;
import se.myapp.greeting.service.GreetingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreetingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(loader = org.springframework.boot.test.context.SpringBootContextLoader.class)
public class GreetingControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private GreetingService greetingService_mock;

    @Test
    public void givenPersonalAccount_whenGreeting_thenReturnGreeting() {
        // given
        String responseMessage = "Completed successfully";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "/greeting?account=personal";
        doReturn(responseMessage).when(greetingService_mock).greetCustomer(any(),any(),any());

        // when
        ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);

        // then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo(responseMessage));
        verify(greetingService_mock, times(1)).greetCustomer(any(), any(), any());

    }

    @Test
    public void givenNullMandatoryAccount_whenGreeting_thenReturnBadRequest() {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "/greeting";

        // when
        ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);

        // then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));

    }
    
    @Test
    public void givenBusinessAccountAndSmallType_whenGreeting_thenReturnNotImplemented() {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "/greeting?account=business";
        doThrow(new UnsupportedOperationException("")).when(greetingService_mock).greetCustomer(any(),any(),any());

        // when
        ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);

        // then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_IMPLEMENTED));

    }

}