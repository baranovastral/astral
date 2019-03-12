<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <sec:csrfMetaTags />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список записей</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
        <script src="resources/js/jquery-3.3.1.min.js"></script>
        <script src="resources/js/notes.js"></script>
    </head>
    <body onload="loadNotes();">
        <button class="btn btn-link" onclick="location.href='newNote';">Добавить</button>
        <button class="btn btn-link" onclick="location.href='logout';">Выход</button>
        <input id="search" type="text" placeholder="Поиск" class="fa fa-search" onkeyup="loadNotes()"/>
        <table id="notesTable" class="table">
        </table>
    </body>
</html>
