<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적 변경폼</title>
</head>
<body>
<h1>성적 변경</h1>
<form action="update.do" method="post">
번호: <input type="text" name="no" value='${requestScope.score.no}' readonly><br>
이름: <input type="text" name="name" value='${score.name}'><br>
국어: <input type="text" name="kor" value='${score.kor}'><br>
영어: <input type="text" name="eng" value='${score.eng}'><br>
수학 : <input type="text" name="math" value='${score.math}'><br>
시험일 : <input type="text" name="execDate" 
          value='<fmt:formatDate value="${score.execDate}" pattern="yyyy-MM-dd"/>'><br>
<input type="submit" value="변경">
</form>

<jsp:include page="/score/step02/copyright.jsp"/>

</body>
</html>
    
    
    
    
    
    