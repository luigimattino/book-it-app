<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Book it App</title>
        <script src="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/webjars/jquery/3.4.1/jquery.min.js" ></script>
        <link href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/webjars/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" />
        <script src="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/webjars/bootstrap/4.4.1/js/bootstrap.min.js" ></script>
        <style>
            .custom-table > tbody {
                display: block;
                height: 550px;
                overflow: auto;
            }
            .custom-table > thead, tbody tr {
                display: table;
                width: 100%;         
            }
            .custom-table > thead {
                width: 100%   
            }
            .custom-table {
                width: 100%;
            }
        </style>
    </head>
    <body>
        <div class="container py-5">
            <div class="row">
                <div class="col-md-12">
                    <ul class="nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/home">Home</a>
                        </li>
                        <li class="timeline">
                            <a class="nav-link" href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/timeline">Timeline</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <h2 class="text-center mb-5">Space Slots</h2>
                    <hr  class="mb-5"/>
                    <div class="row">
                        <div class="col-md-6 offset-md-3">
                            <a href="/new-space-slot">
                                <button type="submit">Add new Space Slot</button>
                            </a>
                        </div>
                    </div>
                    <div div class="row">
                        <div class="col-md-6 offset-md-3">
                            <div>Space Slot list</div>
                        </div>
                        <div class="col-md-6 offset-md-3">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover custom-table text-nowrap">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Text</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="slot" items="${slots}">
                                            <tr>
                                                <td>${slot.id}</td>
                                                <td>${slot.text}</td>
                                                <td>
                                                    <div class="row">
                                                        <div class="col-md-auto">
                                                            <a href="space-slot/${slot.id}">
                                                                <button type="submit">Edit</button></a>
                                                        </div>
                                                        <div class="col-md-auto">
                                                            <form action="/space-slot/${slot.id}/delete" class="form form-inline" method="post">
                                                                <input type="submit" value="Delete" />
                                                            </form>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>