package cn.my.web.servlet;

import cn.my.domain.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther Summerday
 */
@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getAttribute("user");

        if (user != null) {
            //编辑页面


            //设置编码
            response.setContentType("text/html;charset=utf-8");
            //输出
            response.getWriter().write("登陆成功！" + user.getUsername() + "欢迎您");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
