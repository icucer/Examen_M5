package cl.praxis.examen_m5.servlet;

import cl.praxis.examen_m5.conexion.Conexion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "register-servlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los datos del formulario
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int weight = Integer.parseInt(request.getParameter("weight"));
        String address = request.getParameter("address");
        String houseNumber = request.getParameter("house_number");

        // Conectar a la base de datos
        try (Connection connection = Conexion.getConnection()) {
            if (emailRegistered(connection, email)) {
                response.setContentType("text/plain");
                response.setStatus(HttpServletResponse.SC_CONFLICT); // Establecer el código de estado HTTP a 409 (Conflicto)
                response.getWriter().write("El correo ya está registrado. Por favor, usa un correo diferente.");
                return;
            }
            // Insertar en la tabla usuarios
            String sqlUsuario = "INSERT INTO usuarios (correo, creado, alias, nombre, contraseña, peso, modificado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, email);
                pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                pstmt.setString(3, nickname);
                pstmt.setString(4, name);
                pstmt.setString(5, password);
                pstmt.setInt(6, weight);
                pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

                int affectedRows = pstmt.executeUpdate();  // Ejecutar la consulta

                if (affectedRows > 0) {
                    // Obtener el ID generado para el usuario
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int userId = rs.getInt(1);

                            // Insertar en la tabla direcciones
                            String sqlDireccion = "INSERT INTO direcciones (direccion, numero_construccion, usuarios_id) VALUES (?, ?, ?)";
                            try (PreparedStatement pstmtDireccion = connection.prepareStatement(sqlDireccion)) {
                                pstmtDireccion.setString(1, address);
                                pstmtDireccion.setString(2, houseNumber);
                                pstmtDireccion.setInt(3, userId);
                                pstmtDireccion.executeUpdate();
                            }

                            // Insertar en la tabla roles_usuarios con rol ID 2 por defecto
                            String sqlRolesUsuarios = "INSERT INTO roles_usuarios (usuario_id, rol_id) VALUES (?, ?)";
                            try (PreparedStatement pstmtRolesUsuarios = connection.prepareStatement(sqlRolesUsuarios)) {
                                pstmtRolesUsuarios.setInt(1, userId);
                                pstmtRolesUsuarios.setInt(2, 2); // Rol ID 2 por defecto
                                pstmtRolesUsuarios.executeUpdate();
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Se produjo un error al procesar la solicitud. Por favor, inténtelo de nuevo más tarde.");
            request.getRequestDispatcher("registro.jsp").forward(request, response); // Redirigir a la página de login con el mensaje de error
        }

        // Redireccionar a una página de éxito o a otro servlet
        response.sendRedirect("login.jsp");
    }

    private boolean emailRegistered(Connection connection, String email) throws SQLException {
        String sqlCheckEmail = "SELECT id FROM usuarios WHERE correo = ?";
        try (PreparedStatement pstmtCheckEmail = connection.prepareStatement(sqlCheckEmail)) {
            pstmtCheckEmail.setString(1, email);
            try (ResultSet rs = pstmtCheckEmail.executeQuery()) {
                return rs.next(); // Retorna true si se encuentra un registro con ese correo
            }
        }
    }
}
