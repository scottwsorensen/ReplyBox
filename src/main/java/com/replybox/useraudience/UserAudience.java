package com.replybox.useraudience;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user_audiences")
public class UserAudience implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_audience_id")
    private Integer userAudienceId;  
	@Column(name="user_id")
    private Integer userId;
	@Column(name="audience_id")
    private Integer audienceId;
	public Integer getUserAudienceId() {
		return userAudienceId;
	}
	public void setUserAudienceId(Integer userAudienceId) {
		this.userAudienceId = userAudienceId;
	}
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAudienceId() {
		return this.audienceId;
	}
	public void setAudienceId(Integer audienceId) {
		this.audienceId = audienceId;
	}
}  
