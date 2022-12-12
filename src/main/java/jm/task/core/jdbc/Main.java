package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.dao.UserDao;
import jm.task.core.jdbc.util.dao.UserDaoHibernateImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Leonardo", "Dicaprio", (byte) 45);
        userService.saveUser("Vasya", "Pupkin", (byte) 12);
        userService.saveUser("Nikolay", "Dicaprio", (byte) 33);
        userService.saveUser("Antonio", "Pupkin", (byte) 22);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
