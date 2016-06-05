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

/**
 * Created by Administrator on 2016/6/5.
 */
@WebServlet(urlPatterns = "/user")
public class UserAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action.equals("userquery")) {
            userquery(req, resp);
        }

    }

    private void userquery(HttpServletRequest req, HttpServletResponse resp) {

        String wordToQuery = req.getParameter("queryword");

        Connection connection = DB.getConnection();
        if (connection == null) {
            return;
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Word word = null;

        String sql = "SELECT * FROM db_dictionary.word where english = ? OR chinese = ?";

        try {

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, wordToQuery);
            preparedStatement.setString(2, wordToQuery);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                word = new Word(
                        resultSet.getInt("id"),
                        resultSet.getString("english"),
                        resultSet.getString("chinese"),
                        resultSet.getString("phonetic"),
                        resultSet.getString("partOfSpeech"),
                        resultSet.getString("category")
                );

                //req.setAttribute("queryresult",word);
                req.getSession().setAttribute("queryresult", word);
                req.getSession().setAttribute("message", "查询结果 :");
                resp.sendRedirect("index.jsp");
            } else {
                req.getSession().setAttribute("queryresult", word);
                req.getSession().setAttribute("message", "未找到该单词.");
                resp.sendRedirect("index.jsp");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}




























