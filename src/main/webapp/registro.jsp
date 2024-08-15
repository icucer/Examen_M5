<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de usuario</title>
    <%@ include file="/assets/html/head.jsp" %>
    <script src="assets/js/verificacion_registro.js"></script>
</head>
<body class="bg-light d-flex flex-column min-vh-100">
    <%@ include file="/assets/html/header.jsp" %>
    <div class="container mt-60">
        <h2>Registrar Usuario</h2>
        <form id="registerForm" onsubmit="event.preventDefault(); handleRegistrationResponse();">
            <div class="form-group mt-5">
                <label for="email" class="fs-4">Correo:</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese su correo electronico" required>
            </div>
            <div class="form-group mt-3">
                <label for="nickname" class="fs-4">Alias:</label>
                <input type="text" class="form-control" id="nickname" name="nickname" placeholder="Ingrese un alias" required>
            </div>
            <div class="form-group mt-3">
                <label for="name" class="fs-4">Nombre completo:</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Ingresar nombre y apellido" required>
            </div>
            <div class="form-group mt-3">
                <label for="password" class="fs-4">Contraseña:</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Ingrese su contraseña" required>
            </div>
            <div class="form-group mt-3">
                <label for="weight" class="fs-4">Peso:</label>
                <input type="number" class="form-control" id="weight" name="weight" placeholder="Ingrese su peso en kg." required>
            </div>
            <div class="form-group mt-3">
                <label for="address" class="fs-4">Direccion:</label>
                <input type="text" class="form-control" id="address" name="address" placeholder="Nombre calle, ciudad, comuna, region" required>
            </div>
            <div class="form-group mt-3">
                <label for="house_number" class="fs-4">Numero casa o departamento:</label>
                <input type="text" class="form-control" id="house_number" name="house_number" placeholder="Ingrese numero de casa o departamento" required>
            </div>
            <button type="submit" class="btn btn-primary mt-5 fs-3">Registrar</button>
        </form>
    </div>
    <div class="mt-60">
        <%@ include file="assets/html/footer.jsp" %>
    </div>
</body>
</html>

