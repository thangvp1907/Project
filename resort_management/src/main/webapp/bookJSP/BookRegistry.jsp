<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Book Registry</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col">
        </div>
        <div class="col-6">
            <H1>BOOK REGISTRY</H1>
        </div>
        <div class="col">
        </div>
    </div>
    <div class="row">
        <div class="col">
        </div>
        <div class="col-6">
            <form action="/BookServlet" method="post">
                <c:choose>
                    <c:when test="${item.id == null || (item.id != null && message == 'Create is fail!!!')}">
                        <input type="hidden" name="action" value="doCreate">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="action" value="doUpdate">
                    </c:otherwise>
                </c:choose>
                <c:if test="${message!=null}"><p>${message}</p></c:if>
                <div class="mb-3">
                    <label class="form-label">ID</label>
                    <input type="text" class="form-control" name="id" value="${item.id}" >
                    <c:if test="${error!=nul}">${error.get("id")}</c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label">CUSTOMER NAME</label>
                    <input type="text" class="form-control" name="customer_name" value="${item.customer_name}">
                    <c:if test="${error!=nul}">${error.get("customer_name")}</c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label">FACILITY NAME</label>
                    <input type="text" class="form-control" name="facility_name" value="${item.facility_name}">
                    <c:if test="${error!=nul}">${error.get("facility_name")}</c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label">COMPANION</label>
                    <input type="tel" class="form-control" name="companion" value="${item.companion}">
                </div>
                <div class="mb-3">
                    <label class="form-label">DATE IN</label>
                    <input type="date" class="form-control" name="date_in" value="${item.date_in}">
                    <c:if test="${error!=nul}">${error.get("date_in")}</c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label">DATE OUT</label>
                    <input type="date" class="form-control" name="date_out" value="${item.date_out}">
                    <c:if test="${error!=nul}">${error.get("date_out")}</c:if>
                </div>

                <button type="submit" class="btn btn-primary" >Submit</button>
                <a href="/BookServlet" class="btn btn-secondary" role="button">Back</a>
            </form>
        </div>
        <div class="col">
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
