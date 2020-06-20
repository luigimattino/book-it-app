<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<html lang="en">
    <head>
        <%@ page isELIgnored="false" %>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Book it App</title>
        <script src="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/webjars/jquery/3.4.1/jquery.min.js" ></script>
        <link href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/webjars/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" />
        <script src="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/webjars/bootstrap/4.4.1/js/bootstrap.min.js" ></script>
    </head>
    <body>
        <div class="container py-5">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="text-center mb-5">New Space Slot</h2>
                    <div class="card card-outline-secondary">
                        <div class="card-body">
                            <form:form action="/add" modelAttribute="slot" method="post">
                                <div class="form-group">
                                    <form:label path="text">Text</form:label>
                                    <form:input type="text" class="form-control" id="text" path="text"/>
                                    <form:errors path="text" />
                                </div>
                                <input type="submit" value="Add Space Slot">
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>