package com.replybox.userresponse;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="user_responses")
public class UserResponse implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_response_id")
    private Integer userResponseId;  
	@Column(name="user_id")
	private Integer userId;
	@Column(name="survey_question_id")
    private Integer surveyQuestionId;  
	@Column(name="ask_time")
	@Temporal(TemporalType.TIMESTAMP)
    private Date askTime;
	@Column(name="answer_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date answerTime;	
	@Column(name="custom_answer")
    private String customAnswer;
	@Column(name="question_response_id")
    private Integer questionResponseId;
	@Column(name="test_data")
	private Integer testData;
	public Integer getUserResponseId() {
		return this.userResponseId;
	}
	public void setUserResponseId(Integer userResponseId) {
		this.userResponseId = userResponseId;
	}
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSurveyQuestionId() {
		return this.surveyQuestionId;
	}
	public void setSurveyQuestionId(Integer surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}
	public Integer getQuestionResponseId() {
		return this.questionResponseId;
	}
	public void setQuestionResponseId(Integer questionResponseId) {
		this.questionResponseId = questionResponseId;
	}
	public Date getAskTime() {
		return this.askTime;
	}
	public void setAskTime(Date askTime) {
		this.askTime = askTime;
	}
	public Date getAnswerTime() {
		return this.answerTime;
	}
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	public String getCustomAnswer() {
		return this.customAnswer;
	}
	public void setCustomAnswer(String customAnswer) {
		this.customAnswer = customAnswer;
	}
	public Integer getTestData() {
		return this.testData;
	}
	public void setTestData(Integer testData) {
		this.testData = testData;
	}

	//{SWS} TODO: create a cron job that looks at survey start dates and 
	//{SWS} intervals and determines if a survey question needs to be sent. 
	//{SWS} If so send the question and on success update the numbeofQuestionsSent 
	//{SWS} field in the survey table. when done with all questions for the survey 
	//{SWS} then update the end_date
}  
