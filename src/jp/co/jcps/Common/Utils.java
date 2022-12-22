package jp.co.jcps.Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * Utilメソッドを実装するクラス
 *
 */

public class Utils {

	/**
	 * ファイルの中身を文字列で返す
	 * @return String
	 */
	public static String convertFileToString(String filePath) throws IOException{

		// BufferdReaderクラスのオブジェクトを生成
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(file));


		String result = null;
		String row;
		while((row = br.readLine()) != null) {
			result += row;
		}

		br.close();
		return result;
	}

	/**
	 *
	 * 曜日のコードを文字列に変換
	 * @param youbi
	 * @return String
	 */
	public static String convertIntToYoubi(int youbi) {
		switch (youbi) {
		case 1:
			return "月";
		case 2:
			return "火";
		case 3:
			return "水";
		case 4:
			return "木";
		case 5:
			return "金";
		case 6:
			return "土";
		case 7:
			return "日";

		default:
			return null;
		}
	}


	/**
	 *
	 * 過去日かどうか調べる
	 * @param date
	 * @return 過去 : ture
	 */
	public static boolean isPastDate(String date) throws Exception{
		// 当日日付を取得
		Date today = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(date);
			return d.before(today);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * calendarクラスの曜日をISO曜日に変換して返却
	 * calendar 日曜 1 ～ 土曜 7
	 * ISO      月曜 1 ～ 日曜 7
	 * @param calendarYoubi
	 * @return
	 */
	public static int castDayOfWeekCalendarToIso(int calendarYoubi) {
		switch(calendarYoubi){
			case 1:
				return 7;
			case 2:
				return 1;
			case 3:
				return 2;
			case 4:
				return 3;
			case 5:
				return 4;
			case 6:
				return 5;
			case 7:
				return 6;

			default:
				return 0;
		}
	}

	/**
	 * 指定された日付の曜日を取得する
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(String date) {
		// カレンダーインスタンスを初期化
		Calendar cal = Calendar.getInstance();
		// 文字列から年月日を取得
		String splitDate[] = date.split("/",0);
		// 日付をセット
		cal.set(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]) - 1, Integer.parseInt(splitDate[2]));
		return castDayOfWeekCalendarToIso(cal.get(Calendar.DAY_OF_WEEK));
	}

	/**
	 * Timestamp から日付部分を取り出す
	 * @param Timestamp
	 * @return String yyyy/MM/ddの日付
	 */
	public static String getYYYYMMDD(Timestamp timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(timestamp);
	}

	/**
	 * 画面表示用に
	 * hh:mm～hh:mmの文字列を作成する
	 * @param Timestamp Timestamp
	 * @return String
	 */
	public static String getDispActivityTimeString(Timestamp start, Timestamp end) {
		String result;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		result = sdf.format(start) + " ～ " + sdf.format(end);

		return result;
	}
}
