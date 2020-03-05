package cn.my.dao;

import cn.my.domain.User;
import cn.my.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @auther Summerday
 *
 * 操作数据库中User表的类
 */
public class UserDao {


    //声明JDBCTemplate 对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登陆方法
     * @param loginUser  只有用户名和密码
     * @return  user包含用户全部的数据,如果没有查询到，返回null
     */
    public User login(User loginUser){
        try {
            //1.编写sql
            String sql = "select * from user where username = ? and password = ?";

            //调用query方法
            return template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
