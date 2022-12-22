package jp.co.jcps.A07;

import java.io.Serializable;

/**
 * 部活情報登録画面のBean
 */
public class ClubInfoRegisterBean implements Serializable {

	// 部活名
	private String clubName;
	// 部活説明
	private String clubDescription;

	/**
	 * コンストラクタ
	 *
	 */
	public ClubInfoRegisterBean() {
		this.clubName = "";
		this.clubDescription = "";
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
	 * @return clubDescription
	 */
	public String getClubDescription() {
		return clubDescription;
	}

	/**
	 * @param clubDescription セットする clubDescription
	 */
	public void setClubDescription(String clubDescription) {
		this.clubDescription = clubDescription;
	}

}
