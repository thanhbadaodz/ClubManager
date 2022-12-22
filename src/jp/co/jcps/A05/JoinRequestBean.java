package jp.co.jcps.A05;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 部員登録申請画面のBean
 */
public class JoinRequestBean implements Serializable {

	// 部活IDリスト
	private List<String> clubIdList = new ArrayList<>();

	// 部活名リスト
	private List<String> clubNameList = new ArrayList<>();

	// 部活紹介リスト
	private List<String> clubDescriptionList = new ArrayList<>();

	/**
	 * コンストラクタ
	 *
	 */
	public JoinRequestBean() {
		this.clubIdList = new ArrayList<>();
		this.clubNameList = new ArrayList<>();
		this.clubDescriptionList = new ArrayList<>();
	}

	/**
	 * @return clubIdList
	 */
	public List<String> getClubIdList() {
		return clubIdList;
	}

	/**
	 * @param clubIdList セットする clubIdList
	 */
	public void setClubIdList(List<String> clubIdList) {
		this.clubIdList = clubIdList;
	}

	/**
	 * @param clubId リストに追加するclubId
	 */
	public void addClubIdList(String clubId) {
		this.clubIdList.add(clubId);
	}

	/**
	 * @return clubNameList
	 */
	public List<String> getClubNameList() {
		return clubNameList;
	}

	/**
	 * @param clubNameList セットする clubNameList
	 */
	public void setClubNameList(List<String> clubNameList) {
		this.clubNameList = clubNameList;
	}

	/**
	 * @param clubName リストに追加するclubName
	 */
	public void addClubNameList(String clubName) {
		this.clubNameList.add(clubName);
	}

	/**
	 * @return clubDescriptionList
	 */
	public List<String> getClubDescriptionList() {
		return clubDescriptionList;
	}

	/**
	 * @param clubDescriptionList セットする clubDescriptionList
	 */
	public void setClubDescriptionList(List<String> clubDescriptionList) {
		this.clubDescriptionList = clubDescriptionList;
	}

	/**
	 * @param clubDescription 追加する clubDescription
	 */
	public void addClubDescriptionList(String clubDescription) {
		this.clubDescriptionList.add(clubDescription);
	}
}
