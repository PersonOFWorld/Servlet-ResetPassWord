<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>西安邮电大学</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container" style="padding-top:45px">
			<h1>验证教务登录</h1>
			<form class="form" method="post" action="./resetCode">
				<input type="text" id="schoolnum" name="schoolnum" placeholder="请输入你的学号" value="<%=session.getAttribute("userNumber") %>">
				<input type="password" name="password" placeholder="请输入你的教务密码">
				<input type="text" name="validate" placeholder="请输入验证码"><img src="" onclick="javascript:getImage()"><a onclick="javascript:getImage();return false;" href="#">看不清</a> <br>
				<button type="submit" id="login-button">提交</button>
			</form>
		</div>
	</div>
</div>
<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script>
		init();
		function init() {
			$.get("./setCode", 
				function(data) {
				//其实在第一次运行的时候是可以直接将图片的二进制作为Image的src进行显示的，但是因为后台统一返回的是  
				//经过base64编码过后的内容，所以这里初始显示的时候也是利用base64的方法  
				$("img").attr("src", "data:image/jpeg;base64," + data);
			});
		}
		function getImage(){
			var schoolnum=document.getElementById("schoolnum").value;
			var url = "./resetCode?schoolnum="+ schoolnum;   
			window.location.href = url; 
		}
</script>

</body>
</html>