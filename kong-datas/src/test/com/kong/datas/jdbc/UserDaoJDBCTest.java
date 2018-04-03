package com.kong.datas.jdbc;

import com.kong.datas.daos.UserDao;
import com.kong.datas.models.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.security.provider.MD5;

public class UserDaoJDBCTest {
    ClassPathXmlApplicationContext classPathXmlApplicationContext;
    UserDao bean;

    @Before
    public void before() {
        classPathXmlApplicationContext = new
                ClassPathXmlApplicationContext("spring/application-datas.xml");
        bean = classPathXmlApplicationContext.getBean(UserDao.class);
    }

    @Test
    public void testFindUserById() {
        User userById = bean.findUserById(1);
        assert userById != null;
        assert userById.getUserName() == "放飞的小二狗";
        System.out.println(userById.toString());
    }

    @Test
    public void testFindUserByName() {
        User user = bean.findUserByName("放飞的小二狗");
        User user2 = bean.findUserByName("放飞的小二狗2");
        assert user.getUserId() == 1;
        assert user.getUserId() == 3;
        System.out.println(user.toString());
        System.out.println(user2.toString());
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUserName("小白侧123");
        user.setUserPassword("12323232");
        User user1 = bean.addUser(user);

        System.out.println(user1.toString());
    }
}
