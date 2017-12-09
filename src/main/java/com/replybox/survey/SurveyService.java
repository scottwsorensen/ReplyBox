package com.replybox.survey;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bandwidth.sdk.BandwidthClient;
import com.replybox.question.IQuestionService;
import com.replybox.question.Question;
import com.replybox.question.QuestionService;
import com.replybox.survey.ISurveyDAO;
import com.replybox.survey.Survey;
import com.replybox.surveyquestion.ISurveyQuestionDAO;
import com.replybox.surveyquestion.ISurveyQuestionService;
import com.replybox.surveyquestion.SurveyQuestion;
import com.replybox.surveyquestion.SurveyQuestionService;
import com.replybox.useraudience.IUserAudienceDAO;
import com.replybox.useraudience.IUserAudienceService;
import com.replybox.useraudience.UserAudience;
import com.replybox.user.UserService;
import com.replybox.user.IUserService;
import com.replybox.user.User;
import com.replybox.useraudience.UserAudienceService;
import com.replybox.userresponse.IUserResponseService;
import com.replybox.userresponse.UserResponse;
import com.replybox.userresponse.UserResponseService;

@Service
public class SurveyService implements ISurveyService {
	@Autowired
	private ISurveyDAO surveyDAO;
	@Autowired
	private ISurveyQuestionService surveyQuestionService;
	@Autowired
	private IUserAudienceService userAudienceService;
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserResponseService userResponseService;
	
	@Override
	public Survey getSurveyById(Integer surveyId) {
		Survey obj = surveyDAO.getSurveyById(surveyId);
		return obj;
	}	
	@Override
	public List<Survey> getAllSurveys(){
		return surveyDAO.getAllSurveys();
	}
	@Override
	public synchronized boolean addSurvey(Survey survey){
                if (surveyDAO.surveyExists(survey.getCompanyId(), survey.getSurveyName())) {
    	            return false;
                } else {
    	            surveyDAO.addSurvey(survey);
    	            return true;
                }
	}
	@Override
	public void updateSurvey(Survey survey) {
		surveyDAO.updateSurvey(survey);
	}
	@Override
	public void deleteSurvey(Integer surveyId) {
		surveyDAO.deleteSurvey(surveyId);
	}
	@Override
	public List<Survey> getSurveysToProcess() {
		List<Survey> surveysToProcess = surveyDAO.getSurveysToProcess();
		return surveysToProcess;
	}
	@Override
    public Integer sendSurveyQuestion(Integer surveyQuestionId, List<UserAudience> usersForSurvey) {
		Integer numSent = 0;
		Question question = questionService.getQuestionById(surveyQuestionId);
        for (int i = 0; i < usersForSurvey.size(); i++) {
			try {
				User user = userService.getUserById(usersForSurvey.get(i).getUserId());
				//{SWS} TODO credentials should go somewhere else as a setting or something. from number needs to be configuration or something
				BandwidthClient.getInstance().setCredentials("u-flugzrabh6dnvrwhv5xl3la", "t-viyjwby6m4vjs6uhqjv4cza", "uziser57ihcsx3ddgxxbuucjehp5uzozpsgtvdq");
				com.bandwidth.sdk.model.Message.create(user.getPhoneNumber(), "+18014309264", question.getQuestionText());
				// write initial info in UserResponse table because we won't have this information at the time receive the user response
				UserResponse userResponse = new UserResponse();
				userResponse.setUserId(user.getUserId());
				userResponse.setSurveyQuestionId(surveyQuestionId);
				userResponse.setAskTime(new Date());
				userResponseService.addUserResponse(userResponse);
				numSent++;
			} catch (Exception e) {
				//{SWS} TODO figure out logging
			}
		}
		return numSent;
	}
	@Override
	public void processQuestionsForSurvey(Survey survey) {
		Integer audienceId = survey.getAudienceId();
		List<UserAudience> usersForSurvey = userAudienceService.getUsersForSurvey(audienceId);
		List<SurveyQuestion> questionsForSurvey = surveyQuestionService.getQuestionsForSurvey(survey.getSurveyId());
		// find the question to send
		for (Integer j=0; j<questionsForSurvey.size(); j++) {
			SurveyQuestion surveyQuestion = questionsForSurvey.get(j);
			if(surveyQuestion.getSendSuccess() == 1) {
				// this question has already been sent so continue to the next question.
				continue;
			}
			else{
				// this is the question that we need to send
				sendSurveyQuestion(surveyQuestion.getSurveyQuestionId(), usersForSurvey);
				surveyQuestion.setSendSuccess(1);
				surveyQuestionService.updateSurveyQuestion(surveyQuestion);
				// If that is the last question then update the survey with finish date
				//{SWS} this doesn't handle the survey with no questions...
				if(j == questionsForSurvey.size()-1) {
					survey.setEndDate(new Date());
					surveyDAO.updateSurvey(survey);
				}
				break;
			}
		}

	}
	public Integer processSurveys() {
		List<Survey> surveysToProcess = getSurveysToProcess();
		for (Integer i=0; i<surveysToProcess.size(); i++) {
			Survey survey = surveysToProcess.get(i);
			processQuestionsForSurvey(survey);
		}
		return surveysToProcess.size();
	}
} 