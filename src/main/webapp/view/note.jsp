<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Просмотр заметки</title>
        <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    </head>
    <body style="padding: 50px; text-align: center;">
        <img style="height: 40px;" src="img/${note.id}.svg">
        <br>
        <p>${note.message}</p>
        <br>
        <button class="btn btn-link" onclick="location.href = '<c:url value="/"/>';">К списку заметок</button>
    </body>
</html>
