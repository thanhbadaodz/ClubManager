<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body>
  <%
  	String leaderClubId = null;
  	// セッション情報を取得
  	if(session.getAttribute("leaderClubId") != null){
  		leaderClubId = (String)session.getAttribute("leaderClubId");
  	}
  %>
  <br/>
  <div style="text-align: right; padding-right:10px;">
  	<% if(leaderClubId != null){ %>
  	<input type="button" onclick="location.href='/JC21PS/RegisterActivityController'" value="活動登録"/>
  	<input type="button" onclick="location.href='/JC21PS/ClubInfoRegisterController'" value="部活情報登録"/>
  	<input type="button" onclick="location.href='/JC21PS/JoinApprovalController'" value="部員登録承認"/>
  	<%} %>
  	<input type="button" onclick="location.href='/JC21PS/JoinRequestController'" value="部員登録申請"/>
  </div>
  <br/>
</body>
