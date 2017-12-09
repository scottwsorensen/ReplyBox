package com.replybox.surveyquestion;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="survey_questions")
public class SurveyQuestion implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="survey_questions_id")
    private Integer surveyQuestionId;  
	@Column(name="surveyid")
    private Integer surveyId;
	@Column(name="questionid")
    private Integer questionId;
	@Column(name="send_order")
    private Integer sendOrder;
	@Column(name="send_success")
	private Integer sendSuccess;
	
	public Integer getSurveyQuestionId() {
		return surveyQuestionId;
	}
	public void setSurveyQuestionId(Integer surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}
	public Integer getSurveyId() {
		return this.surveyId;
	}
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	public Integer getQuestionId() {
		return this.questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getSendOrder() {
		return this.sendOrder;
	}
	public void setSendOrder(Integer sendOrder) {
		this.sendOrder = sendOrder;
	}
	public Integer getSendSuccess() {
		return this.sendSuccess;
	}
	public void setSendSuccess(Integer sendSuccess) {
		this.sendSuccess = sendSuccess;
	}
}  
