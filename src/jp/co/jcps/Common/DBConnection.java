package jp.co.jcps.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {

	// DB接続関係クラス
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * SELECTクエリを実行し、結果を返す
	 * @param sql
	 * @param paramList
	 * @return
	 * @throws Exception
	 */
	public ResultSet executeSelectQuery(String sql, List<String> paramList) throws Exception {
		try {
			// データソースの取得
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jc21ps");

			// データベースへ接続
			con = ds.getConnection();

			// PreparedStatementにSQLをセット
			pstmt = con.prepareStatement(sql);

			// SQLに埋め込むパラメータをセット
			int paramCount = 0;
			for(String param : paramList) {
				pstmt.setString(paramCount + 1, param);
				paramCount++;
			}

		    // SQLを実行し返却
			System.out.println(pstmt.toString());
			this.rs = pstmt.executeQuery();
			return this.rs;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	/**
	 * INSERT,UPDATEクエリを実行し、結果を返す
	 * @param sql
	 * @param paramList
	 * @return
	 * @throws Exception
	 */
	public void executeInsertUpdateQuery(String sql, List<String> paramList) throws Exception {
		try {
			// データソースの取得
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jc21ps");

			// データベースへ接続
			con = ds.getConnection();

			// PreparedStatementにSQLをセット
			pstmt = con.prepareStatement(sql);

			// SQLに埋め込むパラメータをセット
			int paramCount = 0;
			for(String param : paramList) {
				pstmt.setString(paramCount + 1, param);
				paramCount++;
			}

		    // SQLを実行し返却
			System.out.println(pstmt.toString());
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			try {
				this.con.close();
				this.pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}


	/**
	 * DB接続をクローズ
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		this.con.close();
		this.pstmt.close();
		this.rs.close();
	}
}

