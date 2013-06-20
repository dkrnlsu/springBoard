<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="myboard.entity.Board" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
  <title></title>
  </head>
  <body>
  <c:if test="${sessionScope.isLogin eq true}">
    <a href="/login/logout">로그아웃</a>
  </c:if>
  <c:if test="${sessionScope.isLogin eq null || sessionScope.isLogin eq false}">
    <a href="/login/loginForm">로그인</a>
  </c:if>
  | 접속자수 : ${loginCount}
  <br><br>
   <table border=1 width="500">
       <tr>
           <td width="20%" align=center>No.</td>
           <td width="50%" align=center>title</td>
           <td width="30%" align=center>writer</td>
       </tr>
      <c:forEach var="board" items="${boards}">
           <tr>
               <td>${board.id}</td>
               <td><a href="/board/detail?id=${board.id}">${board.title}</a></td>
               <td>${board.writer}</td>
           </tr>
      </c:forEach>
   </table>
   <br>
   <input type="button" value="등록" onclick="location.href='/board/insertForm'">
  <%@ include file="/board/footer.jsp"%>
  </body>
</html>