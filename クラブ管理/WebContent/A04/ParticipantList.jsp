<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="messageBean" class="jp.co.jcps.Bean.MessageBean" scope="request" />
<jsp:useBean id="bean" class="jp.co.jcps.A04.ParticipantListBean" scope="request" />

<link rel="stylesheet" type="text/css" href="/JC21PS/css/common.css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部活動管理システム</title>
<style type="text/css">
	th {
		background-color: #add8e6;
	}
</style>
</head>
<body>
  <div class="container"></div>
  <h2 class="teacher-header">活動登録</h2>
  <jsp:include page="/A00/Header.jsp"></jsp:include>

  <%
	// メッセージがある場合は表示
	for(int i = 0; i < messageBean.getMessageList().size(); i++){
		out.println("<p>" + messageBean.getMessageList().get(i) + "</p>");
	}
  %>
  <div style='width:60%; margin:auto'>
  	<table class='table table-bordered'>
  		<tbody>
  		<tr>
  			<th>活動名</th>
  		</tr>
  		<tr>
  			<td><%= bean.getActivityName() %></td>
  		</tr>
  		<tr>
  			<th>参加者一覧</th>
  		</tr>
  		<%--TODO
  		for文を完成させなさい。
  		ヒント
  		size()メソッドでListの要素数を取得することができる
  		 --%>
  		<% 
  		
  		for(int i = 0; i < bean.getParticipantList().size(); i++) {  
  			
  		%>
  			<%--TODO
 			参加者名の一覧が表示されるように実装しなさい。
 			ヒント
 			Listの要素はget(【番号】)で取得することができる。
 			 --%>
  			<tr>
 				<td><%=bean.getParticipantList().get(i)%></td>
 			<tr>
  		<% }%>
  		</tbody>
  	</table>
  	</div>
  	<div align="center">
  	<input type='button' value='戻る' onclick="location.href='/JC21PS/TopController'" class="btn btn-primary"/>
  	</div>
</body>
</html>