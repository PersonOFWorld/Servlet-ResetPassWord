<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-2.1.3.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<div style="display:none">
			<input id="page" value="${page }" />
			<input id="result" value="${result }" />
			<input id="schoolnum" value="<%=session.getAttribute("userNumber") %>">
		</div>
		
<script>
	direct();
	function direct(){
		var result=document.getElementById('result').value;
		var page=document.getElementById('page').value;
		if(page=="resetCode"){
			alert(result);
			studySubmit();
		}
		if(page=="validate.jsp")
			window.location.href  = "validate.jsp";
		if(page=="modify.jsp"){
			alert(result);
			window.location.href  = "modify.jsp";	
		}
		if(page=="schoolnum.jsp"){
			alert(result);
			window.location.href  = "schoolnum.jsp";	
		}
		if(page=="success"){
			alert(result)
			window.location.href  = "http://222.24.19.74";	
		}
	}
	function studySubmit(){
		var schoolnum=document.getElementById("schoolnum").value;
		var url = "./resetCode?schoolnum="+ schoolnum;   
		window.location.href = url;  
	}
</script>
	
</body>
</html>