<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login al sistema</title>
    <%@ include file="/assets/html/head.jsp" %>
</head>
<body class="bg-light d-flex flex-column min-vh-100">
    <%@ include file="/assets/html/header.jsp" %>
    <div class="container mt-120">
        <h3>Iniciar Sesión</h3>
        <form action="login-servlet" method="post">
            <div class="form-group mt-60">
                <label for="email" class="fs-3">Correo:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group mt-5">
                <label for="password" class="fs-3">Contraseña:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary mt-60 fs-3">Ingresar</button>
        </form>
    </div>
    <div class="mt-auto">
        <%@ include file="assets/html/footer.jsp" %>
    </div>
</body>
</html>
