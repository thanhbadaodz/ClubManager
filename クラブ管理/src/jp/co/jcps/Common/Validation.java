package jp.co.jcps.Common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import jp.co.jcps.Bean.MessageBean;

/**
 * 入力値チェックをするクラス
 *
 */

public class Validation {

	/**
	 * 半角英数かどうかをチェック
	 * @param str チェック対象文字列
	 * @param field 対象フィールド名
	 * @param messageBean
	 * @return messageBean
	 */
	public static MessageBean checkAlphaNumeric(String str, String field, MessageBean messageBean) {
		if(str.length() == 0){
			return messageBean;
		}

		if(str.matches("[0-9a-zA-Z]+")) {
			return messageBean;
		}else {
			messageBean.addMessageList(field + "は半角英数で入力してください。" );
			return messageBean;
		}
	}

	/**
	 * 桁数チェック
	 * maxLength以下であるかをチェック
	 *
	 * @param str チェック対象文字列
	 * @param maxLength 最大文字数
	 * @param field 対象フィールド名
	 * @param messageBean
	 * @return messageBean
	 */
	public static MessageBean checkLegalLengthString(String str, int maxLength, String field, MessageBean messageBean) {
		if(str == null) {
			return messageBean;
		}
		if(str.length() <= maxLength) {
			return messageBean;
		}else {
			messageBean.addMessageList(field + "は" + maxLength + "文字以下で入力してください。" );
			return messageBean;
		}
	}

	/**
	 * 必須チェック
	 * @param str
	 * @param field
	 * @param messageBean
	 * @return
	 */
	public static MessageBean checkRequired(String str,String field, MessageBean messageBean) {
		if(str == null || str.length() == 0) {
			messageBean.addMessageList(field + "は必須項目です。");
			return messageBean;
		}else {
			return messageBean;
		}
	}
	/**
	 * 半角数字チェック
	 * @param num
	 * @param field
	 * @param messageBean
	 * @return
	 */
	public static MessageBean checkNumeric(String num, String field,MessageBean messageBean) {
		// 半角数字の場合はそのまま返却
		if(Pattern.matches("[1-9]?[0-9]+$", num)) {
			return messageBean;
		}
		// 半角数字ではない場合はエラーメッセージを追加して返却
		messageBean.addMessageList(field + "は半角数字で入力してください。");
		return messageBean;
	}

	/**
	 * 数値の範囲チェック
	 * @param num 検査数字
	 * @param min 最小値
	 * @param max 最大値
	 * @param field
	 * @param messageBean
	 * @return
	 */
	public static MessageBean checkCorrectRangeNumber(String num, int min, int max, String field, MessageBean messageBean) {
		try {
			int checkNum = Integer.parseInt(num);
			if(min <= checkNum && checkNum <= max) {
				// 範囲内の数字の場合はそのまま返却
				return messageBean;
			} else {
				// 範囲外の数字の場合はエラーメッセージを追加して返却
				messageBean.addMessageList(field + "は" + min + "以上" + max + "以下で入力してください。");
				return messageBean;
			}
		} catch(NumberFormatException e) {
			// 数値以外の場合はエラーメッセージを追加して返却
			messageBean.addMessageList(field + "は" + min + "以上" + max + "以下で入力してください。");
			return messageBean;
		}
	}

	/**
	 * 存在する日付かどうかチェックする
	 * @param date
	 * @param messageBean
	 * @return
	 */
	public static MessageBean checkCorrectDate(String date, String field, MessageBean messageBean) {
		if (date == null || date.length() != 10) {
			messageBean.addMessageList(field + "が不正です。");
			return messageBean;
		}

		date = date.replace("-", "/");
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		format.setLenient(false);
		try {
			format.parse(date);
			return messageBean;
		} catch (Exception e) {
			messageBean.addMessageList(field + "が不正です。");
			return messageBean;
		}
	}

	/**
	 * 翌日以降かどうかチェックする
	 * @param date
	 * @param field
	 * @param messageBean
	 * @return
	 */
	public static MessageBean checkIsAfterTommorowDate(String date, String field, MessageBean messageBean) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		String today = format.format(now.getTime());
		if(date.compareTo(today) > 0) {
			// 日付が翌日以降ならエラーなし
			return messageBean;
		} else {
			// 過去もしくは当日の場合はエラー
			messageBean.addMessageList(field + "は明日以降で入力してください。");
		}
		return messageBean;
	}

	/**
	 * yyyy/MM/ddの日付かどうかチェックする
	 * @param date
	 * @param field
	 * @param messageBean
	 * @return
	 */
	public static MessageBean checkYYYYMMDDFromatDate(String date, String field, MessageBean messageBean) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			df.format(df.parse(date));
		} catch(ParseException e) {
			// フォーマットできない場合はエラー
			messageBean.addMessageList(field + "はyyyy/MM/dd形式で入力してください。");
		}
		return messageBean;

	}

	/**
	 * 活動時間の型と前後のチェックを行う
	 */

	public static MessageBean checkCorrectActivityTime(String startTime, String endTime, MessageBean messageBean) {

		Pattern pattern = Pattern.compile("([0-9]|[0-1][0-9]|[2][0-3]):([0-9]|[0-5][0-9])$");

		boolean returnFlg = false;

		if(!pattern.matcher(startTime).matches()) {
			messageBean.addMessageList("活動時間（自）はhh:mm形式で入力してください。");
			returnFlg = true;
		}
		if(!pattern.matcher(endTime).matches()) {
			messageBean.addMessageList("活動時間（至）はhh:mm形式で入力してください。");
			returnFlg = true;
		}

		if(returnFlg) {
			return messageBean;
		} else {
			// 前後チェック
			if(startTime.compareTo(endTime) >= 0) {
				messageBean.addMessageList("活動時間の前後関係が不正です。");
			}

		}

		return messageBean;
	}
}
