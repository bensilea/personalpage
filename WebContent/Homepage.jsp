<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HomePage</title>

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
			<p>content</p>
		</div>
	</div>

</body>
</html>