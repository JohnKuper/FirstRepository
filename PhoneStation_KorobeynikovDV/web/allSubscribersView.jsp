<%-- 
    Author     : Коробейников Дмитрий
--%>

<%@page import="kuper.controller.SubscriberController"%>
<%@page import="kuper.model.Subscriber"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css"/>
        <title>All subscribers</title>
    </head>
    <body>
        <%@include file="headerView.jsp"%>
        <div>
            <div style="color: #FF0000; font-size: small" align="center" >${errorMessage}</div>
            <div style="color: #0000FF; font-size: small" align="center" >${positiveMessage}</div>
            <table>
                <thead>
                    <tr>
                        <th>Абонент ID</th>
                        <th>Фамилия</th>
                        <th>Имя</th>
                        <th>Отчество</th>
                        <th>Адрес</th>
                        <th>Номер квартиры</th>
                        <th>Дата подключения</th>
                        <th>Тариф</th>
                    </tr>
                </thead>
                <tbody>
                    <!--считываем данные о всех абонентах и заполняем страницу данными из базы данных-->
                    <%
                        SubscriberController sc = new SubscriberController();
                        List<Subscriber> list = sc.getSubscribers();
                        for (Subscriber sub : list) {
                    %>

                    <tr>
                        <td><%=String.valueOf(sub.getID())%></td>
                        <td><%=sub.getSurname()%></td>
                        <td><%=sub.getName()%></td>
                        <td><%=sub.getPatronymic()%></td>
                        <td><%=sub.getAddress()%></td>
                        <td><%=sub.getFlatnumber()%></td>
                        <td><%=sub.getDateofRegistration()%></td>
                        <td><%=sub.getTarrif()%></td>

                        <td style="border: none;">
                            <div>
                                <%--кнопка изменить абонента--%>
                                <form method="post" action="subscriberChangeView.jsp">
                                    <input type="hidden" id="updateId" name="updateId" value="<%=String.valueOf(sub.getID())%>"/> 
                                    <input type="submit" value="Изменить..."/> 
                                </form>
                            </div>
                        </td>
                        <td style="border: none;">
                            <div>
                                <%--кнопка удалить абонента--%>
                                <form method="post" action="DeleteSubscriberServlet">
                                    <input type="hidden" id="delId" name="delId" value="<%=String.valueOf(sub.getID())%>"/> 
                                    <input type="submit" value="Удалить"/> 
                                </form>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
