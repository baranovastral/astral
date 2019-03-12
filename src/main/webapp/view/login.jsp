<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Аутентификация</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
        <script src="resources/js/jquery-3.3.1.min.js"></script>
        <script src="resources/js/login.js"></script>
    </head>
    <body>
        <div id="loginForm">
            <form method="POST" onsubmit="return false;">
                <input id="token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="form-group">
                    <label for="login">Логин</label>
                    <input class="form-control" name="username" id="username" type="text" placeholder="Введите Ваш логин">
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input class="form-control" name="password" id="password" type="password" placeholder="Введите Ваш пароль">
                </div>
                <button class="btn btn-link" onclick="login();">Войти</button>
                <button class="btn btn-link" onclick="location.href = 'register';">Зарегистрироваться</button>
            </form>
        </div>
    </body>
</html>
