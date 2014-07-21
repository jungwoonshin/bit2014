<%--
JSTL (JSP Standard Tag Library)
=> JSP 페이지를 작성할 때 사용할 수 있는 추가 라이브러리
=> 다양한 자바 문법을 표현할 수 있는 태그가 있다.
=> JSP에서 직접 DBMS에 질의를 할 수 있는 태그도 있다.

 --%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적 관리</title>
<style type="text/css">
table {
  font-family: verdana,arial,sans-serif;
  font-size:11px;
  color:#333333;
  border-width: 1px;
  border-color: #666666;
  border-collapse: collapse;
}
table th {
  border-width: 1px;
  padding: 8px;
  border-style: solid;
  border-color: #666666;
  background-color: #dedede;
}
table td {
  border-width: 1px;
  padding: 8px;
  border-style: solid;
  border-color: #666666;
  background-color: #ffffff;
}
</style>
</head>
<body>
<h1>성적 관리(Front Controller 도입)</h1>
<a href='scoreform.html'>추가</a>
<table>
<tr>
  <th>번호</th> 
  <th>
	  <c:choose>
	    <c:when test="${order == 'NM_A'}">
	      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}&order=NM_D">이름(a-z)</a>
	    </c:when>
	    <c:when test="${order == 'NM_D'}">
	      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}">이름(z-a)</a>
	    </c:when>
	    <c:otherwise>
	      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}&order=NM_A">이름</a>
	    </c:otherwise>
	  </c:choose>
  </th> 
  <th>
	  <c:choose>
	    <c:when test="${order == 'KO_A'}">
	      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}&order=KO_D">국어(a-z)</a>
	    </c:when>
	    <c:when test="${order == 'KO_D'}">
	      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}">국어(z-a)</a>
	    </c:when>
	    <c:otherwise>
	      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}&order=KO_A">국어</a>
	    </c:otherwise>
	  </c:choose>
  </th> 
  <th>
    <c:choose>
    <c:when test="${order == 'EN_A'}">
      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}&order=EN_D">영어(a-z)</a>
    </c:when>
    <c:when test="${order == 'EN_D'}">
      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}">영어(z-a)</a>
    </c:when>
    <c:otherwise>
      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}&order=EN_A">영어</a>
    </c:otherwise>
  </c:choose>
  </th> 
  <th>
    <c:choose>
    <c:when test="${order == 'MA_A'}">
      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}&order=MA_D">수학(a-z)</a>
    </c:when>
    <c:when test="${order == 'MA_D'}">
      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}">수학(z-a)</a>
    </c:when>
    <c:otherwise>
      <a href="list.do?pageNo=${pageNO}&pageSize=${pageSize}&order=MA_A">수학</a>
    </c:otherwise>
  </c:choose>
  </th> 
  <th>합계</th> 
  <th>평균</th>
  <th></th>
</tr>
<c:forEach var="score" items="${scores}">
<tr>
  <td><a href='update.do?no=${score.no}'>${score.no}</a></td>   
  <td>${score.name}</td>   
  <td>${score.kor}</td>   
  <td>${score.eng}</td>   
  <td>${score.math}</td>   
  <td>${score.total}</td>   
  <td>${score.average}</td>  
  <td><a href='delete.do?no=${score.no}'>삭제</a></td>
</tr>
</c:forEach>
</table>
<div>

<c:choose>
<c:when test="${pageNo > 1}">
  <a href="list.do?pageNo=${pageNo - 1}&pageSize=${pageSize}&order=${order}">[이전]</a>
</c:when>
<c:otherwise>[이전]</c:otherwise>
</c:choose>

${pageNo}

<c:choose>
<c:when test="${pageNo < totalPage}">
  <a href="list.do?pageNo=${pageNo + 1}&pageSize=${pageSize}&order=${order}">[다음]</a>
</c:when>
<c:otherwise>[다음]</c:otherwise>
</c:choose>
</div>
<jsp:include page="/score/step01/copyright.jsp"/>

</body>
</html>








    