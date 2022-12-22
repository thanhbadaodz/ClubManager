package jp.co.jcps.A02;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import jp.co.jcps.Bean.ActivityBean;
import jp.co.jcps.Common.CommonCheck;
import jp.co.jcps.Common.DBConnection;
import jp.co.jcps.Common.Utils;

/**
 * トップ画面のコントローラー
 */
@WebServlet("/TopController")
public class TopControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public TopControllerServlet() {
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

		// セッションからログイン中のユーザーIDを取得する
		String userId = (String) request.getSession().getAttribute("userId");

		// SQLに埋め込むパラメータリストを定義
		List<String> paramList = new ArrayList<String>();
		paramList.add(userId);
		paramList.add(userId);

		// SQLを設定
		String sql = "SELECT activity.*,club.club_id,club.club_name,count.count,isnull(participant.user_id) != 1 as participation_flg FROM trn_activity as activity INNER JOIN mst_club as club USING(club_id) INNER JOIN trn_club_member as member ON club.club_id = member.club_id LEFT JOIN (SELECT activity_id,count(*) as count FROM trn_participant GROUP BY activity_id) as count ON count.activity_id = activity.activity_id LEFT JOIN trn_participant as participant ON participant.user_id = ? AND participant.activity_id = activity.activity_id WHERE member.user_id = ? AND activity.activity_start_time > now() ORDER BY club.club_id ASC,activity.activity_start_time ASC;";

		// DB接続を初期化
		DBConnection db = new DBConnection();

		// 比較用の部活ID
		String tmpClubId = null;
		// Whileの実行回数
		int exeCount = 0;
		// 部活のリストを初期化
		List<List<ActivityBean>> clubList = new ArrayList<>();
		// 活動のリストを初期化
		List<ActivityBean> activityList = new ArrayList<>();
		// トップ画面用のbean
		TopBean bean = new TopBean();

		try {
			// SQLを実行し結果を取得
			ResultSet rs = db.executeSelectQuery(sql, paramList);
			// リストにDBから取得した値をセット
			while (rs.next()) {
				ActivityBean activity = new ActivityBean();

				if (exeCount == 0) {
					// 初回は必ず退避
					tmpClubId = rs.getString("club_id");
					bean.addClubNameList(rs.getString("club_name"));
				} else if (!tmpClubId.equals(rs.getString("club_id"))) {
					// 以降はclub_idが変わったときだけ更新
					tmpClubId = rs.getString("club_id");

					bean.addClubNameList(rs.getString("club_name"));
					// 部活リストに活動リストを追加し、活動リストを初期化
					clubList.add(activityList);
					activityList = new ArrayList<>();
				}

				// DB取得結果をBeanに詰め替える
				activity.setClubId(rs.getString("club_id"));
				activity.setClubName(rs.getString("club_name"));
				activity.setActivityId(rs.getString("activity_id"));
				activity.setActivityName(rs.getString("activity_name"));
				activity.setActivityPlace(rs.getString("activity_place"));
				activity.setDispActivityDate(Utils.getYYYYMMDD(rs.getTimestamp("activity_start_time")));
				activity.setDispActivityTime(Utils.getDispActivityTimeString(rs.getTimestamp("activity_start_time"),
						rs.getTimestamp("activity_end_time")));
				activity.setActivityDescription(rs.getString("activity_description"));
				activity.setParticipantsCount(rs.getInt("count"));
				activity.setMaxParticipant(
						StringUtils.isEmpty(rs.getString("max_participant")) ? "-" : rs.getString("max_participant"));
				activity.setIsParticipationFlg(rs.getBoolean("participation_flg"));
				activity.setIsMajorityFlg(isMajority(rs.getInt("count"), rs.getString("max_participant")));

				// 活動リストに活動を追加
				activityList.add(activity);

				// 実行回数をインクリメント
				exeCount++;
			}
			// 最後の活動リストを部活リストに追加
			if (activityList.size() != 0) {
				clubList.add(activityList);
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

		// トップ画面のBeanにリストをセットし、リクエストにセット
		bean.setClubActivityList(clubList);
		request.setAttribute("bean", bean);

		// 履修講義一覧画面を表示
		request.getRequestDispatcher("A02/Top.jsp").forward(request, response);
	}

	/**
	 * POSTでリクエストされた場合の処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 過半数を超えているかを判定する
	 * @param participant 参加人数
	 * @param maxParticipant 募集人数
	 */
	private boolean isMajority(Integer participant, String maxParticipant) {
		// 空もしくはnullの場合はfalseを返却
		if (StringUtils.isEmpty(maxParticipant)) {
			return false;
		}
		BigDecimal num = BigDecimal.valueOf(participant);
		BigDecimal max = BigDecimal.valueOf(Integer.parseInt(maxParticipant));

		// 過半数判定の閾値
		BigDecimal threshold = new BigDecimal(0.5);

		// 参加人数÷募集人数を計算し、過半数を超えている場合はtrueを返却
		return num.divide(max, 2, BigDecimal.ROUND_HALF_DOWN).compareTo(threshold) >= 0;

	}
}
