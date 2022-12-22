package jp.co.jcps.A03;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import jp.co.jcps.Bean.MessageBean;
import jp.co.jcps.Common.CommonCheck;
import jp.co.jcps.Common.Const;
import jp.co.jcps.Common.DBConnection;
import jp.co.jcps.Common.Validation;

/**
 * 活動登録画面のコントローラー
 */
@WebServlet("/RegisterActivitySave")
public class RegisterActivitySaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public RegisterActivitySaveServlet() {
		super();
	}

	/**
	 * POSTでリクエストされた場合
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 共通チェック
		if (!CommonCheck.existSession(request)) {
			// セッションが切れてる場合はログイン画面に遷移
			request.getRequestDispatcher("/Login").forward(request, response);
		}
		//リクエストのエンコードを指定
		request.setCharacterEncoding("UTF-8");

		// 入力値チェック
		MessageBean msg = checkValid(request);
		if (msg.getMessageList().size() != 0) {
			//入力値が不正な場合はエラーメッセージを表示し、入力値を復元する
			RegisterActivityBean bean = restoreInputValue(request);
			request.setAttribute("bean", bean);
			request.setAttribute("messageBean", msg);

			request.getRequestDispatcher("A03/RegisterActivity.jsp").forward(request, response);
			return;

		}

		// 登録用の開始時間と終了時間を成形する
		String startTime = request.getParameter("registActivityDate") + " "
				+ request.getParameter("registActivityStartTime");
		String endTime = request.getParameter("registActivityDate") + " "
				+ request.getParameter("registActivityEndTime");

		// SQLを宣言
		String sql = "INSERT INTO trn_activity (activity_id, club_id, activity_name, activity_place, activity_start_time, activity_end_time, activity_description, max_participant) VALUES (?,?,?,?,?,?,?,?);";

		// SQLに埋め込むパラメータリストを定義
		try {
			List<String> paramList = new ArrayList<String>();
			paramList.add(getNextActivityId());
			paramList.add((String) request.getSession().getAttribute("leaderClubId"));
			paramList.add(request.getParameter("registActivityName"));
			paramList.add(request.getParameter("registActivityPlace"));
			paramList.add(startTime);
			paramList.add(endTime);
			paramList.add(request.getParameter("registActivityDescription"));
			if (StringUtils.isEmpty(request.getParameter("registMaxParticipant"))) {
				paramList.add(request.getParameter(null));
			} else {
				paramList.add(request.getParameter("registMaxParticipant"));
			}

			// SQLを実行し結果を取得
			DBConnection db = new DBConnection();
			db.executeInsertUpdateQuery(sql, paramList);
		}catch(Exception e){
			request.getRequestDispatcher("ERROR/Error.jsp").forward(request, response);
		}
		// msgに登録完了メッセージをセット
		msg.addMessageList("新規活動を登録しました。");
		request.setAttribute("messageBean", msg);

		// TOP画面の呼び出し
		request.getRequestDispatcher("/TopController").forward(request, response);

	}

	/**
	 * 活動登録画面の入力値チェック
	 *
	 * @param msg
	 * @param request
	 * @return
	 */
	private MessageBean checkValid(HttpServletRequest request) {
		// 返却用MessageBeanを初期化
		MessageBean msg = new MessageBean();

		// 必須チェック
		Validation.checkRequired(request.getParameter("registActivityName"), "活動名", msg);
		Validation.checkRequired(request.getParameter("registActivityDate"), "活動日", msg);
		Validation.checkRequired(request.getParameter("registActivityStartTime"), "活動時間(自）", msg);
		Validation.checkRequired(request.getParameter("registActivityEndTime"), "活動時間(至）", msg);
		Validation.checkRequired(request.getParameter("registActivityPlace"), "活動場所", msg);

		// 桁数チェック
		Validation.checkLegalLengthString(request.getParameter("registActivityName"), 30, "活動名", msg);
		Validation.checkLegalLengthString(request.getParameter("registActivityPlace"), 30, "活動場所", msg);
		if (!StringUtils.isEmpty("registActivityDescription")) {
			Validation.checkLegalLengthString(request.getParameter("registActivityDescription"), 400, "活動説明", msg);
		}

		// 型チェック
		if (!StringUtils.isEmpty(request.getParameter("registActivityDate"))) {
			// 活動日の型チェック
			Validation.checkCorrectDate(request.getParameter("registActivityDate"), "活動日", msg);

			// 活動日が翌日以降かのチェック
			Validation.checkIsAfterTommorowDate(request.getParameter("registActivityDate"), "活動日", msg);
		}

		if (!StringUtils.isEmpty(request.getParameter("registActivityStartTime"))
				&& !StringUtils.isEmpty(request.getParameter("registActivityEndTime"))) {
			// 活動日の型チェック
			Validation.checkCorrectActivityTime(request.getParameter("registActivityStartTime"),
					request.getParameter("registActivityEndTime"), msg);
		}
		if (!StringUtils.isEmpty(request.getParameter("registMaxParticipant"))) {
			// 募集人数の型チェック
			Validation.checkCorrectRangeNumber(request.getParameter("registMaxParticipant"), 0, 99, "募集人数", msg);
		}

		return msg;
	}

	/**
	 * シーケンスから次の活動IDを取得する
	 * @return activityId
	 * @throws Exception
	 *
	 */
	private String getNextActivityId() throws Exception {
		String activityId = null;

		DBConnection db = new DBConnection();
		String sql = "SELECT nextval('activity_id_sequence') as id;";
		ResultSet rs = db.executeSelectQuery(sql, new ArrayList<>());

		try {
			while (rs.next()) {
				activityId = Const.ACTIVITY_ID_PREFIX + String.format("%07d", rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return activityId;
	}

	/**
	 * 入力値復元用に画面のBeanに入力値を詰める
	 *
	 **/
	private RegisterActivityBean restoreInputValue(HttpServletRequest request) {
		// 返却用のBean
		RegisterActivityBean bean = new RegisterActivityBean();

		// 各入力値をセット
		bean.setClubName(request.getParameter("registClubName"));
		bean.setActivityName(request.getParameter("registActivityName"));
		bean.setActivityDate(request.getParameter("registActivityDate"));
		bean.setActivityStartTime(request.getParameter("registActivityStartTime"));
		bean.setActivityEndTime(request.getParameter("registActivityEndTime"));
		bean.setActivityPlace(request.getParameter("registActivityPlace"));
		bean.setMaxParticipant(request.getParameter("registMaxParticipant"));
		bean.setActivityDescription(request.getParameter("registActivityDescription"));

		return bean;
	}
}
