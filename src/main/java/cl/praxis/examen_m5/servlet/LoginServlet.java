package cl.praxis.examen_m5.servlet;

import cl.praxis.examen_m5.conexion.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "login-servlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("email");
        String contraseña = request.getParameter("password");

        try {
            Connection connection = Conexion.getConnection();
            String sql = "SELECT u.*, r.nombre AS rol_nombre " +
                    "FROM usuarios u " +
                    "JOIN roles_usuarios ru ON u.id = ru.usuario_id " +
                    "JOIN roles r ON ru.rol_id = r.id " +
                    "WHERE u.correo = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("contraseña");
                if (contraseña.equals(storedPassword)) {
                    // Autenticación exitosa: almacenar datos del usuario en la sesión
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioId", rs.getInt("id"));
                    session.setAttribute("nombre", rs.getString("nombre"));
                    session.setAttribute("correo", rs.getString("correo"));

                    // Obtener y almacenar el rol del usuario
                    String rol = rs.getString("rol_nombre");
                    session.setAttribute("rol", rol);

                    response.sendRedirect("home.jsp"); // Redirigir a la pantalla principal
                } else {
                    request.setAttribute("errorMessage", "Contraseña incorrecta");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Usuario no encontrado");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Se produjo un error al procesar la solicitud. Por favor, inténtelo de nuevo más tarde.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}