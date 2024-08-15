<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Oficina virtual</title>
  <%@ include file="/assets/html/head.jsp" %>
</head>
<body class="bg-success p-2 text-dark bg-opacity-25 d-flex flex-column min-vh-100">
  <%@ include file="/assets/html/header.jsp" %>
  <div class="container mt-120">
    <h3>Bienvenido a tu oficina virtual</h3>
    <p class="mt-60 fs-3">Hola, <strong><%= session.getAttribute("nombre") %></strong>
      (<%= session.getAttribute("correo") %>)</p>
    <p class="mt-5 fs-3">Has accedido exitosamente al sistema de gestión de trabajadores.</p>

    <form action="${pageContext.request.contextPath}/employee_list" method="GET">
      <button type="submit" class="btn btn-primary mt-60 fs-3">Mostrar Trabajadores y Vehículos</button>
    </form>

    <!-- Formulario para cerrar sesión -->
    <form action="login.jsp" method="post">
      <button type="submit" class="btn btn-primary mt-60 fs-3">Cerrar sesión</button>
    </form>
  </div>
  <div class="mt-auto">
    <%@ include file="assets/html/footer.jsp" %>
  </div>
</body>
</html>
