<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.MatriculantDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: VirtBox
  Date: 19.06.14
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!MatriculantDTO DTO;%>
<%  DTO= (MatriculantDTO)request.getAttribute("DTO");%>
<html>
<head>
    <title>Применая комиссия</title>
    <script type="text/javascript">

        function validate_fio () {
            valid=true;

            if(document.form.matriculant.value=="")
            {
                valid=false;
            }
            return valid;

        }
        function validate_ball() {
            valid=true;
            res = parseFloat(document.form.ball.value);

            if(isNaN(res)==true)
            {
                valid=false;
            }

            return valid;
        }
        function add ( ) {
            valid=true;
            text="";
            err=0;

            if(validate_fio() == false)
            {
                valid=false;
                err++;
                text=text+err+": Пожалуйста заполните поле 'ФИО'. пример: Иванов Иван Иванович"+"\n";
            }
            if(validate_ball() == false)
            {
                valid=false;
                err++;
                text=text+err+": Некорректны введены баллы"+"\n";
            }
            if(text!="") {
                alert(text);
            }
            if(valid) {
                document.form.command.value = "add";
                document.form.submit();
            }
            return valid;
        }
        function check_dataBase() {
            valid=true;
            text="";
            err=0;
            if(validate_fio() == false)  {
                valid=false;
                err++;
                text=text+err+": Пожалуйста заполните поле 'ФИО'. пример: Иванов Иван Иванович"+"\n";
                alert(text);
            }

            if(valid==true) {
                document.form.command.value = "check";
                document.form.submit();
            }
        }
        function edit() {
            valid=true;
            text="";
            err=0;

            if(validate_fio() == false)
            {
                valid=false;
                err++;
                text=text+err+": Пожалуйста заполните поле 'ФИО'. пример: Иванов Иван Иванович"+"\n";
            }
            if(validate_ball() == false)
            {
                valid=false;
                err++;
                text=text+err+": Некорректны введены баллы"+"\n";
            }
            if(text!="") {
                alert(text);
            }
            if(valid) {
                document.form.command.value = "edit";
                document.form.submit();
            }
        }
        function del(){
            document.form.command.value = "del";
            document.form.submit();
        }
        function exit(){
            document.form.command.value = "exit";
            document.form.submit();
        }

    </script>
</head>
<body>
<table border="1" width="400" bgcolor="white" align="center" bordercolor="black" cellpadding="10" cellspacing="5">
    <tr>
        <td>

            <form action="/matriculant" method="GET" name="form">  <!-- style="font-family:helvetica">-->
                <input type="hidden" name="command" value="" />
                <h2 style = "color: blue"><center><i><strong>Сайт приемной комиссии</strong></i></center></h2>

                <p><strong>Факультет</strong><br>
                    <select name="faculty">
                        <option value="1">Исторический факультет</option>
                        <option value="2">Филологический факультет</option>
                        <option value="3">Факультет удмуртской филологии</option>
                        <option value="4">Факультет физической культуры и спорта</option>
                        <option value="5">Факультет социологии и философии</option>
                    </select>
                </p>

                <p>
                    <strong>ФИО</strong><br>

                    <input type="text" name="matriculant" width="200">
                    <input type="button" name="button2" value="Проверить по базе" onclick="check_dataBase()">


                </p>
                <p><strong>Средний балл</strong><br>
                    <input type="text" name="ball" width="5">
                </p>

                <p><strong>Год выпуска</strong><br>
                    <select name="yaers">
                        <% GregorianCalendar gregorianCalendar = new GregorianCalendar();
                            gregorianCalendar.setGregorianChange(new Date());
                            gregorianCalendar.get(1);
                            for(int i=0;i<40;i++) {
                                out.println("<option value="+(gregorianCalendar.get(1)-i)+">"+(gregorianCalendar.get(1)-i)+"</option>");
                            } %>
                    </select>
                </p>

                <p><strong>Комментарий</strong><br>
                    <textarea name="notes" cols="40" rows="5"></textarea>
                </p>

                <%
                    if(DTO != null) {
                        if(DTO.isResult("addNewMatriculant")){
                            out.println("<h2 style = \"color: blue\"><center><i><strong>Добавили успешно!</strong></i></center></h2>");
                        }
                        if(DTO.isResult("isMatriculantExists")){
                            out.println("<p><strong>"+DTO.getName()+" уже есть в базе</strong></p>");
                            out.println("<script type=\"text/javascript\">");
                            out.println("document.form.faculty.value='"+DTO.getFaculty()+"';");
                            out.println("document.form.matriculant.value='"+DTO.getName()+"';");
                            out.println("document.form.ball.value='"+DTO.getBall()+"';");
                            out.println("document.form.yaers.value='"+DTO.getYear()+"';");
                            out.println("document.form.notes.value='"+DTO.getComment()+"';");
                            out.println(" </script>");
                        }
                        if(DTO.isResult("errorIncorrectly")){
                            out.print("<p><strong>Ошибка: Укажите корректно Фамилия Имя Отчество (через пробел и с большой буквы, в т.ч. псевдонимы)</strong></p>");

                        }
                        if(DTO.isResult("notMatriculantExists")){
                            out.println("<p><strong>"+DTO.getName()+" в базе не числится</strong></p>");
                            out.println("<script type=\"text/javascript\">");
                            out.println("document.form.matriculant.value='"+DTO.getName()+"';");
                            out.println(" </script>");
                        }
                        if(DTO.isResult("errorIncorrectly")){
                            out.println("<p><strong>Ошибка: Исправьте значение Баллы</strong></p>");
                        }
                        if(DTO!=null && DTO.isResult("editNewMatriculant")){
                            out.println("<h2 style = \"color: blue\"><center><i><strong>Запись отредактирована!</strong></i></center></h2>");

                            out.println("<script type=\"text/javascript\">");
                            out.println("document.form.faculty.value='"+DTO.getFaculty()+"';");
                            out.println("document.form.matriculant.value='"+DTO.getName()+"';");
                            out.println("document.form.ball.value='"+DTO.getBall()+"';");
                            out.println("document.form.yaers.value='"+DTO.getYear()+"';");
                            out.println("document.form.notes.value='"+DTO.getComment()+"';");
                            out.println(" </script>");
                        }
                        if(DTO.isResult("deleteMatriculant")){
                            out.println("<h2 style = \"color: red\"><center><i><strong>"+DTO.getName()+" удален!</strong></i></center></h2>");
                        }
                    }

                %>

                <% if(DTO == null || DTO.isResult("notMatriculantExists"))  { %>
                <p><input type="button" name="button" value="Добавить" onclick="add()"></p>


                <%} if(DTO != null)  {
                    if(DTO.isResult("isMatriculantExists")) {
                %>
                <p> <input type="button" name="button3" value="Редактировать" onclick="edit()"> </p>
                <p> <input type="button" name="button4" value="Удалить" onclick="del()"> </p>
                <%
                        }
                    }%>
                <p> <input type="button" name="button4" value="Выйти из кабинета" onclick="exit()"> </p>
            </form>


        </td>
    </tr>
</table>
</body>
</html>