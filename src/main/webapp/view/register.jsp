<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <sec:csrfMetaTags />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Регистрация</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
        <script src="resources/js/jquery-3.3.1.min.js"></script>
        <script src="resources/js/register.js"></script>
    </head>
    <body>
        <div id="loginForm">
            <form onsubmit="return false;">
                <div class="form-group">
                    <label for="login">Логин</label>
                    <input class="form-control" name="username" id="username" type="text" placeholder="Введите логин">
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input class="form-control" name="password" id="password" type="password" placeholder="Введите пароль">
                </div>
                <button class="btn btn-link" onclick="register();">Зарегистрироваться</button>
            </form>
        </div>
    </body>
</html>
