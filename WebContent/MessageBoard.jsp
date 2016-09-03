<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MessageBoard</title>

	<link rel="stylesheet" type="text/css" href="<%=path%>/css/frame.css" />
</head>
<body>
	
	<div class="wrapper">
		<div class="header"></div>
		<div class="navigator">
			<li><a href="#">首页</a></li>
			<li><a href="PhotoWall.jsp">照片墙</a></li>
			<li><a href="">蛋疼的游戏哟</a></li>
			<li><a href="MessageBoard.jsp">留言板</a></li>
		</div>
		<div class="content">
			<div class="message-content">
				<p>{$param.content}</p>
			</div>
			<form name="message-form" id="messageboard" method="post" action="LeaveMessage.do" >
				<p>
					<label>username: </label>
					<input type="text" name="username">
				</p>
				<p>
					<label>say something: </label>
					<textarea rows="10" cols="100" name="content"></textarea>
				</p>
				<p><input type="submit" value="login"></p>
			</form>
		</div>
	</div>


</body>
</html>