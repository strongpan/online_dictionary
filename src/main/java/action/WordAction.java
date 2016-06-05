package action;

import model.Word;
import util.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mingfei.net@gmail.com
 * 2016/6/4.
 */
@WebServlet(urlPatterns = "/word")
public class WordAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("create")) {
            create(req, resp);
        }
        if (action.equals("query")) {
            query(req, resp);
        }
        if (action.equals("search")) {
            search(req, resp);
        }
        if (action.equals("modify")) {
            modify(req, resp);
        }
        if (action.equals("remove")) {
            remove(req, resp);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String english = req.getParameter("english");
        String chinese = req.getParameter("chinese");
        String phonetic = req.getParameter("phonetic");
        String partOfSpeech = req.getParameter("partOfSpeech");
        String category = req.getParameter("category");

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO db_dictionary.word VALUES (NULL, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, english);
            preparedStatement.setString(2, chinese);
            preparedStatement.setString(3, phonetic);
            preparedStatement.setString(4, partOfSpeech);
            preparedStatement.setString(5, category);

            preparedStatement.executeUpdate();
            query(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM db_dictionary.word ORDER BY id";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<Word> words = new ArrayList<>();
            while (resultSet.next()) {
                Word word = new Word(
                        resultSet.getInt("id"),
                        resultSet.getString("english"),
                        resultSet.getString("chinese"),
                        resultSet.getString("phonetic"),
                        resultSet.getString("partOfSpeech"),
                        resultSet.getString("category")
                );
                words.add(word);
            }
            req.getSession().setAttribute("words", words);
            resp.sendRedirect("/admin/admin.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM db_dictionary.word WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Word word = new Word(
                    resultSet.getInt("id"),
                    resultSet.getString("english"),
                    resultSet.getString("chinese"),
                    resultSet.getString("phonetic"),
                    resultSet.getString("partOfSpeech"),
                    resultSet.getString("category")
            );
            req.getSession().setAttribute("word", word);
            resp.sendRedirect("/admin/edit.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }

    private void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String english = req.getParameter("english");
        String chinese = req.getParameter("chinese");
        String phonetic = req.getParameter("phonetic");
        String partOfSpeech = req.getParameter("partOfSpeech");
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE db_dictionary.word SET english = ?, chinese = ?, phonetic = ?, partOfSpeech = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, english);
            preparedStatement.setString(2, chinese);
            preparedStatement.setString(3, phonetic);
            preparedStatement.setString(4, partOfSpeech);
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
            query(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
    }
    private void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM db_dictionary.word WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            query(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
