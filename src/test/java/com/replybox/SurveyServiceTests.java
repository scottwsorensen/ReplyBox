package com.replybox;

import java.util.List;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import com.replybox.survey.*;

//{SWS} See QuestionServiceTests
public class SurveyServiceTests {
    @Autowired
    private ISurveyService surveyService;// = new QuestionService();
    
//	@Test
	public void test() {
//		List<Survey> surveys = surveyService.getAllSurveys();
//		assertThat(surveys.get(0).getSurveyName().contains("Adobe"));	
//		boolean addQuestion(Question question){
//		void updateQuestion(Question question)  with new and with same (should fail)
//		questionDAO.updateQuestion(question);
//		void deleteQuestion(Integer questionId) {
	}
	
    // This test will ensure that we can 
    // - get surveys to process
    // - get audience to send surveys to
    // - get question to send
    // - but will not actually send the questions.
//	@Test
	public void testProcessSurveysComponentsWithoutSend() {
		// test User
		/*
		List<User> list = userService.getAllUsers();
		for (Integer i=0; i<list.size(); i++) {
			User user = list.get(i);
			user.getFirstName();
		}
		*/
		assert(true);
	}
		
	
    //{SWS} How do I create a processSurveys test that only processes test surveys?
	//{SWS} TODO: make a test for
	// processsurveys should just send to a seed list?
	// getSurveysToProcess test needs to write a DummySurvey to the database that has no questions before executing
	// this means that getSurveysToProcess, getQuestionsForSurvey, getUsersForSurvey should be separate from sendSurveyQuestions(questions, users) so that they 
	// are independently testable.
	// how do I make sure there is always a survey to get that doesn't get executed - can I create 
	// a dummysurvey that has no questions or no audience? Maybe all Dummy date starts with Dummy
	// so that our reports can scrub the data.
	// how do I processSurvey and make sure it only goes to a seedlist
}
