<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="messageBean" class="jp.co.jcps.Bean.MessageBean" scope="request" />
<%--TODO beanの読み込み --%>
<jsp:useBean id="bean" class="jp.co.jcps.A07.ClubInfoRegisterBean" scope="request" />

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
  <h2 class="teacher-header">部活情報登録</h2>
  <jsp:include page="/A00/Header.jsp"></jsp:include>

  <%
	// メッセージがある場合は表示
	for(int i = 0; i < messageBean.getMessageList().size(); i++){
		out.println("<p>" + messageBean.getMessageList().get(i) + "</p>");
	}
  %>
  <form action="/JC21PS/ClubInfoRegisterSave" method="POST">
  <input type="hidden" name=registClubName value=<%= bean.getClubName() %>>
  	<table class='table table-bordered' style='width: 70%;margin:auto'>
  		<tbody>
  		<tr>
  			<th colspan="1">部活名</th>
  			<%--TODO 部活名の表示 --%>
  			<td colspan="3"><%= bean.getClubName() %></td>
  		</tr>
  		<tr>
  			<th colspan="1">部活説明</th>
  			<%--TODO 部活説明の表示 --%>
  			<td colspan ="3"><textarea name="registClubDescription"  rows="5" cols="100" maxlength="400"><%= bean.getClubDescription() %></textarea></td>
  		</tbody>
  	</table>
  	<div align="center">
  	<input type='submit' value='登録' onclick='return confirm("登録しますか？");' class="btn btn-primary"/>
  	<input type='button' value='戻る' onclick="location.href='/JC21PS/TopController'" class="btn btn-primary"/>
  	</div>
</form>
</body>
</html>