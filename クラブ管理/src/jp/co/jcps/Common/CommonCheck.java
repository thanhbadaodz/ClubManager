package jp.co.jcps.Common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 共通チェック処理を実装するクラス
 */

public class CommonCheck {

	/**
	 * 	学生セッションチェック
	 * @return sessionが有効 : ture sessionが無効 : false
	 */
	public static boolean existSession(HttpServletRequest request) {
		// セッション情報を取得
		HttpSession session = request.getSession(false);

		// セッションが存在しない場合、falseを返却
		if(session == null) {
			return false;
		}

		// 学生IDがセッションに含まれていない場合、falseを返却
		if(session.getAttribute("userId") == null) {
			return false;
		}
		return true;
	}

}
