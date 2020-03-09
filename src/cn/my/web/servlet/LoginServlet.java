package cn.my.web.servlet;

import cn.my.dao.UserDao;
import cn.my.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther Summerday
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取请求参数
        String username = request.getParameter("username");

        String password = request.getParameter("password");

        //3.封装User对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        User user = null;
        try {
            //4.调用UserDao的login方法
            UserDao userDao = new UserDao();
            user = userDao.login(loginUser);
            System.out.println(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //5.判断user
        if(user == null){
            //登录失败
            request.getRequestDispatcher("/failServlet").forward(request,response);
        }else {
            //登陆成功
            //存储数据
            request.setAttribute("user",user);
            //转发
            request.getRequestDispatcher("/successServlet").forward(request,response);
        }


    }
}
