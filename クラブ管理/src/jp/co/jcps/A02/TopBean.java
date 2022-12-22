package jp.co.jcps.A02;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.co.jcps.Bean.ActivityBean;

/**
 * Top画面のBean
 *
 */
public class TopBean implements Serializable {

	// 部活名のリスト
	private List<String> clubNameList;
	// 部活毎の活動リスト
	private List<List<ActivityBean>> clubActivityList;

	/**
	 * コンストラクタ
	 *
	 */
	public TopBean() {
		this.clubNameList = new ArrayList<>();
		this.clubActivityList = new ArrayList<>();
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
	 * @param clubNameList に要素を追加する clubName
	 */
	public void addClubNameList(String clubName) {
		this.clubNameList.add(clubName);
	}

	/**
	 * @return clubActivityMap
	 */
	public List<List<ActivityBean>> getClubActivityList() {
		return clubActivityList;
	}

	/**
	 * @param clubActivityMap セットする clubActivityMap
	 */
	public void setClubActivityList(List<List<ActivityBean>> clubActivityList) {
		this.clubActivityList = clubActivityList;
	}

}
