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
  <p>로그인</p>
  <form name=form method="post" action="/login/login" onsubmit="return checkText(this)">
   <table border=1 width="600">
       <tr>
           <td width="100">ID</td>
           <td width="500"><input type="text" name="id" length="50" value="${idSave}"> <input type="checkbox" name="idsave" checked>ID 기억</td>
       </tr>
       <tr>
           <td width="100">PASSWORD</td>
           <td width="500"><input type="password" name="password" length="50"></td>
       </tr>
   </table>
      <br>
   <input type="submit" value="로그인">   <input type="button" value="취소" onclick="location.href='/board/list'">
  </form>
  <%@ include file="/board/footer.jsp"%>
  </body>
</html>