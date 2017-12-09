package com.replybox;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.replybox.question.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void healthCheckShouldReturnUP() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/health",
                String.class)).contains("UP");
    }
    
    @Test
    public void getAllSurveysShouldReturnASurveyId() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/replyboxsurvey/surveys",
                String.class)).contains("surveyId");   	
    }   
}
