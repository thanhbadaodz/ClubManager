package jp.co.jcps.A06;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 部員登録承認画面のBean
 */
public class JoinApprovalBean implements Serializable {

	// 部活名
	private String clubName;

	// ユーザーIDリスト
	private List<String> userIdList;

	// ユーザー名リスト
	private List<String> userNameList;

	/**
	 * コンストラクタ
	 *
	 */
	public JoinApprovalBean() {
		this.clubName = "";
		this.userIdList = new ArrayList<>();
		this.userNameList = new ArrayList<>();

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
	 * @return userIdList
	 */
	public List<String> getUserIdList() {
		return userIdList;
	}

	/**
	 * @param userIdList セットする userIdList
	 */
	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	/**
	 * @param String リストに追加するuserId
	 */
	public void addUserIdList(String userId) {
		this.userIdList.add(userId);
	}

	/**
	 * @return userNameList
	 */
	public List<String> getUserNameList() {
		return userNameList;
	}

	/**
	 * @param userNameList セットする userNameList
	 */
	public void setUserNameList(List<String> userNameList) {
		this.userNameList = userNameList;
	}

	/**
	 * @param String リストに追加するuserName
	 */
	public void addUserNameList(String userName) {
		this.userNameList.add(userName);
	}

}
