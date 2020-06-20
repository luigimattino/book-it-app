<%-- 
    Document   : home
    Created on : 17-giu-2020, 9.56.29
    Author     : Mattinò
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Book it App</title>
        <script src="webjars/jquery/3.4.1/jquery.min.js" ></script>
        <link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" />
        <script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js" ></script>
    </head>
    <body>
        <div class="row">
            <div class="col-md-12">
                <h3>Home</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/space-slots">Space Slot List</a>
                    </li>
                    <li class="timeline">
                        <a class="nav-link" href="/timeline">Timeline</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <p>${msg}</p>
            </div>
        </div>
    </body>
</html>
