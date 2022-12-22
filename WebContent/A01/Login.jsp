<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="messageBean" class="jp.co.jcps.Bean.MessageBean" scope="request" />
<link rel="stylesheet" type="text/css" href="/JC21PS/css/common.css" />
<html>
<head>
<meta charset="UTF-8">
<title>部活動管理システム</title>
</head>
<body>
<%
	// メッセージがある場合は表示
	for(int i = 0; i < messageBean.getMessageList().size(); i++){
		out.println("<p>" + messageBean.getMessageList().get(i) + "</p>");
	}
%>
<div class="login">
  <h2 class="teacher-header">部活動管理システム</h2>
  <form class="login-container" action="/JC21PS/Login" method="post">
    <p><input type="text" placeholder="ログイン名" name="loginName"></p>
    <p><input type="password" placeholder="パスワード" name="password"></p>
    <p><input type="submit" value="Log in" style="background: #1e90ff"></p>
  </form>
</div>

</body>
</html>