package com.replybox.survey;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name="surveys")
public class Survey implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="survey_id")
    private Integer surveyId;  
	@Column(name="campaign_id")
	private Integer campaignId;
	@Column(name="company_id")
    private Integer companyId;  
	@Column(name="survey_name")
    private String surveyName;
	@Column(name="start_date")
	@Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
	@Column(name="end_date")
	@Temporal(TemporalType.TIMESTAMP)
    private Date endDate;	
	@Column(name="interval_days")
    private Integer intervalDays;
	@Column(name="audience_id")
    private Integer audienceId;
	public Integer getSurveyId() {
		return this.surveyId;
	}
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	public Integer getCampaignId() {
		return this.campaignId;
	}
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}
	public Integer getCompanyId() {
		return this.companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getSurveyName() {
		return this.surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getIntervalDays() {
		return this.intervalDays;
	}
	public void setIntervalDays(Integer intervalDays) {
		this.intervalDays = intervalDays;
	}
	public Integer getAudienceId() {
		return this.audienceId;
	}
	public void setAudienceId(Integer audienceId) {
		this.audienceId = audienceId;
	}
}  
