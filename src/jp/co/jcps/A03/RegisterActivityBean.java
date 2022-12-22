package jp.co.jcps.A03;

import java.io.Serializable;

/**
 * 活動登録画面のBean
 *
 */
public class RegisterActivityBean implements Serializable {

	// 部活名
	private String clubName;

	// 活動名
	private String activityName;

	// 活動場所
	private String activityPlace;

	// 活動日
	private String activityDate;

	// 活動時間(自)
	private String activityStartTime;

	// 活動時間(至)
	private String activityEndTime;

	// 活動説明
	private String activityDescription;

	// 参加上限人数
	private String maxParticipant;

	/**
	 * コンストラクタ
	 *
	 */
	public RegisterActivityBean() {
		this.clubName = "";
		this.activityName = "";
		this.activityPlace = "";
		this.activityStartTime = "";
		this.activityEndTime = "";
		this.activityDescription = "";
		this.maxParticipant = "";
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
	 * @return activityDate
	 */
	public String getActivityDate() {
		return activityDate;
	}

	/**
	 * @param activityDate セットする activityDate
	 */
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	/**
	 * @return activityStartTime
	 */
	public String getActivityStartTime() {
		return activityStartTime;
	}

	/**
	 * @param activityStartTime セットする activityStartTime
	 */
	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	/**
	 * @return activityEndTime
	 */
	public String getActivityEndTime() {
		return activityEndTime;
	}

	/**
	 * @param activityEndTime セットする activityEndTime
	 */
	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
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

}
