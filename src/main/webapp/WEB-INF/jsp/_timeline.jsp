
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
        <link rel="stylesheet" href="/css/timeline.css">
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
                    <c:choose>
                        <c:when test="${empty spaceSlots}">
                            <div class="alert alert-danger" role="alert">
                                Non ci sono ancora spazi da prenotare. <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/space-slots" class="alert-link">Inseriscili</a>.
                            </div>
                        </c:when>    
                        <c:otherwise>
                            <div class="card">
                                <div class="card-body">
                                    <div class="tl-container">
                                        <div class="tl-dashboard">
                                            DASHBOARD
                                        </div>
                                        <div class="tl-view">
                                            <table>
                                                <thead class="tl-head">
                                                    <tr>
                                                        <td class="tl-resource-area">
                                                            <span class="resource-area-head">Rooms</span>
                                                        </td>
                                                        <td class="tl-divider"></td>
                                                        <td>
                                                            <div class="tl-scroller-clip">
                                                                <div style="overflow: scroll hidden; margin: 0px 0px -17px;">
                                                                    <table>
                                                                        <tbody>
                                                                            <tr class="tl-chrono">
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </thead>
                                                <tbody class="tl-body">
                                                    <tr>
                                                        <td class="tl-resource-area">
                                                            <div class="tl-scroller-clip">
                                                                <div style="overflow: auto scroll; height: 534px; margin: 0px -17px 0px 0px;">
                                                                    <table style="width: 100%">
                                                                        <tbody>
                                                                            <c:forEach var="spaceSlot" items="${spaceSlots}">
                                                                                <tr>
                                                                                    <td>
                                                                                        <div class="resource-area-cell">${spaceSlot.text}</div>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="tl-divider"></td>
                                                        <td>
                                                            <div class="tl-scroller-clip">
                                                                <div style="overflow: auto; height: 534px; margin: 0px;">
                                                                    <div style="width: 1280px; height: 1080px;"></div>
                                                                </div>
                                                            </div>

                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!--
                                        <div class="timeline-rooms">
                                            <div class="timeline-rooms-head">Rooms</div>
                                        <c:forEach var="spaceSlot" items="${spaceSlots}">
                                            <div class="timeline-rooms-item">${spaceSlot.text}</div>
                                        </c:forEach>
                                    </div>
                                    <div class="timeline-chrono">
                                        <table>
                                            <tbody>
                                                <tr class="chrono">
                                                    <th class="chrono-header">0:00</th>
                                            <th class="chrono-header">1:00</th>
                                            <th class="chrono-header">2:00</th>
                                            <th class="chrono-header">3:00</th>
                                            <th class="chrono-header">4:00</th>
                                            <th class="chrono-header">5:00</th>
                                            <th class="chrono-header">6:00</th>
                                            <th class="chrono-header">7:00</th>
                                            <th class="chrono-header">8:00</th>
                                            <th class="chrono-header">9:00</th>
                                            <th class="chrono-header">10:00</th>
                                            <th class="chrono-header">11:00</th>
                                            <th class="chrono-header">12:00</th>
                                            <th class="chrono-header">13:00</th>
                                            <th class="chrono-header">14:00</th>
                                            <th class="chrono-header">15:00</th>
                                            <th class="chrono-header">16:00</th>
                                            <th class="chrono-header">17:00</th>
                                            <th class="chrono-header">18:00</th>
                                            <th class="chrono-header">19:00</th>
                                            <th class="chrono-header">20:00</th>
                                            <th class="chrono-header">21:00</th>
                                            <th class="chrono-header">22:00</th>
                                            <th class="chrono-header">23:00</th>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                        -->
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <script src="/js/timeline.js"></script>
    </body>
</html>
