package jp.co.jcps.A02;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.jcps.Common.CommonCheck;
import jp.co.jcps.Common.DBConnection;

/**
 * 参加・不参加を登録する
 */
@WebServlet("/TopSave")
public class TopSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public TopSaveServlet() {
		super();
	}

	/**
	 * POSTでリクエストされた場合の処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 共通チェック
		if (!CommonCheck.existSession(request)) {
			// セッションが切れてる場合はログイン画面に遷移
			request.getRequestDispatcher("/Login").forward(request, response);
		}

		// セッションからログイン中のユーザーIDを取得する
		String userId = (String) request.getSession().getAttribute("userId");

		// リクエストから更新対象の活動IDを取得する
		String updateActivityId = request.getParameter("activityId");

		// SQLに埋め込むパラメータリストを定義
		List<String> paramList = new ArrayList<String>();
		paramList.add(updateActivityId);
		paramList.add(userId);

		// SQLを設定
		String sql = "SELECT * FROM trn_participant WHERE activity_id = ? AND user_id = ?; ";

		// SQLを実行し結果を取得
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		try {
			rs = db.executeSelectQuery(sql, paramList);
		} catch (Exception e1) {
			request.getRequestDispatcher("ERROR/Error.jsp").forward(request, response);
		}

		try {
			// 参加者データの有無をチェック
			if (rs.next()) {
				// データがない場合はinsert(参加にする）
				deleteTrnParticipant(paramList);
			} else {
				// データがある場合はdelete(不参加にする）
				insertTrnParticipant(paramList);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getRequestDispatcher("ERROR/Error.jsp").forward(request, response);
		} finally {
			try {
				db.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		// TOP画面の呼び出し
		request.getRequestDispatcher("/TopController").forward(request, response);
	}

	private void insertTrnParticipant(List<String> paramList) throws Exception {
		// インサートするSQL
		String sql = "INSERT INTO trn_participant VALUES (?,?);";

		// SQLを実行し結果を取得
		DBConnection db = new DBConnection();
		db.executeInsertUpdateQuery(sql, paramList);
	}

	private void deleteTrnParticipant(List<String> paramList) throws Exception {
		// デリートするSQL
		String sql = "DELETE FROM trn_participant WHERE activity_id = ? AND user_id = ?;";

		// SQLを実行し結果を取得
		DBConnection db = new DBConnection();
		db.executeInsertUpdateQuery(sql, paramList);
	}
}
