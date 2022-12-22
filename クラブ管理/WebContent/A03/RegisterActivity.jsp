<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="messageBean" class="jp.co.jcps.Bean.MessageBean" scope="request" />
<jsp:useBean id="bean" class="jp.co.jcps.A03.RegisterActivityBean" scope="request" />

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
  <form action="/JC21PS/RegisterActivitySave" method="POST">
  <input type="hidden" name=registClubName value=<%= bean.getClubName() %>>
  	<table class='table table-bordered'>
  		<tbody>
  		<tr>
  			<th colspan="1">&nbsp;&nbsp;部活名</th>
  			<td colspan="3"><%= bean.getClubName() %></td>
  		</tr>
  		<tr>
  			<th colspan="1">*&nbsp;活動名</th>
  			<td colspan="3"><input type="text" name="registActivityName"  maxlength="30" value=<%= bean.getActivityName()%>></td>
  		</tr>
  		<tr>
  			<th colspan="1">*&nbsp;活動日</th>
  			<td colspan="1"><input type="date" name="registActivityDate"  maxlength="10" value=<%= bean.getActivityDate()%>></td>
  			<th colspan="1">*&nbsp;活動時間</th>
  			<td colspan="1"><input type="time" name="registActivityStartTime" value=<%= bean.getActivityStartTime()%>>～
  			<input type="time" name="registActivityEndTime" value=<%= bean.getActivityEndTime()%>></td>
  		</tr>
  		<tr>
  			<th colspan="1">*&nbsp;活動場所</th>
  			<td colspan="3"><input type="text" name="registActivityPlace" maxlength="30" value=<%= bean.getActivityPlace()%>></td>
  		</tr>
  		<tr>
  			<th colspan="1">&nbsp;&nbsp;募集人数</th>
  			<td colspan="3"><input type="text" name="registMaxParticipant" maxlength="2" value=<%= bean.getMaxParticipant()%>></td>
  		</tr>
  		<tr>
  			<th colspan="1">&nbsp;&nbsp;活動説明</th>
  			<td colspan="3"><textarea name="registActivityDescription"  rows="5" cols="80" maxlength="400"><%= bean.getActivityDescription()%></textarea></td>
  		</tr>
  		</tbody>
  	</table>
  	<div align="center">
  	<input type='submit' value='登録' onclick='return confirm("活動を登録しますか？");' class="btn btn-primary"/>
  	<input type='button' value='戻る' onclick="location.href='/JC21PS/TopController'" class="btn btn-primary"/>
  	</div>
</form>
</body>
</html>