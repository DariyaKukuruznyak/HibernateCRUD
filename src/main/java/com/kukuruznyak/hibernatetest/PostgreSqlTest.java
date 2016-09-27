package com.kukuruznyak.hibernatetest;

import com.kukuruznyak.hibernatetest.dao.DaoFactory;
import com.kukuruznyak.hibernatetest.dao.UserDao;
import com.kukuruznyak.hibernatetest.entity.User;


public class PostgreSqlTest {
    public static void main(String[] args) {
        User user = new User(25, "Nastia", "Alexeeva");
        UserDao userDao = new UserDao();

        System.out.println("// create");
        user = userDao.create(user);
        System.out.println(user);

        user.setLastName("Maximova");
        user = userDao.update(user);
        System.out.println("// update");
        System.out.println(userDao.findById(user.getId()));

        System.out.println("// all users");
        for (User currentUser : userDao.getAll()) {
            System.out.println(currentUser);
        }
        System.out.println("// find by id = 2");
        User foundedUser = userDao.findById(2);
        System.out.println(foundedUser);

        System.out.println("// delete founded user");
        userDao.remove(foundedUser);

        System.out.println("// all users");
        for (User currentUser : userDao.getAll()) {
            System.out.println(currentUser);
        }

        DaoFactory.closeEntityManagerFactory();
    }
}
