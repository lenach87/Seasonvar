<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">

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
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="container">
    <div class="header clearfix">
        <nav class="navbar navbar-default navbar-static-top">
            <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${rootURL}list">Serials</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
        </nav>
    </div>

    <div class="form-group">
        <form:form action="signup" method="post" modelAttribute="userForm"
                   class="form-horizontal" role="form" cssStyle="width: 800px; margin: 0 auto;">

        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">E-mail</label>
            <div class="col-sm-4">
                <form:input path="email" type="text" class="form-control" placeholder="E-mail" />
                <form:errors path="email" cssStyle="color: #ff0000;"/>
            </div>
        </div>
        <div class="form-group">
            <label for="login" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-4">
                <form:input path="login" type="text" class="form-control" placeholder="Username" />
                <form:errors path="login" cssStyle="color: #ff0000;"/>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-4">
                <form:input path="password" type="password" class="form-control" placeholder="Password"/>
                <form:errors path="password" cssStyle="color: #ff0000;"/>
            </div>
        </div>
            <div class="form-group">
                <label for="role" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-4">
                    <form:input path="role" type="text" class="form-control" placeholder="Role"/>
                    <form:errors path="role" cssStyle="color: #ff0000;"/>
                </div>
            </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button class="btn btn-lg btn-primary btn-block"  type="submit" value="/signup"> Sign up</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
