package jp.co.jcps.A03;

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
 * 活動登録画面のコントローラー
 */
@WebServlet("/RegisterActivityController")
public class RegisterActivityControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public RegisterActivityControllerServlet() {
		super();
	}

	/**
	 * GETメソッドでリクエストされた場合の処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 共通チェック
		if (!CommonCheck.existSession(request)) {
			// セッションが切れてる場合はログイン画面に遷移
			request.getRequestDispatcher("/Login").forward(request, response);
		}

		// セッションからログイン中のユーザーの部長クラブIDを取得する
		String leaderClubId = (String) request.getSession().getAttribute("leaderClubId");

		// SQLに埋め込むパラメータリストを定義
		List<String> paramList = new ArrayList<String>();
		paramList.add(leaderClubId);

		// SQLを設定
		String sql = "SELECT club_name FROM mst_club WHERE club_id = ?;";

		// DB接続を初期化
		DBConnection db = new DBConnection();

		// 活動登録画面のBeanを初期化
		RegisterActivityBean bean = new RegisterActivityBean();

		try {
			// SQLを実行し結果を取得
			ResultSet rs = db.executeSelectQuery(sql, paramList);
			// beanに部活名をセット
			while (rs.next()) {
				bean.setClubName(rs.getString("club_name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServletException(e);
		} finally {
			try {
				db.close();
			} catch (Exception e) {
			}
		}

		// beanをリクエストにセット
		request.setAttribute("bean", bean);

		// 履修講義一覧画面を表示
		request.getRequestDispatcher("A03/RegisterActivity.jsp").forward(request, response);
	}

}
