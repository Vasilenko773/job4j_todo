<%@ page import="ru.job4j.model.Item" %>
<%@ page import="ru.job4j.store.HibernateUtil" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="fu.js"></script>

    <script>
        $(document).ready(
            function() {
                    if ( $('#doneCheckBox').is(':checked') ) {
                        $(document).ready(
                        finaAllItems())
                    } else {
                        $(document).ready(
                        findByDone())
                    }
                }
        );
    </script>

<c:if test="${sessionScope.user == null}">
    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"><c:out value="${item.user.name}"/> || Войти</a>
    </li>
</c:if>

    <c:if test="${user != null}">
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">Выйти</a>
        </li>
    </c:if>
    <form>
        <div class="form-group">
            <label>Добавить новое задание</label>
            <input type="text" class="form-control" id="descriptionId" name="description">
            <small class="form-text text-muted">Необходимо заполнить описание задачи</small>
        </div>
        <button type="submit" class="btn btn-primary" onclick="return saveItem();">Сохранить</button>
    </form>
</head>

<body>



<div class="form-check">
    <input class="form-check-input" type="checkbox" onchange='parseTask()' id="doneCheckBox" checked>
    <label class="form-check-label" for="doneCheckBox">
        Показать все задачи
    </label>
</div>

<table border="2" class="table" id='table'>
    <thead>
    <tr>
        <th>#</th>
        <th>Description</th>
        <th>Created</th>
        <th>Done</th>
        <th>User</th>
    </tr>
    </thead>
    <tbody id="tableBody">
    <tr></tr>
    </tbody>
</table>
</body>
</html>
