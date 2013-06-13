<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="myboard.entity.Board" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
    <script type="text/javascript">
        function checkText(form) {
            /*for (var i=0; form.elements.length; i++ ) {
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
  <p>게시글 등록</p>
  <spring:hasBindErrors name="command" />
  <form:errors path="command" />
  <form name=form method="post" action="/board/save" onsubmit="return checkText(this)">
   <table border=1 width="600">
       <tr>
           <td width="100">title</td>
           <td width="500"><input type="text" name="title" length="50" value="${board.title}"> <form:errors path="board.title" /></td>
       </tr>
       <tr>
           <td width="100">content</td>
           <td width="500"><textarea name="content" cols="50" rows="5">${board.content}</textarea> <form:errors path="board.content" /></td>
       </tr>
       <tr>
           <td width="100">writer</td>
           <td width="500"><input type="text" name="writer" length="50" value="${board.writer}"> <form:errors path="board.writer" /></td>
       </tr>
       <tr>
           <td width="100">pw</td>
           <td width="500"><input type="password" name="pw" length="50" value="${board.pw}"> <form:errors path="board.pw" /></td>
       </tr>
   </table>
      <br>
   <input type="submit" value="등록">   <input type="button" value="취소" onclick="location.href='/board/list'">
  </form>
  <%@ include file="/board/footer.jsp"%>
  </body>
</html>