<%@ page import="java.util.*" %>
<%@ page import="models.InstructorDTO" %>
<!--< 5%@page contentType="text/html" pageEncoding="UTF-8"%>-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Push Git-a</title>
</head>
<body>

<%
    InstructorDTO DTO = (InstructorDTO) request.getAttribute("DTO");
    // Если этот тотже поользователь
    String isLogin = (String) request.getSession().getAttribute("isLogin");
    if (isLogin != null && (InstructorDTO)DTO!=null)
    {
        if (isLogin.equals(request.getSession().getId()))
        {                 // Если он прошел авторизацию
            out.println("<script type=\"text/javascript\">" +
                    "window.location.href='matriculant.jsp'" +
                    "</script>");
        }
    }
%>
<table border="1" width="400" bgcolor="white" align="center" bordercolor="black" cellpadding="10" cellspacing="5">
    <tr>
        <td>
            <form action="/login" method="POST" name="login">
                <input type="hidden" name="command" value=""/>

                <p>
                    <strong>адрес</strong><br>
                    <input type="text" name="host" width="200" value="localhost:3306">
                </p>

                <p>
                    <strong>Логин</strong><br>
                    <input type="text" name="login" width="200" value="user1">
                </p>

                <p>
                    <strong>Пароль</strong><br>
                    <input type="password" name="password" width="200" value="12345">
                    <%
                        if (DTO != null) {

                                if (!DTO.isIS()) {
                                    out.println("<p><strong>Неверный логин или пароль</strong></p>");
                            }
                        }

                    %>
                </p>

                <input type="submit" name="pass" value="Войти">

            </form>
        </td>
    </tr>
</table>
</body>
</html>
