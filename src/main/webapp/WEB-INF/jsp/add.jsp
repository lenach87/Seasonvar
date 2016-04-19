<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Serials</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="http://getbootstrap.com/examples/dashboard/dashboard.css" rel="stylesheet">
</head>
<body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<div class="container">
    <div class="header clearfix">
        <nav class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                    <li class="active"><a href="${rootURL}list">All</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
    </div>

<div class="form-group">
    <form:form action="add" method="post" modelAttribute="addForm"
               class="form-horizontal" role="form" cssStyle="width: 800px; margin: 0 auto;">

    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">To</label>
        <div class="col-sm-4">
            <form:input path="name" type="text" class="form-control" placeholder="To" autofocus="true"/>
            <form:errors path="name" cssStyle="color: #ff0000;"/>
        </div>
    </div>
        <div class="col-sm-offset-2 col-sm-4">
            <button class="btn btn-lg btn-primary btn-block"  type="submit" value="Create"> Add </button>
        </div>
    </div>
    </form:form>
</div>
</div>

</body>
</html>