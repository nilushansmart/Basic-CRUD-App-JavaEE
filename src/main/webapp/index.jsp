<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f7f9fc;
            padding: 30px;
        }

        .container {
            max-width: 900px;
        }

        .card {
            border-radius: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center mb-4 text-primary">Employee Management System</h2>

    <!-- Employee Form -->
    <div class="card p-4 shadow-sm">
        <h5 class="mb-3">Add / Update Employee</h5>
        <form id="employeeForm">
            <div class="row mb-3">
                <div class="col-md-2">
                    <input type="number" class="form-control" id="txtIdNo" placeholder="ID No" required>
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" id="txtName" placeholder="Name" required>
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" id="txtAddress" placeholder="Address" required>
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" id="txtPosition" placeholder="Position" required>
                </div>
            </div>
            <div class="d-flex justify-content-between">
                <button type="button" class="btn btn-success" onclick="saveEmployee()">Save</button>
                <button type="button" class="btn btn-primary" onclick="updateEmployee()">Update</button>
                <button type="button" class="btn btn-danger" onclick="deleteEmployee()">Delete</button>
                <button type="button" class="btn btn-secondary" onclick="getAllEmployees()">View All</button>
            </div>
        </form>
    </div>

    <!-- Employee Table -->
    <div class="card mt-4 p-3 shadow-sm">
        <h5 class="mb-3">Employee List</h5>
        <table class="table table-striped table-hover" id="employeeTable">
            <thead class="table-dark">
            <tr>
                <th>ID No</th>
                <th>Name</th>
                <th>Address</th>
                <th>Position</th>
            </tr>
            </thead>
            <tbody id="display">
<%--            <tr>--%>
<%--                <td></td>--%>
<%--                <td></td>--%>
<%--                <td></td>--%>
<%--                <td></td>--%>
<%--            </tr>--%>
            </tbody>
        </table>
    </div>
</div>

<script>
    function saveEmployee() {
        const idNo = document.getElementById("txtIdNo").value;
        const name = document.getElementById("txtName").value;
        const address = document.getElementById("txtAddress").value;
        const position = document.getElementById("txtPosition").value;

        fetch('http://localhost:8080/CRUD_App_war_exploded/employee', {
            method: 'POST',
            body: JSON.stringify({
                idNo: idNo,
                name: name,
                address: address,
                position: position
            }),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
        })
            .then((response) => response.json())
            .then((json) => {
                if (json === true) {
                    alert('Employee added successfully......');
                    document.getElementById("txtIdNo").value = "";
                    document.getElementById("txtName").value = "";
                    document.getElementById("txtAddress").value = "";
                    document.getElementById("txtPosition").value = "";
                } else {
                    alert('Employee added failed.....');
                }
            });
    }
</script>

</body>
</html>
