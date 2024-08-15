<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trabajadores y vehículos</title>
    <%@ include file="/assets/html/head.jsp" %>
</head>
<body class="bg-success p-2 text-dark bg-opacity-25 d-flex flex-column min-vh-100">
<%@ include file="/assets/html/header.jsp" %>
<div class="container mt-120">
    <h2>Trabajadores y vehículos a cargo</h2>
    <%
        String userRole = (String) session.getAttribute("rol");

        if ("admin".equals(userRole)) {
    %>
    <table border="1" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Modelo del vehículo</th>
            <th>Marca del vehículo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.nombre}</td>
                <td>
                    <c:choose>
                        <c:when test="${empty employee.modelo}">
                            No tiene vehículo
                        </c:when>
                        <c:otherwise>
                            ${employee.modelo}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${empty employee.marca}">
                            No tiene vehículo
                        </c:when>
                        <c:otherwise>
                            ${employee.marca}
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%
    } else {
    %>
    <p class="text-danger fs-4">No tienes permisos suficientes para ver todos los registros de trabajadores y vehículos.</p>
    <table border="1" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Modelo del vehículo</th>
            <th>Marca del vehículo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.nombre}</td>
                <td>
                    <c:choose>
                        <c:when test="${empty employee.modelo}">
                            No tiene vehículo
                        </c:when>
                        <c:otherwise>
                            ${employee.modelo}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${empty employee.marca}">
                            No tiene vehículo
                        </c:when>
                        <c:otherwise>
                            ${employee.marca}
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%
        }
    %>
    <a href="${pageContext.request.contextPath}/home.jsp" class="btn btn-secondary mt-3">
        Oficina virtual
    </a>
</div>
<div class="mt-auto">
    <%@ include file="assets/html/footer.jsp" %>
</div>
</body>
</html>
