package jp.co.jcps.A07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.jcps.Bean.MessageBean;
import jp.co.jcps.Common.CommonCheck;
import jp.co.jcps.Common.DBConnection;
import jp.co.jcps.Common.Validation;

/**
 * 部活情報登録画面のコントローラー
 */
@WebServlet("/ClubInfoRegisterSave")
public class ClubInfoRegisterSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ//
	 */
	public ClubInfoRegisterSaveServlet() {
		super();
	}

	/**
	 * POSTメソッドでリクエストされた場合の処理
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
		MessageBean msg = new MessageBean();
		Validation.checkLegalLengthString(request.getParameter("registClubDescription"), 400, "部活説明", msg);

		// エラーがある場合は入力値を復元してエラーを表示
		if (msg.getMessageList().size() != 0) {
			ClubInfoRegisterBean bean = new ClubInfoRegisterBean();
			bean.setClubName(request.getParameter("registClubName"));
			bean.setClubDescription(request.getParameter("registClubDescription"));

			request.setAttribute("bean", bean);
			request.setAttribute("messageBean", msg);

			request.getRequestDispatcher("A07/ClubInfoRegister.jsp").forward(request, response);
		}

		// セッションからログイン中のユーザーIDを取得する
		String leaderClubId = (String) request.getSession().getAttribute("leaderClubId");

		// SQLを宣言
		String sql = "UPDATE mst_club SET club_description=?  WHERE club_id=?;";

		// SQLに埋め込むパラメータリストを定義
		List<String> paramList = new ArrayList<String>();
		paramList.add(request.getParameter("registClubDescription"));
		paramList.add(leaderClubId);

		// DB接続を初期化
		DBConnection db = new DBConnection();
		try {
			// SQLを実行し結果を取得
			db.executeInsertUpdateQuery(sql, paramList);
		} catch (Exception e) {
			request.getRequestDispatcher("ERROR/Error.jsp").forward(request, response);
		}

		// msgに登録完了メッセージをセット
		msg.addMessageList("部活情報を登録しました。");
		request.setAttribute("messageBean", msg);

		// 一覧画面に遷移
		request.getRequestDispatcher("/TopController").forward(request, response);
	}
}
