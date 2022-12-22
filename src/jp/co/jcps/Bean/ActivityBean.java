package jp.co.jcps.Bean;

import java.io.Serializable;

/**
 * 活動リストのBean
 *
 */
public class ActivityBean implements Serializable {

	// No
	private String no;

	// 部活ID
	private String clubId;

	// 部活名
	private String clubName;

	// 活動ID
	private String activityId;

	// 活動名
	private String activityName;

	// 活動場所
	private String activityPlace;

	// 活動日（表示用）
	private String dispActivityDate;

	// 活動時間(表示用)
	private String dispActivityTime;

	// 活動説明
	private String activityDescription;

	// 参加予定人数
	private Integer participantsCount;

	// 参加上限人数
	private String maxParticipant;

	// 参加予定フラグ
	private Boolean isParticipationFlg;

	// 過半数フラグ
	private Boolean isMajorityFlg;

	/**
	 * コンストラクタ
	 *
	 */
	public ActivityBean() {
		this.no = null;
		this.clubId = null;
		this.clubName = null;
		this.activityId = null;
		this.activityName = null;
		this.activityPlace = null;
		this.activityDescription = null;
		this.participantsCount = null;
		this.maxParticipant = null;
		this.isParticipationFlg = null;
		this.isMajorityFlg = null;
	}

	/**
	 * @return no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no セットする no
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return clubId
	 */
	public String getClubId() {
		return clubId;
	}

	/**
	 * @param clubId セットする clubId
	 */
	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	/**
	 * @return clubName
	 */
	public String getClubName() {
		return clubName;
	}

	/**
	 * @param clubName セットする clubName
	 */
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	/**
	 * @return activityId
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId セットする activityId
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * @return activityName
	 */
	public String getActivityName() {
		return activityName;
	}

	/**
	 * @param activityName セットする activityName
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * @return activityPlace
	 */
	public String getActivityPlace() {
		return activityPlace;
	}

	/**
	 * @param activityPlace セットする activityPlace
	 */
	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}

	/**
	 * @return dispActivityDate
	 */
	public String getDispActivityDate() {
		return dispActivityDate;
	}

	/**
	 * @param dispActivityDate セットする dispActivityDate
	 */
	public void setDispActivityDate(String dispActivityDate) {
		this.dispActivityDate = dispActivityDate;
	}

	/**
	 * @return dispActivityTime
	 */
	public String getDispActivityTime() {
		return dispActivityTime;
	}

	/**
	 * @param dispActivityTime セットする dispActivityTime
	 */
	public void setDispActivityTime(String dispActivityTime) {
		this.dispActivityTime = dispActivityTime;
	}

	/**
	 * @return activityDescription
	 */
	public String getActivityDescription() {
		return activityDescription;
	}

	/**
	 * @param activityDescription セットする activityDescription
	 */
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	/**
	 * @return participantsCount
	 */
	public Integer getParticipantsCount() {
		return participantsCount;
	}

	/**
	 * @param participantsCount セットする participantsCount
	 */
	public void setParticipantsCount(Integer participantsCount) {
		this.participantsCount = participantsCount;
	}

	/**
	 * @return maxParticipant
	 */
	public String getMaxParticipant() {
		return maxParticipant;
	}

	/**
	 * @param maxParticipant セットする maxParticipant
	 */
	public void setMaxParticipant(String maxParticipant) {
		this.maxParticipant = maxParticipant;
	}

	/**
	 * @return isParticipationFlg
	 */
	public Boolean getIsParticipationFlg() {
		return isParticipationFlg;
	}

	/**
	 * @param isParticipationFlg セットする isParticipationFlg
	 */
	public void setIsParticipationFlg(Boolean isParticipationFlg) {
		this.isParticipationFlg = isParticipationFlg;
	}

	/**
	 * @return isMajorityFlg
	 */
	public Boolean getIsMajorityFlg() {
		return isMajorityFlg;
	}

	/**
	 * @param isMajorityFlg セットする isMajorityFlg
	 */
	public void setIsMajorityFlg(Boolean isMajorityFlg) {
		this.isMajorityFlg = isMajorityFlg;
	}



}
