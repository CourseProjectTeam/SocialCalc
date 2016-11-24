package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import com.mysql.jdbc.Driver;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SimpleServlet", urlPatterns = {"/SimpleServlet"})
public class SimpleServlet extends HttpServlet {

    static Connection conn;
    static Statement st;
    String actPassword;
    String actLogin;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //задание заголовков http ответа
        response.setContentType("text/html; charset=UTF-8");
        String sessionId = request.getSession().getId();
        response.addHeader("session", sessionId);
        //инициализация выходного потока данных
        PrintWriter out = response.getWriter();
        // получение логина и пароля из формы
        String login = (String) request.getParameter("login");
        String pass = (String) request.getParameter("password");
        //параметры доступа к БД
        String url = "jdbc:mysql://localhost:3306/sitedb", user = "root", password = "javaforever";
        try { 
            //регистрация драйвера 
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
        } catch (SQLException e) {
            out.println("Exception while register driver: " + e);
        } catch (ClassNotFoundException e) {
            out.println("Driver not found: " + e);
        } catch (InstantiationException | IllegalAccessException e) {
            out.println("Exception: " + e);
        }

        //TODO: переделать в блок try-with-resources
        try {
            // соединение и запрос к БД
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT login, pass FROM user WHERE login = \""+login+"\";");
            rs.first();
            //получение реальных имени и пароля из базы, если такой логин существует
            //если нет, SQLException
            actLogin = rs.getString("login");
            actPassword = rs.getString("pass");
            //закрытие соединения с БД
            st.close();
            conn.close();
            //если пароль также верный, отправляем OK
            if (pass.equals(actPassword)) {
                out.println("OK: user loged in");
            } else {
                out.println("BAD: incorrect password");
            }
        } catch (SQLException e) {
            out.println("SQLException occured: database doesn't contain this user: " + e.getMessage());
        } catch (Exception e) {
            out.println("Exception: " + e.getMessage());
        } finally {
            //закрываем PrintWriter
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
