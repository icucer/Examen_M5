<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de trabajadores</title>
    <%@ include file="/assets/html/head.jsp" %>
</head>
<body class="bg-info p-2 text-dark bg-opacity-25 d-flex flex-column min-vh-100">
    <%@ include file="/assets/html/header.jsp" %>
    <section class="container">
        <h2 class="mt-120">Bienvenido al sistema de gestión de trabajadores</h2>
        <div class="mt-60 fs-3  lh-lg text-justify">
            <p>Este sistema ha sido diseñado para facilitar la administración y gestión de los trabajadores de tu empresa. Aquí podrás registrar nuevos usuarios, gestionar sus datos y controlar el acceso al sistema.</p>
            <p>Para comenzar, elige una de las siguientes opciones:</p>
        </div>
        <div class="d-flex justify-content-center mt-120">
            <button onclick="location.href='registro.jsp';" class="btn btn-primary fs-4 mx-4">Registrar Usuario</button>
            <button onclick="location.href='login.jsp';" class="btn btn-primary fs-4 mx-4">Iniciar Sesión</button>
        </div>
    </section>
    <div class="mt-auto">
        <%@ include file="assets/html/footer.jsp" %>
    </div>
</body>
</html>
