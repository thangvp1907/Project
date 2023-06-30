<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Facility Registry</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

</head>
<body>
<div class="container-fluid ">
    <div class="row">
        <div class="col">
        </div>
        <div class="col-6">
            <h1>FACILITY REGISTRY</h1>
        </div>
        <div class="col">
        </div>
    </div>
    <div class="row">
        <div class="col">
        </div>
        <div class="col-6">
            <form action="/FacilityServlet" method="post">
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
                    <label class="form-label">PERIOD</label>
                    <select aria-label="Default select example" class="form-select" name="period">
                        <option >Open this select menu</option>
                        <c:forEach items="${period}" var="period">
                            <option value="${period.id}" ${period.id == item.period ? "selected" : ""}>${period.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">AREA</label>
                    <input type="number" class="form-control" name="area" value="${item.area}">
                </div>
                <div class="mb-3">
                    <label class="form-label">COMPANION</label>
                    <input type="number" class="form-control" name="max_person" value="${item.max_person}">
                </div>
                <div class="mb-3">
                    <label class="form-label">PRICE</label>
                    <input type="number" class="form-control" name="price" value="${item.price}">
                </div>
                <div class="mb-3">
                    <label class="form-label">TYPE</label>
                    <select aria-label="Default select example" class="form-select" name="type">
                        <option >Open this select menu</option>
                        <c:forEach items="${type}" var="type">
                            <option value="${type.id}" ${type.id == item.type ? "selected" : ""}>${type.name}</option>
<                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">FLOOR</label>
                    <input type="number" class="form-control" name="floor" value="${item.floor}">
<%--                    <c:if test="${error!=nul}">${error.get("salary")}</c:if>--%>
                </div>
                <div class="mb-3">
                    <label class="form-label">POOL AREA</label>
                    <input type="number" class="form-control" name="pool_area" value="${item.pool_area}">
                    <%--                    <c:if test="${error!=nul}">${error.get("salary")}</c:if>--%>
                </div>
                <button type="submit" class="btn btn-primary" >Submit</button>
                <a href="/FacilityServlet" class="btn btn-secondary" role="button">Back</a>
            </form>
        </div>
        <div class="col">
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
