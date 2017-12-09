package com.replybox.survey;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import com.replybox.survey.Survey;
@Controller
@RequestMapping("replyboxsurvey") // I am not sure why this is needed.
public class SurveyController {
	@Autowired
	private ISurveyService surveyService;
	@GetMapping("survey/{id}") 
	public ResponseEntity<Survey> getSurveyById(@PathVariable("id") Integer id) {
		Survey survey = surveyService.getSurveyById(id);
		return new ResponseEntity<Survey>(survey, HttpStatus.OK);
	}
	@GetMapping("surveys")
	public ResponseEntity<List<Survey>> getAllSurveys() {
		List<Survey> list = surveyService.getAllSurveys();
		return new ResponseEntity<List<Survey>>(list, HttpStatus.OK);
	}
	@GetMapping("processsurveys")
	public ResponseEntity<Integer> processSurveys() {
		Integer numSurveysProcessed = surveyService.processSurveys();
		//{SWS} I should probably format the response at JSON
		return new ResponseEntity<Integer>(numSurveysProcessed, HttpStatus.OK);
	}
	@PostMapping("survey")
	public ResponseEntity<Void> addSurvey(@RequestBody Survey survey, UriComponentsBuilder builder) {
        boolean flag = surveyService.addSurvey(survey);
        if (flag == false) {
	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/survey/{id}").buildAndExpand(survey.getSurveyId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("survey")
	public ResponseEntity<Survey> updateSurvey(@RequestBody Survey survey) {
		surveyService.updateSurvey(survey);
		return new ResponseEntity<Survey>(survey, HttpStatus.OK);
	}
	@DeleteMapping("survey/{id}")
	public ResponseEntity<Void> deleteSurvey(@PathVariable("id") Integer id) {
		surveyService.deleteSurvey(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}  