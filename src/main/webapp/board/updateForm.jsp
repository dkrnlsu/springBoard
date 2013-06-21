<%@ page import="java.util.Date" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="myboard.entity.Board" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
    <script type="text/javascript">
        function checkText(form) {
 /*           for (var i=0; form.elements.length; i++ ) {
                if (form.elements[i].name != "") {
                    if (form.elements[i].value == "" ) {
                        alert(form.elements[i].name + "을 입력하세요");
                        form.elements[i].focus();
                        return false;
                    }
                }
            }*/
            return true;
        }
    </script>
  </head>
  <body>
  <p>게시글 수정</p>
  <spring:hasBindErrors name="command" />
  <form:errors path="command" />
  <form:form commandName="board" action="/board/update" method="POST" onsubmit="return checkText(this)">
    <form:hidden path="id" />
  <form:errors path="id" />
   <table border=1 width="600">
       <tr>
           <td width="100">title</td>
           <td width="500"><form:input path="title" length="50" /> <form:errors path="title" /></td>
       </tr>
       <tr>
           <td width="100">content</td>
           <td width="500"><form:textarea path="content" cols="50" rows="5" /> <form:errors path="content" /></td>
       </tr>
       <tr>
           <td width="100">writer</td>
           <td width="500"><form:input path="writer" length="50" /> <form:errors path="writer" /></td>
       </tr>
       <tr>
           <td width="100">pw</td>
           <td width="500"><form:password path="pw" length="50" /> <form:errors path="pw" /></td>
       </tr>
   </table>
      <br>
   <input type="submit" value="수정">   <input type="button" value="취소" onclick="location.href='/board/list'">
  </form:form>
  <%@ include file="/board/footer.jsp"%>
  </body>
</html>