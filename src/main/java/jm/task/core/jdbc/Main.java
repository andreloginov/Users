package jm.task.core.jdbc;


import jm.task.core.jdbc.util.dao.UserDao;
import jm.task.core.jdbc.util.dao.UserDaoHibernateImpl;


public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();


        userDao.saveUser("ke", "be", (byte) 2);
        userDao.getAllUsers();

    }
}
