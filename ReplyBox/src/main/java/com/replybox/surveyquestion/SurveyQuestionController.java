package com.replybox.surveyquestion;

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
import com.replybox.surveyquestion.SurveyQuestion;


@Controller
@RequestMapping("replyboxsurveyquestion") // I am not sure why this is needed.
public class SurveyQuestionController {
	@Autowired
	private ISurveyQuestionService surveyQuestionService;
	@GetMapping("surveyquestion/{id}") 
	public ResponseEntity<SurveyQuestion> getUseerById(@PathVariable("id") Integer id) {
		SurveyQuestion surveyQuestion = surveyQuestionService.getSurveyQuestionById(id);
		return new ResponseEntity<SurveyQuestion>(surveyQuestion, HttpStatus.OK);
	}
	@GetMapping("surveyquestions")
	public ResponseEntity<List<SurveyQuestion>> getAllSurveyQuestions() {
		List<SurveyQuestion> list = surveyQuestionService.getAllSurveyQuestions();
		return new ResponseEntity<List<SurveyQuestion>>(list, HttpStatus.OK);
	}
	@PostMapping("surveyquestion")
	public ResponseEntity<Void> addSurveyQuestion(@RequestBody SurveyQuestion surveyQuestion, UriComponentsBuilder builder) {
                boolean flag = surveyQuestionService.addSurveyQuestion(surveyQuestion);
                if (flag == false) {
        	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/surveyquestion/{id}").buildAndExpand(surveyQuestion.getSurveyQuestionId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("surveyquestion")
	public ResponseEntity<SurveyQuestion> updateSurveyQuestion(@RequestBody SurveyQuestion surveyQuestion) {
		surveyQuestionService.updateSurveyQuestion(surveyQuestion);
		return new ResponseEntity<SurveyQuestion>(surveyQuestion, HttpStatus.OK);
	}
	@DeleteMapping("surveyquestion/{id}")
	public ResponseEntity<Void> deleteSurveyQuestion(@PathVariable("id") Integer id) {
		surveyQuestionService.deleteSurveyQuestion(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}  