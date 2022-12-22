package jp.co.jcps.A04;

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
 * 参加者一覧画面のコントローラー
 */
@WebServlet("/ParticipantListController")
public class ParticipantListControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ParticipantListControllerServlet() {
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

		// リクエストパラメーターから活動IDを取得する
		// TODO: リクエストから遷移元でクリックされた活動IDを取得できるように44行目を変更しなさい。
		String activityid = (String) request.getParameter("activityId");

		// TODO: データベースから必要な情報を取得するためのSQL文を完成させなさい。
		String sql = "SELECT activity_name, user_name From trn_participant "
				+ "join mst_user on trn_participant.user_id = mst_user.user_id "
				+ "join trn_activity on trn_participant.activity_id = trn_activity.activity_id "
				+ "WHERE trn_participant.activity_id = ?";
				
		// SQLに埋め込むパラメータリストを定義
		List<String> paramList = new ArrayList<String>();
		// TODO: SQLに埋め込む値を設定しなさい.
		paramList.add(activityid);
		//paramList.add(activityid);

		// DB接続を初期化
		DBConnection db = new DBConnection();

		// 活動登録画面のBeanを初期化
		ParticipantListBean bean = new ParticipantListBean();
		

		try {
			// SQLを実行し結果を取得
			ResultSet rs = db.executeSelectQuery(sql, paramList);
			// beanに画面に出力する情報をセット
			while (rs.next()) {
				/* TODO: データベースから取得した情報をbeanにセットしなさい。
				ヒント①
				ParticipantListBeanは活動名と参加者リストのプロパティがあり、
				活動名はsetActivityName()
				参加者リストはaddParticipantList()
				で設定・追加ができる。
				ヒント②
				DBから取得した情報はResultSetクラスのgetString()メソッドで取得する。
				getStringメソッドの引数は取得したいカラム名を文字列で指定する。
				 */
				bean.setActivityName(rs.getString("activity_name"));
				bean.addParticipantList(rs.getString("user_name"));
				
		
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

		// beanをリクエストにセット
		request.setAttribute("bean", bean);

		// 履修講義一覧画面を表示
		request.getRequestDispatcher("A04/ParticipantList.jsp").forward(request, response);
	}

}
