package cl.praxis.examen_m5.servlet;

import cl.praxis.examen_m5.conexion.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "employee_list", value = "/employee_list")
public class EmployeeListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userRole = (String) request.getSession().getAttribute("rol");
        int userId = (Integer) request.getSession().getAttribute("usuarioId");
        List<Map<String, String>> employees = new ArrayList<>();

        try (Connection connection = Conexion.getConnection()) {
            String sql;
            if ("admin".equals(userRole)) {
                sql = "SELECT nombre, modelo, marca FROM usuarios LEFT JOIN vehiculos ON usuarios.id = vehiculos.usuario_id";
            } else {
                sql = "SELECT nombre, modelo, marca FROM usuarios lEFT JOIN vehiculos ON usuarios.id = vehiculos.usuario_id WHERE usuarios.id = ?";
            }

            PreparedStatement ps = connection.prepareStatement(sql);
            if (!"admin".equals(userRole)) {
                ps.setInt(1, userId);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> employeeData = new HashMap<>();
                employeeData.put("nombre", rs.getString("nombre"));
                employeeData.put("modelo", rs.getString("modelo"));
                employeeData.put("marca", rs.getString("marca"));
                employees.add(employeeData);
            }

            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/employee_list_and_car.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
