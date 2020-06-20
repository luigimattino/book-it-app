
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
        <link href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet" />
        <script src="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/webjars/bootstrap/4.4.1/js/bootstrap.min.js" ></script>
        <script src="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" ></script>
        <link rel="stylesheet" href="/css/timetablejs.css">
        <link rel="stylesheet" href="/css/demo.css">
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
                            <a class="nav-link" href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/space-slots">Space Slot List</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <h2 class="text-center mb-5">Timeline</h2>
                    <hr  class="mb-5"/>
                    <div id="demo"></div>

                    <div class="card">
                        <div id="no-spaces-alert" class="card-body d-none">
                            <div class="alert alert-danger" role="alert">
                                Non ci sono ancora spazi da prenotare. <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/space-slots" class="alert-link">Inseriscili</a>.
                            </div>
                        </div>
                        <div id="error-feedback-alert" class="card-body d-none">
                            <div class="alert alert-danger" role="alert">
                                Non ci sono ancora spazi da prenotare. <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/space-slots" class="alert-link">Inseriscili</a>.
                            </div>
                        </div>
                        <div id="timetable-body" class="card-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <div id="datepicker"></div>
                                </div>
                                <div class="col-md-8">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <button id="add-event-btn" class="d-none" >Add Event</button>
                                            <div id="add-event-form" class="d-none">
                                                <div class="form-group">
                                                    <div class="row mb-2">
                                                        <div class="col-md-6">
                                                            <select id="space-slot-select" class="custom-select mb-3">
                                                                <option selected>Seleziona slot</option>
                                                                <!-- form ajax -->
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row mb-2">
                                                        <div class="col-md-3">
                                                            <label for="eventStart">Start time</label>
                                                            <input type="time" class="form-control" id="eventStart">
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label for="eventEnd">End time</label>
                                                            <input type="time" class="form-control" id="eventEnd">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label for="eventText">Text</label>
                                                            <input type="text" class="form-control" id="eventText" placeholder="event text">                                                        
                                                        </div>
                                                    </div>
                                                    <div class="row mb-2">
                                                        <div class="col-md-3">
                                                            <button id="save-event-btn" >Save Event</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="timetable"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="/js/syncscroll.js"></script>
        <script src="/js/timetable.js"></script>
        <script src="/js/api.js"></script>


        <script>

        </script>
    </body>
</html>
