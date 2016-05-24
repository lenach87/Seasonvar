<%@taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:url var="rootURL" value="/"/>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Seasonvar</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="http://getbootstrap.com/examples/dashboard/dashboard.css" rel="stylesheet"><link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <style type="text/css">
        p {
            font-size: 15px;
            font-family: arial, sans-serif;
        }
        td { line-height: 1em;
            height: 1em;
            overflow: hidden;
            word-wrap: break-word;
            text-overflow: ellipsis;
            font-size: 15px;
            font-family: arial, sans-serif;
        }

        table {
            line-height: 1em;
            height: 1em;
            overflow: hidden;
            word-wrap: break-word;
            text-overflow: ellipsis;
            width: 100%;
            font-size: 15px;
            font-family: arial, sans-serif;}

        td.blue span, td.blue span a {
            display: block;
            height: 100%;
            width: 100%;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${not empty pageContext.request.userPrincipal}">
                    <li><a href="${rootURL}signup">Create new user</a></li>
                    <li><a href="${rootURL}list">Serials</a></li>
                    <form class="navbar-form navbar-right" action='${rootURL}logout' method='POST'>
                        <button type="submit" class="btn btn-primary">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"> </span>
                            Log out as <sec:authentication property="principal.username"/>
                        </button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </c:if>
            </ul>

        </div>
    </div>
</nav>
<div class="container">
    <div class="form-group" style="padding-top: 10px;">
    <form:form action="add" method="post" modelAttribute="addForm"
               class="form-horizontal" role="form" cssStyle="width: 800px; margin: 0 auto;">

    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Serial</label>
        <div class="col-sm-4">
            <form:input path="name" type="text" class="form-control" placeholder="Serial" autofocus="true"/>
            <form:errors path="name" cssStyle="color: #ff0000;"/>
        </div>
    </div>
        <div class="col-sm-offset-2 col-sm-4">
            <button class="btn btn-lg btn-primary btn-block"  type="submit" value="Add"> Add </button>
        </div>
    </form:form>
    </div>
</div>

</body>
</html>