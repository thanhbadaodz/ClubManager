package jp.co.jcps.A04;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 参加者一覧画面のBean
 */
public class ParticipantListBean implements Serializable {

	// 活動名
	private String activityName;
	// 参加者リスト
	private List<String> participantList;

	/**
	 * コンストラクタ
	 *
	 */
	public ParticipantListBean() {
		this.activityName = "";
		this.participantList = new ArrayList<>();

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
	 * @return participantList
	 */
	public List<String> getParticipantList() {
		return participantList;
	}

	/**
	 * @param participantList セットする participantList
	 */
	public void setParticipantList(List<String> participantList) {
		this.participantList = participantList;
	}

	/**
	 * @param participantList に要素を追加する participnat
	 */
	public void addParticipantList(String participant) {
		this.participantList.add(participant);
	}
}
