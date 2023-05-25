package WebApiAutomation.WebApiAutomation;

import entities.NotFound;
import entities.RateLimit;
import entities.User;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class BodyTestWithJackson extends BaseClass {

	
    @Test
    public void returnsCorrectLogin() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/estarencodes");

        response = client.execute(get);

        User user = ResponseUtils.unmarshallGeneric(response, User.class);

        assertEquals(user.getLogin(), "estarencodes");
    }

    @Test
    public void returnsCorrectId() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/estarencodes");

        response = client.execute(get);

        User user = ResponseUtils.unmarshallGeneric(response, User.class);

        assertEquals(user.getId(), 40895061);
    }

    @Test
    public void notFoundMessageIsCorrect() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingendpoint");

        response = client.execute(get);

        NotFound notFoundMessage = ResponseUtils.unmarshallGeneric(response, NotFound.class);

        assertEquals(notFoundMessage.getMessage(), "Not Found");
    }

    @Test
    public void correctRateLimitsAreSet() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");

        response = client.execute(get);

        RateLimit rateLimits = ResponseUtils.unmarshallGeneric(response, RateLimit.class);

        assertEquals(rateLimits.getCoreLimit(), 60);
        assertEquals(rateLimits.getSearchLimit(), "10");

    }


}
