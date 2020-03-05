package cn.my.test;

import cn.my.dao.UserDao;
import cn.my.domain.User;
import org.junit.Test;

/**
 * @auther Summerday
 */
public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginUser = new User();
        loginUser.setUsername("superbaby");
        loginUser.setPassword("123");


        UserDao dao = new UserDao();
        System.out.println(dao.login(loginUser));
    }

}
