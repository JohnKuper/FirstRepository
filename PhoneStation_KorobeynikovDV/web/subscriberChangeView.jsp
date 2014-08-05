<%-- 
    Author     : Коробейников Дмитрий
--%>

<%@page import="kuper.model.Subscriber"%>
<%@page import="kuper.controller.SubscriberController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css"/>
        <title>Subscriber Update Page</title>
        <script>
            function validateForm(form) {
                var surname = form.Surname.value;
                var lettersonly_pattern = /^[а-яА-я]+$/;
                var checksurname = lettersonly_pattern.test(surname);
                if (checksurname == false) {
                    alert("Корректно укажите фамилию абонента");
                    form.Surname.focus();
                    return false;
                }
                var name = form.Name.value;
                var checkname = lettersonly_pattern.test(name);
                if (checkname == false) {
                    alert("Корректно укажите имя абонента");
                    form.Name.focus();
                    return false;
                }
                var patronymic = form.Patronymic.value;
                var checkpatronymic = lettersonly_pattern.test(patronymic);
                if (checkpatronymic == false) {
                    alert("Корректно укажите отчество абонента");
                    form.Patronymic.focus();
                    return false;
                }
                var address = form.Address.value;
                var addresspattern = /^[а-яА-я0-9]+[а-яА-я0-9.,\s'-]+[а-яА-я0-9]+$/;
                var checkaddress = addresspattern.test(address);
                if (checkaddress == false) {
                    alert("Корректно укажите адрес абонента");
                    form.Address.focus();
                    return false;
                }
                var flatnumber = form.Flatnumber.value;
                var numbersonly = /^[0-9]+$/;
                var checkflatnumber = numbersonly.test(flatnumber);
                if (checkflatnumber == false) {
                    alert("Корректно укажите квартиру абонента");
                    form.Flatnumber.focus();
                    return false;
                }
                var dateofreg = form.DateofRegistration.value;
                var datepattern = /^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$/;
                var checkdate = datepattern.test(dateofreg);
                if (checkdate == false) {
                    alert("Корректно укажите дату подключения абонента");
                    form.DateofRegistration.focus();
                    return false;
                }
                
            }
            function isNumberKey(evt) {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;
                return true;
            }
        </script>
    </head>
    <body>
        <%@include file="headerView.jsp"%>
        <%--получаем абонента данные которого нужно изменить--%>
        <%
            int id = Integer.parseInt(request.getParameter("updateId"));
            SubscriberController sc = new SubscriberController();
            Subscriber sub = sc.getSubcriber(id);
        %>
        <%--форма для изменения данных абонента--%>
        <div id="mystyle" class="myform">
            <form id="form" name="subscriberChangeForm" action="UpdateSubscriberServlet" method="post" onsubmit="return validateForm(subscriberChangeForm)">
                <h1 align="center">Обновить данные абонента с ID:<%=sub.getID()%></h1>
                <p align="center">Внесите изменения в данные абонента с ID:<%=sub.getID()%></p>
                <label><input type="hidden" name="ID" id="ID" value="<%=sub.getID()%>"/><span class="small"></span></label>                    
                <label>Фамилия<span class="small">Введите фамилию</span></label>
                <input type="text" name="Surname" id="Surname" value="<%=sub.getSurname()%>" maxlength="20" />
                <label>Имя<span class="small">Введите имя</span></label>
                <input type="text" name="Name" id="Name" value="<%=sub.getName()%>" maxlength="15"/>
                <label>Отчество<span class="small">Введите отчество</span></label>
                <input type="text" name="Patronymic" id="Patronymic" value="<%=sub.getPatronymic()%>" maxlength="25" />
                <label>Адрес<span class="small">Укажите адрес</span></label>
                <input type="text" name="Address" id="Address" value="<%=sub.getAddress()%>" maxlength="30"/>
                <label>Квартира<span class="small">Укажите номер квартиры</span></label>
                <input type="text" name="Flatnumber" id="Flatnumber" value="<%=sub.getFlatnumber()%>" maxlength="4" onkeypress="return isNumberKey(event)"/>
                <label>Дата подключение<span class="small">Укажите дату</span></label>
                <input type="text" name="DateofRegistration" id="DateofRegistration" value="<%=sub.getDateofRegistration()%>" maxlength="10" />
                <label>Тариф<span class="small">Выберите тариф </span></label>
                <select name="Tarrif"> 
                    <option>Безлимитный</option>
                    <option>Поминутный</option>
                    <option>Смешанный</option>

                </select>                                  
                <button type="submit">Обновить данные</button>
                <div class="spacer"></div>
            </form>
        </div>   
    </body>
</html>
