<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Facility list</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <style>
        .row{
            margin-bottom: 1rem;
        }
        .d-flex{
            padding-top: 10px;
        }
    </style>
</head>
<body>
<div class="container-fluid text-center">
    <div class="row">
        <div class="col-12">
            <nav class="navbar navbar-expand-lg bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">RESORT MANAGEMENT</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link" href="/CustomerServlet">Customer</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/EmployeeServlet">Employee</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/FacilityServlet">Facility</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/BookServlet">Book</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/ContractServlet">Contract</a>
                            </li>

                        </ul>
                        <form action="/FacilityServlet" method="get" class="d-flex" role="search">
                            <input type="hidden" name="action" value="search" >
                            <input name="id" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-info" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
        </div>

    </div>
    <div class="row">
        <div class="col">
            <div class="d-grid gap-2 ">
                <a href="/FacilityServlet?action=create" class="btn btn-outline-info btn-lg" role="button">
                    <i class="bi bi-plus-lg"></i> ADD
                </a>
            </div>
        </div>
        <div class="col-8">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ID</th>
                    <th scope="col">Period</th>
                    <th scope="col">Area</th>
                    <th scope="col">Max person</th>
                    <th scope="col">Price</th>
                    <th scope="col">Type</th>
                    <th scope="col">Floor</th>
                    <th scope="col">Pool Area</th>
                    <th scope="col">Used Times</th>
                    <th scope="col" colspan="2">Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="list" varStatus="state">
                    <tr>
                        <th scope="row">${state.count}</th>
                        <td>${list.id}</td>
                        <c:forEach items="${period}" var="period">
                            <c:if test="${period.id == list.period}">
                                <td>${period.name}</td>
                            </c:if>
                        </c:forEach>
                        <td>${list.area}</td>
                        <td>${list.max_person}</td>
                        <td>${list.price}</td>
                        <c:forEach items="${type}" var="type">
                            <c:if test="${list.type == type.id}">
                                <td>${type.name}</td>
                            </c:if>
                        </c:forEach>
                        <td>${list.floor}</td>
                        <td>${list.pool_area}</td>
                        <td>${list.times}</td>
<%--                        <c:forEach items="${time}" var="time">--%>
<%--                            <c:if test="${time.id == list.id}">--%>
<%--                                <td>${time.times}</td>--%>
<%--                            </c:if>--%>
<%--                        </c:forEach>--%>
                        <td><a href="/FacilityServlet?action=update&id=${list.id}">
                            <i class="bi bi-wrench"></i>
                        </a></td>
                        <td><a data-bs-target="#delete" data-bs-toggle="modal" href="" onclick="showInfoDelete('${list.id}')" role="button">
                            <i class="bi bi-x-square"></i>
                        </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${index == 1}">
                        <li class="page-item"><a class="page-link" href="#" style="color: darkgray">Previous</a></li>
                    </c:if>
                    <c:if test="${index != 1}">
                        <li class="page-item"><a class="page-link" href="/FacilityServlet?action=paging&index=${index - 1}">Previous</a></li>
                    </c:if>
                    <c:forEach begin="1" end="${maxPages}" var="item">
                        <li class="page-item"><a class="page-link" href="/FacilityServlet?action=paging&index=${item}">${item}</a></li>
                    </c:forEach>
                    <c:if test="${index == maxPages}">
                        <li class="page-item"><a class="page-link" href="#" style="color: darkgray">Next</a></li>
                    </c:if>
                    <c:if test="${index != maxPages}">
                        <li class="page-item"><a class="page-link" href="/FacilityServlet?action=paging&index=${index + 1}">Next</a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
        <div class="col">

        </div>
    </div>
</div>

<%--modal delete--%>
<div class="modal fade" id="delete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/FacilityServlet?action=delete" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input id="deleteId" name="id" type="hidden">
                    <span>Are you delete </span><span id="deleteObject"></span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Delete</button>
                </div>
            </div>
        </form>
    </div>
</div>



<script>
    function showInfoDelete(id) {
        console.log(id);
        document.getElementById("deleteId").value = id;
        document.getElementById("deleteObject").innerText = id;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
