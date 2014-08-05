<%-- 
    Author     : Коробейников Дмитрий
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css"/>
        <title>New Subscriber</title>
        <script>
            <%--проверяет правильность введенных данных--%>
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
            <%--позволяет набирать только цифры--%>
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
        <form method="post" action="AddNewSubscriberServlet" name="addNewSubscriberForm" onsubmit="return validateForm(addNewSubscriberForm)">
            <%--форма для добавления нового абонента в базу--%>
            <div id="mystyle" class="myform">
                <div style="color: #FF0000; font-size: small" align="center" >${errorMessage}</div>
                <h1 align="center">Данные об абоненте</h1>
                <p align="center">Чтобы добавить нового абонента, введите следующую информацию</p>
                <label>Фамилия<span class="small">Введите фамилию</span></label>
                <input type="text" name="Surname" id="Surname" value="${requestScope.Surname}" maxLength="20" />
                <label>Имя<span class="small">Введите имя</span></label>
                <input type="text" name="Name" id="Name" value="${requestScope.Name}" maxLength="15" />
                <label>Отчество<span class="small">Введите отчество</span></label>
                <input type="text" name="Patronymic" id="Patronymic" value="${requestScope.Patronymic}" maxLength="25" />
                <label>Адрес<span class="small">Укажите адрес</span></label>
                <input type="text" name="Address" id="Address" value="${requestScope.Address}" maxLength="30"/>
                <label>Квартира<span class="small">Укажите номер квартиры</span></label>
                <input type="text" name="Flatnumber" id="Flatnumber" value="${requestScope.Flatnumber}" maxLength="4" onkeypress="return isNumberKey(event)"/>
                <label>Дата подключения<span class="small">Укажите дату</span></label>
                <input type="text" name="DateofRegistration" id="DateofRegistration" value="${requestScope.DateofRegistration}" maxLength="10"  />
                <label>Тариф<span class="small">Выберите тариф </span></label>
                <select name="Tarrif"> 
                    <option>Безлимитный</option>
                    <option>Поминутный</option>
                    <option>Смешанный</option>

                </select>                  
                <button type="submit">Добавить абонента</button>
            </div>
        </form> 


    </body>
</html>
