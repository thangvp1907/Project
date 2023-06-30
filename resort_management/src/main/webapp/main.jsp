<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>main</title>
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

<div class="row">
    <div class="col">

    </div>
    <div class="col-6">
        <div class="d-grid gap-2 ">
            <a href="/CustomerServlet" class="btn btn-outline-info btn-lg" role="button">
                <i class="bi bi-plus-lg"></i> ADD
            </a>
        </div>

        <div class="d-grid gap-2 ">
            <a href="/EmployeeServlet" class="btn btn-outline-info btn-lg" role="button">
                <i class="bi bi-plus-lg"></i> ADD
            </a>
        </div>

        <div class="d-grid gap-2 ">
            <a href="/FacilityServlet" class="btn btn-outline-info btn-lg" role="button">
                <i class="bi bi-plus-lg"></i> ADD
            </a>
        </div>

        <div class="d-grid gap-2 ">
            <a href="/BookServlet" class="btn btn-outline-info btn-lg" role="button">
                <i class="bi bi-plus-lg"></i> ADD
            </a>
        </div>

    </div>
    <div class="col">
        <form>
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">User name</label>
                <input type="text" class="form-control" id="exampleInputEmail1" >
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>
