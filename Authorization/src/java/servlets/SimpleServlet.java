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
import org.json.*;

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
        // получение логина и пароля из формы
        String login = (String) request.getParameter("login");
        String pass = (String) request.getParameter("password");
        //параметры доступа к БД
        String url = "jdbc:mysql://localhost:3306/sitedb", user = "root", password = "javaforever";
        JSONObject json = new JSONObject();
        try (PrintWriter out = response.getWriter()) {
            try {
                //регистрация драйвера 
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            } catch (SQLException e) {
                json.put("exception", e.getClass()+": Exception while register jdbc driver");
            } catch (ClassNotFoundException e) {
                json.put("exception", e.getClass()+": Driver not found");
            } catch (InstantiationException | IllegalAccessException e) {
                json.put("exception", e.toString());
            }
            try {
                // соединение и запрос к БД
                conn = DriverManager.getConnection(url, user, password);
                st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT login, pass FROM user WHERE login = \"" + login + "\";");
                rs.first();
                //получение реальных имени и пароля из базы, если такой логин существует
                //если нет, SQLException
                actLogin = rs.getString("login");
                actPassword = rs.getString("pass");
                //закрытие соединения с БД
                st.close();
                conn.close();
            } catch (SQLException e) {
                json.put("exception", "SQLException: no such user");
                json.put("accepted", false);
            } catch (Exception e) {
                json.put("exception", e.toString());
                json.put("accepted", false);
            } finally {
                if (login.equals(actLogin) && pass.equals(actPassword)) {
                    json.put("accepted", true);
                } else {
                    json.put("accepted", false);
                }
                // отсылаем данные
                out.print(json);
            }
        } catch (JSONException e) {

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
