<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="myboard.entity.Board" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
    <script type="text/javascript">
        function checkText(form) {
            for (var i=0; form.elements.length; i++ ) {
                if (form.elements[i].name != "") {
                    if (form.elements[i].value == "" ) {
                        alert(form.elements[i].name + "을 입력하세요");
                        form.elements[i].focus();
                        return false;
                    }
                }
            }
            return true;
        }
    </script>
  </head>
  <body>
  <p>게시글 보기</p>
  <form:form commandName="board" action="/board/delete" method="POST" onsubmit="return checkText(this)">
   <form:hidden path="id" />
   <table border=1 width="600">
       <tr>
           <td width="100">title</td>
           <td width="500">${board.title}</td>
       </tr>
       <tr>
           <td width="100">content</td>
           <td width="500"><form:textarea path="content" cols="50" rows="5" readonly="true" /></td>
       </tr>
       <tr>
           <td width="100">writer</td>
           <td width="500">${board.writer}</td>
       </tr>
   </table>
   <br>
   <input type="button" value="리스트" onclick="location.href='/board/list'">
   <input type="button" value="수정" onclick="location.href='/board/updateForm?id=${board.id}'">
   pw : <form:password path="pw" length="10" /> <input type="submit" value="삭제">
   </form:form>
  <%@ include file="/board/footer.jsp"%>
  </body>
</html>