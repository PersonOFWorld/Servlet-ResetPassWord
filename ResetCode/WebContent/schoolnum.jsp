<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<h1>请输入学号</h1>
			<form class="form" action="resetCode" method="get">
				<input type="text" name="schoolnum" placeholder="请输入你的学号">
				<button type="submit" id="login-button">提交</button>
			</form>
			<div style="margin-top:50px">提示：目前仅供学生更改密码</div>
		</div>
	</div>
</div>

<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>

</body>
</html>