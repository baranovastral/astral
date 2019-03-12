<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <sec:csrfMetaTags />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Создать запись</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
        <script src="resources/js/jquery-3.3.1.min.js"></script>
        <script src="resources/js/notes.js"></script>
    </head>
    <body>
        <div id="noteform">
            <form onsubmit="return false;">
                <div class="md-form">
                    <textarea id="message" class="md-textarea form-control" placeholder="Ваша заметка"></textarea>
                </div>
                <button class="btn btn-link" onclick="createNote();">Создать</button>
                <button class="btn btn-link" onclick="location.href = './';">Отмена</button>
            </form>
        </div>
    </body>
</html>
