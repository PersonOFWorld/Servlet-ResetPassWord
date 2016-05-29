<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>西安邮电学校</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">

</head>

<body>
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<h1>输入你的新密码</h1>
			<form name="form1" class="form" action="setCode" method="post" id="qwe">
				<input type="password"  name="password" placeholder="请输入你的新密码" id="pass1">
				<input type="password"  name="password2" placeholder="请确认你的密码" id="pass2">
				<button type="button" id="login-button" onclick="submitForm()" >提交</button>
				<p id="mes" style="font-size:30px;color:red;"></p>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
var btn = document.getElementById('login-button');
var input1 = document.getElementsByTagName('input')[0];
var input2 = document.getElementsByTagName('input')[1];	
var mes = document.getElementsByTagName('p')[0];
var flag;
window.onload = function(){
	input2.onkeyup = function(){
		if(input1.value == input2.value){
			mes.innerHTML = '密码输入一致';
			flag = 1;
		}
		else{
			mes.innerHTML = '密码输入不一致';
			flag = 2;
		}
		return flag;
	}	
}
function submitForm(){
  	if(flag == 1){
  		window.load=validate();
  	}
  	if(flag == 2){
  		alert('密码输入不一致');
  	}
  	function validate(){
  	  	document.getElementsByTagName('form')[0].submit();
  	}
}
</script>
</body>
</html>